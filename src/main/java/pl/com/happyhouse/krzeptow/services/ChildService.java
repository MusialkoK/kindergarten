package pl.com.happyhouse.krzeptow.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.com.happyhouse.krzeptow.model.*;
import pl.com.happyhouse.krzeptow.repository.ChildRepository;

import java.time.DayOfWeek;
import java.util.List;

import static pl.com.happyhouse.krzeptow.model.DayCareStrategyType.HOURLY;

@Service
@AllArgsConstructor
public class ChildService {

    private final ChildRepository childRepository;
    private final UserService userService;
    private final WeeklyCarePlanService weeklyCarePlanService;
    private final DayCareStrategyService dayCareStrategyService;

    public Child save(Child child){
        return childRepository.save(child);
    }

    public Child getById(long id){
        return childRepository.getById(id);
    }

    public List<Child> getByParentId(long id){
        User user = userService.getById(id);
        return childRepository.getChildrenByParentContaining(user);
    }

    public void changeMealPlan(MealChange mealChange){
        mealChange.getChild().setCurrentMealPlan(mealChange.getNewMealPlan());
    }

    public List<Child> findAll(){
        return childRepository.findAll();
    }

    public int getHoursFor(Child child, DayOfWeek dayOfWeek){
        int errorValue = -1000;
        switch(child.getDayCareStrategy().getType()){
            case HOURLY:
                return weeklyCarePlanService.getHoursFor(child.getWeeklyCarePlan(), dayOfWeek);
            case MONTHLY:
                return 10;
            default:
                return errorValue;
        }
    }

    public Child setWeeklyCarePlan(Child child, WeeklyCarePlan weeklyCarePlan){
        weeklyCarePlanService.save(weeklyCarePlan);
        DayCareStrategy dayCareStrategy = dayCareStrategyService.getByType(HOURLY);
        child.setDayCareStrategy(dayCareStrategy);
        child.setWeeklyCarePlan(weeklyCarePlan);
        return save(child);
    }
}
