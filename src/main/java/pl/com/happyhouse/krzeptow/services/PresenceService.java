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
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
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
        List<Presence> absences = getByAbsenceDTO(absenceDTO);
        absences.forEach(a->{
            a.setHours(-1);
            a.setReporter(parent);
        });
        return saveAll(absences);
    }

    public Presence getByChildAndDate(Child child, LocalDate date) {
        return presenceRepository.getByChildAndDate(child, date).orElseThrow(NoSuchElementException::new);
    }

    public LocalDate getByMaxDate(){
        try{
            Presence presence = presenceRepository.getMaxDate().orElseThrow();
            return presence.getDate();
        }catch (Exception e){
            return LocalDate.now().minusMonths(1);
        }
    }

    private List<Presence> getByAbsenceDTO(AbsenceDTO absenceDTO){
        List<Presence> absences = new ArrayList<>();
        for (Child child : absenceDTO.getChildren()) {
            for (LocalDate date : absenceDTO.getLocalDates()) {
                Presence absence = getByChildAndDate(child, date);
                absences.add(absence);
            }
        }
        return absences;
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

    public List<Presence> getByChild(Child c) {
        return presenceRepository.getByChild(c);
    }


//    public Presence registerAbsence(Child child, LocalDate date, User parent) {
//        Presence presence = getByChildAndDate(child, date);
//        presence.setHours(-1);
//        presence.setReporter(parent);
//        return presenceRepository.save(presence);
//    }


}
