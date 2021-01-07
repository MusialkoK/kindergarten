package pl.com.happyhouse.krzeptow.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.com.happyhouse.krzeptow.model.WeeklyCarePlan;
import pl.com.happyhouse.krzeptow.repository.WeeklyCarePlanRepository;

import java.time.DayOfWeek;
import java.time.LocalTime;

@Service
@AllArgsConstructor
public class WeeklyCarePlanService {

    private final WeeklyCarePlanRepository weeklyCarePlanRepository;

    public WeeklyCarePlan save(WeeklyCarePlan weeklyCarePlan){
        return weeklyCarePlanRepository.save(weeklyCarePlan);
    }

    public int getHoursFor(WeeklyCarePlan weeklyCarePlan, DayOfWeek dayOfWeek) {
        int errorValue = -1000;
        if(weeklyCarePlan!=null){
            switch (dayOfWeek) {
                case MONDAY:
                    return hoursCount(weeklyCarePlan.getMondayStart(), weeklyCarePlan.getMondayEnd());
                case TUESDAY:
                    return hoursCount(weeklyCarePlan.getTuesdayStart(), weeklyCarePlan.getTuesdayEnd());
                case WEDNESDAY:
                    return hoursCount(weeklyCarePlan.getWednesdayStart(), weeklyCarePlan.getWednesdayEnd());
                case THURSDAY:
                    return hoursCount(weeklyCarePlan.getThursdayStart(), weeklyCarePlan.getThursdayEnd());
                case FRIDAY:
                    return hoursCount(weeklyCarePlan.getFridayStart(), weeklyCarePlan.getFridayEnd());
                default:
                    return errorValue;
            }
        } else return errorValue;
    }

    private int hoursCount(LocalTime start, LocalTime end) {
        if(start!=null && end!=null){
            return end.getHour() - start.getHour();
        }else return 0;

    }
}
