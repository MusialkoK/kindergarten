package pl.com.happyhouse.krzeptow.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.com.happyhouse.krzeptow.dto.AbsenceDTO;
import pl.com.happyhouse.krzeptow.model.Child;
import pl.com.happyhouse.krzeptow.model.WeeklyCarePlan;
import pl.com.happyhouse.krzeptow.repository.WeeklyCarePlanRepository;
import pl.com.happyhouse.krzeptow.utils.LocalTimeConverter;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static pl.com.happyhouse.krzeptow.model.DayCareStrategyType.HOURLY;

@Service
@AllArgsConstructor
public class WeeklyCarePlanService {

    private final WeeklyCarePlanRepository weeklyCarePlanRepository;

    public WeeklyCarePlan save(WeeklyCarePlan weeklyCarePlan) {
        return weeklyCarePlanRepository.save(weeklyCarePlan);
    }

    public List<WeeklyCarePlan> saveAll(List<WeeklyCarePlan> list) {
        return weeklyCarePlanRepository.saveAll(list);
    }

    public int getHoursFor(WeeklyCarePlan weeklyCarePlan, DayOfWeek dayOfWeek) {
        int errorValue = -1000;
        if (weeklyCarePlan != null) {
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

    public List<WeeklyCarePlan> registerChanges(AbsenceDTO absenceDTO) {
        List<WeeklyCarePlan> result = new ArrayList<>();
        List<Child> children = absenceDTO.getChildren().stream()
                .filter(c -> c.getDayCareStrategy().getType().equals(HOURLY))
                .collect(Collectors.toList());
        List<LocalTime> startHours = LocalTimeConverter.createTimeListFromString(absenceDTO.getStartHours());
        List<LocalTime> endHours = LocalTimeConverter.createTimeListFromString(absenceDTO.getEndHours());
        for (int i = 0; i < children.size(); i++) {
            Child child = children.get(i);
            WeeklyCarePlan weeklyCarePlan = child.getWeeklyCarePlan();
            weeklyCarePlan.setMondayStart(startHours.get(i * 5)).setMondayEnd(endHours.get(i * 5));
            weeklyCarePlan.setThursdayStart(startHours.get(i * 5 + 1)).setTuesdayEnd(endHours.get(i * 5 + 1));
            weeklyCarePlan.setWednesdayStart(startHours.get(i * 5 + 2)).setWednesdayEnd(endHours.get(i * 5 + 2));
            weeklyCarePlan.setThursdayStart(startHours.get(i * 5 + 3)).setThursdayEnd(endHours.get(i * 5 + 3));
            weeklyCarePlan.setFridayStart(startHours.get(i * 5 + 4)).setFridayEnd(endHours.get(i * 5 + 4));
            result.add(weeklyCarePlan);
        }
        return saveAll(result);
    }

    private int hoursCount(LocalTime start, LocalTime end) {
        if (start != null && end != null) {
            return end.getHour() - start.getHour();
        } else return 0;
    }
}
