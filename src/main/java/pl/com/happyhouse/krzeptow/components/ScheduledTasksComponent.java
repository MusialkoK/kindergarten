package pl.com.happyhouse.krzeptow.components;

import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class ScheduledTasksComponent {

    @Scheduled(cron = "* * * * * ?")
    public void generateNextMonthPresences() {
        System.out.println("aaa");
        //"0 0 0 10 * ?"
    }
}
