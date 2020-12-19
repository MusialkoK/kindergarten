package pl.com.happyhouse.krzeptow.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.com.happyhouse.krzeptow.model.MealChange;
import pl.com.happyhouse.krzeptow.repository.MealChangeRepository;

import java.time.LocalDateTime;
import java.util.NoSuchElementException;

@Service
@AllArgsConstructor
public class MealChangeService {

    private final MealChangeRepository mealChangeRepository;
    private final ChildService childService;

    public MealChange save(MealChange mealChange){
        mealChange.setTimeStamp(LocalDateTime.now());
        childService.changeMealPlan(mealChange);
        return mealChangeRepository.save(mealChange);
    }

    public MealChange getById(Long id){
        return mealChangeRepository.getById(id).orElseThrow(NoSuchElementException::new);
    }

}
