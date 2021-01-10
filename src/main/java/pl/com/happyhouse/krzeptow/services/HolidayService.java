package pl.com.happyhouse.krzeptow.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.com.happyhouse.krzeptow.model.Holiday;
import pl.com.happyhouse.krzeptow.model.rest.HolidayRest;
import pl.com.happyhouse.krzeptow.repository.HolidayRepository;
import pl.com.happyhouse.krzeptow.services.rest.HolidayRestService;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class HolidayService {

    private final HolidayRestService holidayRestService;
    private final HolidayRepository holidayRepository;

    public List<Holiday> recordHolidaysInYear(int year){
        List<HolidayRest> holidayRestList = holidayRestService.getHolidaysInYear(year);
        List<Holiday> holidayList = holidayRestList.stream()
                .map(this::create)
                .collect(Collectors.toList());
        return holidayRepository.saveAll(holidayList);
    }

    public List<LocalDate> getHolidaysInYearMonth(YearMonth yearMonth){
        LocalDate startDate = LocalDate.of(yearMonth.getYear(), yearMonth.getMonth(),1);
        LocalDate endDate = LocalDate.of(yearMonth.getYear(), yearMonth.getMonth(), yearMonth.lengthOfMonth()).plusDays(1);
        return holidayRepository.getByDateBetween(startDate, endDate).stream()
                .map(Holiday::getDate)
                .collect(Collectors.toList());
    }

    private Holiday create(HolidayRest holidayRest){
        return Holiday.builder()
                .localName(holidayRest.getLocalName())
                .date(LocalDate.parse(holidayRest.getDate(), DateTimeFormatter.ofPattern("yyyy-MM-dd")))
                .build();
    }

    public List<LocalDate> findAllDates() {
        return holidayRepository.findAll().stream().map(Holiday::getDate).collect(Collectors.toList());
    }
}
