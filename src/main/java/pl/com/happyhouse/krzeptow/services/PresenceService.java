package pl.com.happyhouse.krzeptow.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.com.happyhouse.krzeptow.Helper;
import pl.com.happyhouse.krzeptow.dto.AbsenceDTO;
import pl.com.happyhouse.krzeptow.factory.NextMonthPresenceFactory;
import pl.com.happyhouse.krzeptow.model.Child;
import pl.com.happyhouse.krzeptow.model.Presence;
import pl.com.happyhouse.krzeptow.model.User;
import pl.com.happyhouse.krzeptow.repository.PresenceRepository;

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


    public Presence save(Presence presence) {
        return presenceRepository.save(presence);
    }

    public List<Presence> saveAll(List<Presence> presences) {
        return presenceRepository.saveAll(presences);
    }

    public List<Presence> registerAbsence(AbsenceDTO absenceDTO, User parent) {
        List<Presence> absences = new ArrayList<>();
        List<String> datesList = splitIntoList(absenceDTO.getDates(), absenceDTO.getChildren().size());
        if (datesList.isEmpty()) return Collections.emptyList();
        for (int i = 0; i < absenceDTO.getChildren().size(); i++) {
            if (datesList.get(i).isEmpty()) continue;
            Child child = absenceDTO.getChildren().get(i);
            List<LocalDate> datesFromForm = Helper.stringToLocalDates(datesList.get(i));
            List<LocalDate> datesInPresenceEntity = getPresenceAllDatesBy(child);
            absences = datesInPresenceEntity.stream()
                    .filter(d -> !datesFromForm.contains(d))
                    .map(d -> getByChildAndDate(child, d))
                    .collect(Collectors.toList());
        }
        absences.forEach(a -> a.setHours(-1).setReporter(parent).setDescription(absenceDTO.getDescription()));
        return saveAll(absences);
    }

    private List<String> splitIntoList(String dates, int childrenCount) {
        dates = dates.replaceAll("\",\"", "\";\"");
        List<String> result = Arrays.asList(dates.split(",", 2).clone());
        return result;
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

    private YearMonth getLastMonthInDB() {
        LocalDate date = getByMaxDate();
        return YearMonth.of(date.getYear(), date.getMonth());
    }

    private List<LocalDate> getDatesEntries() {
        YearMonth yearMonth = getLastMonthInDB().plusMonths(1);
        List<LocalDate> monthHolidays = holidayService.getHolidaysInYearMonth(yearMonth);
        Stream<LocalDate> dateEntries = Helper.createStreamFromYearMonth(yearMonth);
        return dateEntries
                .filter(date -> !date.getDayOfWeek().equals(DayOfWeek.SATURDAY))
                .filter(date -> !date.getDayOfWeek().equals(DayOfWeek.SUNDAY))
                .filter(date -> !monthHolidays.contains(date))
                .collect(Collectors.toList());
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


//    public Presence registerAbsence(Child child, LocalDate date, User parent) {
//        Presence presence = getByChildAndDate(child, date);
//        presence.setHours(-1);
//        presence.setReporter(parent);
//        return presenceRepository.save(presence);
//    }


}
