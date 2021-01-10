package pl.com.happyhouse.krzeptow.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.com.happyhouse.krzeptow.dto.ActivityForChildDto;
import pl.com.happyhouse.krzeptow.model.Activity;
import pl.com.happyhouse.krzeptow.model.Child;
import pl.com.happyhouse.krzeptow.model.MealPlan;
import pl.com.happyhouse.krzeptow.repository.ActivityRepository;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ActivityService {
    private final ActivityRepository activityRepository;

    public Activity save(Activity activity) {
        return activityRepository.save(activity);
    }

    public Activity getById(Long id) {
        return activityRepository.getById(id).orElseThrow(NoSuchElementException::new);
    }

    public List<Activity> getAll() {
        return  activityRepository.findAll();
    }
}
