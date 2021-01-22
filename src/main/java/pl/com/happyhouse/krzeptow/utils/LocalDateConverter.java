package pl.com.happyhouse.krzeptow.utils;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class LocalDateConverter {

    public static Stream<LocalDate> createStreamFromYearMonth(YearMonth yearMonth){
        LocalDate startDate = LocalDate.of(yearMonth.getYear(), yearMonth.getMonth(),1);
        LocalDate endDate = LocalDate.of(yearMonth.getYear(),
                yearMonth.getMonth(),
                yearMonth.lengthOfMonth()).plusDays(1);
        return startDate.datesUntil(endDate);
    }

    public static String localDatesToString(List<LocalDate> dates){
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("M/d/yyyy");
        return dates.stream()
                .map(d -> d.format(dateTimeFormatter))
                .collect(Collectors.joining(","));
    }

    public static List<LocalDate> stringToLocalDates(String dates){
        dates = dates.replaceAll("\\[", "");
        dates = dates.replaceAll("\\]", "");
        dates = dates.replaceAll("\"", "");
        List<String> datesInString = Arrays.asList(dates.split(";"));
        return datesInString.stream()
                .map(s -> LocalDate.parse(s, DateTimeFormatter.ofPattern("MM/dd/yyyy")))
                .collect(Collectors.toList());
    }

}
