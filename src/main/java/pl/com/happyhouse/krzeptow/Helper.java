package pl.com.happyhouse.krzeptow;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Helper {

    public static Stream<LocalDate> createStreamFromYearMonth(YearMonth yearMonth){
        LocalDate startDate = LocalDate.of(yearMonth.getYear(), yearMonth.getMonth(),1);
        LocalDate endDate = LocalDate.of(yearMonth.getYear(),
                yearMonth.getMonth(),
                yearMonth.lengthOfMonth()).plusDays(1);
        return startDate.datesUntil(endDate);
    }

    public static String createStringFromLocalDateList(List<LocalDate> dates){
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("M/d/yyyy");
        return dates.stream()
                .map(d -> d.format(dateTimeFormatter))
                .collect(Collectors.joining(","));
    }



}
