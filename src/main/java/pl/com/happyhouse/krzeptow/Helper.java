package pl.com.happyhouse.krzeptow;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.stream.Stream;

public class Helper {

    public static Stream<LocalDate> createStreamFromYearMonth(YearMonth yearMonth){
        LocalDate startDate = LocalDate.of(yearMonth.getYear(), yearMonth.getMonth(),1);
        LocalDate endDate = LocalDate.of(yearMonth.getYear(),
                yearMonth.getMonth(),
                yearMonth.lengthOfMonth()).plusDays(1);
        return startDate.datesUntil(endDate);
    }



}
