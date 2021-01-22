package pl.com.happyhouse.krzeptow.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.com.happyhouse.krzeptow.dto.AbsenceDTO;
import pl.com.happyhouse.krzeptow.factory.NextMonthPresenceFactory;
import pl.com.happyhouse.krzeptow.model.Child;
import pl.com.happyhouse.krzeptow.model.Presence;
import pl.com.happyhouse.krzeptow.model.User;
import pl.com.happyhouse.krzeptow.model.WeeklyCarePlan;
import pl.com.happyhouse.krzeptow.repository.PresenceRepository;
import pl.com.happyhouse.krzeptow.utils.DatesSplitter;
import pl.com.happyhouse.krzeptow.utils.LocalDateConverter;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@AllArgsConstructor
public class PresenceService {

    private final PresenceRepository presenceRepository;
    private final NextMonthPresenceFactory nextMonthPresenceFactory;
    private final HolidayService holidayService;
    private final ChildService childService;
    private final WeeklyCarePlanService weeklyCarePlanService;

    public Presence save(Presence presence) {
        return presenceRepository.save(presence);
    }

    public List<Presence> saveAll(List<Presence> presences) {
        return presenceRepository.saveAll(presences);
    }

    public Presence getByChildAndDate(Child child, LocalDate date) {
        return presenceRepository.getByChildAndDate(child, date).orElseThrow(NoSuchElementException::new);
    }

    public LocalDate getByMaxDate() {
        try {
            Presence presence = presenceRepository.getMaxDate().orElseThrow();
            return presence.getDate();
        } catch (Exception e) {
            return LocalDate.now().minusMonths(1);
        }
    }

    public List<Presence> createNextMonth() {
        List<LocalDate> datesEntry = getDatesEntries();
        List<Child> children = childService.findAll();
        List<Presence> result = nextMonthPresenceFactory.create(datesEntry, children);
        return presenceRepository.saveAll(result);
    }

    public List<Presence> getPresencesByChild(Child c) {
        return presenceRepository.getByChild(c);
    }

    public List<LocalDate> getPresenceAllDatesBy(Child child) {
        return getPresencesByChild(child).stream()
                .map(Presence::getDate)
                .collect(Collectors.toList());
    }

    public List<LocalDate> getRealPresenceDatesBy(Child child) {
        return getPresencesByChild(child).stream()
                .filter(p -> p.getHours() > 0)
                .map(Presence::getDate)
                .collect(Collectors.toList());
    }

    public List<Presence> registerAbsence(AbsenceDTO absenceDTO, User parent) {
        extractChildrenList(absenceDTO);
        List<WeeklyCarePlan> weeklyCarePlanList = weeklyCarePlanService.registerWeekCarePlanChanges(absenceDTO);
        updateWithNewCarePlan(weeklyCarePlanList);
        List<Presence> absences = new ArrayList<>();
        List<String> datesList = splitIntoList(absenceDTO.getDates(), absenceDTO.getChildren().size());
        if (datesList.isEmpty()) return Collections.emptyList();
        for (int i = 0; i < absenceDTO.getChildren().size(); i++) {
            absences.addAll(getPresences(absenceDTO.getChildren().get(i),
                    datesList.get(i), parent, absenceDTO.getDescription()));
        }
        return saveAll(absences);
    }

    private List<Presence> getPresences(Child child, String dates, User parent, String description) {
        List<Presence> result = new ArrayList<>();
        if (dates.isEmpty()) return Collections.emptyList();
        List<LocalDate> datesFromForm = LocalDateConverter.stringToLocalDates(dates);
        List<LocalDate> datesInPresenceEntity = getRealPresenceDatesBy(child);
        DatesSplitter.createDates(datesFromForm, datesInPresenceEntity);

        result.addAll(createAbsences(child));
        result.addAll(createPresences(child));
        result.forEach(a -> a.setReporter(parent).setDescription(description));
        return result;
    }

    private List<Presence> createAbsences(Child child) {
        return DatesSplitter.getDatesToMakeAbsent().stream()
                .map(d -> getByChildAndDate(child, d))
                .map(this::makeAbsent)
                .collect(Collectors.toList());
    }

    private List<Presence> createPresences(Child child) {
        return DatesSplitter.getDatesToMakePresent().stream()
                .map(d -> getByChildAndDate(child, d))
                .map(this::makePresent)
                .collect(Collectors.toList());
    }

    private Presence makeAbsent(Presence presence) {
        return presence.setHours(-1);
    }

    private Presence makePresent(Presence presence) {
        LocalDate date = presence.getDate();
        return presence.setHours(childService.getHoursFor(presence.getChild(), date.getDayOfWeek()));
    }

    private List<String> splitIntoList(String dates, int childrenCount) {
        dates = dates.replaceAll("\",\"", "\";\"");
        List<String> result = Arrays.asList(dates.split(",", childrenCount).clone());
        return result;
    }

    private YearMonth getLastMonthInDB() {
        LocalDate date = getByMaxDate();
        return YearMonth.of(date.getYear(), date.getMonth());
    }

    private List<LocalDate> getDatesEntries() {
        YearMonth yearMonth = getLastMonthInDB().plusMonths(1);
        List<LocalDate> monthHolidays = holidayService.getHolidaysInYearMonth(yearMonth);
        Stream<LocalDate> dateEntries = LocalDateConverter.createStreamFromYearMonth(yearMonth);
        return dateEntries
                .filter(date -> !date.getDayOfWeek().equals(DayOfWeek.SATURDAY))
                .filter(date -> !date.getDayOfWeek().equals(DayOfWeek.SUNDAY))
                .filter(date -> !monthHolidays.contains(date))
                .collect(Collectors.toList());
    }

    private void extractChildrenList(AbsenceDTO absenceDTO) {
        List<Child> result = Arrays.stream(absenceDTO.getChildrenIds()
                .split(","))
                .mapToLong(Long::valueOf)
                .mapToObj(childService::getById)
                .collect(Collectors.toList());
        absenceDTO.setChildren(result);
    }

    public void updateWithNewCarePlan(List<WeeklyCarePlan> plans) {
        for (WeeklyCarePlan plan : plans) {
            List<Presence> presenceList = presenceRepository.getByChildAndDateAfter(plan.getChild(), LocalDate.now());
            presenceList
                    .forEach(p -> p.setHours(weeklyCarePlanService.getHoursFor(plan, p.getDate().getDayOfWeek())));
        }
    }
}
