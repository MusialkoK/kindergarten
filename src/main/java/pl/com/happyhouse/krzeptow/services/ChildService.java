package pl.com.happyhouse.krzeptow.services;

import lombok.AllArgsConstructor;
import org.hibernate.annotations.Parent;
import org.springframework.stereotype.Service;
import pl.com.happyhouse.krzeptow.dto.ActivityForChildDto;
import pl.com.happyhouse.krzeptow.model.Activity;
import pl.com.happyhouse.krzeptow.model.Child;
import pl.com.happyhouse.krzeptow.model.MealChange;
import pl.com.happyhouse.krzeptow.model.User;
import pl.com.happyhouse.krzeptow.repository.ChildRepository;

import java.util.List;
import java.util.Set;

@Service
@AllArgsConstructor
public class ChildService {

    private final ChildRepository childRepository;
    private final UserService userService;

    public Child save(Child child){
        return childRepository.save(child);
    }

    public Child getById(long id){
        return childRepository.getById(id);
    }

    public List<Child> getByGuardianId(long id){
        User user = userService.getById(id);
        return childRepository.getChildrenByParentContaining(user);
    }

    public void changeMealPlan(MealChange mealChange){
        mealChange.getChild().setCurrentMealPlan(mealChange.getNewMealPlan());
    }


    public List<Child> getChildByParent(User parent){
        return childRepository.getChildrenByParentContaining(parent);
    }

    public void addActivityToChildren(ActivityForChildDto activityForChildDto){

        Child child = getById(activityForChildDto.getChild().getId());
        Set<Activity> activities= child.getActivities();
        activities.addAll(activityForChildDto.getActivity());
        save(child);

    }

}
