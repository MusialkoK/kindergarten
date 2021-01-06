package pl.com.happyhouse.krzeptow.components;

import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import pl.com.happyhouse.krzeptow.services.HolidayService;
import pl.com.happyhouse.krzeptow.services.PresenceService;

import java.time.LocalDate;

@Component
@AllArgsConstructor
public class ScheduledTasksComponent {

    private final PresenceService presenceService;
    private final HolidayService holidayService;

    @Scheduled(cron = "0 0 0 10 * ?")
    public void generateNextMonthPresences() {
        presenceService.createNextMonth();
    }

    @Scheduled(cron = "0 0 0 9 12 ?")
    public void getCountryHolidays() {
        int year = LocalDate.now().getYear() + 1;
        holidayService.recordHolidaysInYear(year);
    }
}
