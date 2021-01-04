package pl.com.happyhouse.krzeptow.services.rest;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.com.happyhouse.krzeptow.model.rest.Holiday;
import pl.com.happyhouse.krzeptow.model.rest.HolidayRest;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class HolidayService {

    private final HolidayRestService holidayRestService;

    public List<Holiday> getHolidaysInYear(int year){
        List<HolidayRest> holidayRestList = holidayRestService.getHolidaysInYear(year);
        return holidayRestList.stream()
                .map(this::create).collect(Collectors.toList());
    }


    private Holiday create(HolidayRest holidayRest){
        return Holiday.builder()
                .localName(holidayRest.getLocalName())
                .date(LocalDate.parse(holidayRest.getDate(), DateTimeFormatter.ofPattern("yyyy-MM-dd")))
                .build();
    }
}
