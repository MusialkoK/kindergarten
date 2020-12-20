package pl.com.happyhouse.krzeptow.factory;


import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.com.happyhouse.krzeptow.dto.MealChangeDTO;
import pl.com.happyhouse.krzeptow.model.MealChange;
import pl.com.happyhouse.krzeptow.services.MealPlanService;

import java.util.List;
import java.util.stream.Collectors;



@Service
@AllArgsConstructor
public class MealChangeFactory {

    private final MealPlanService mealPlanService;

    public List<MealChange> create(MealChangeDTO mealChangeDTO) {
        return mealChangeDTO.getSelectedChildren().stream().map(c ->
                MealChange.builder()
                        .child(c)
                        .newMealPlan(mealPlanService.getById(mealChangeDTO.getSelectedMealPlan()))
                        .build())
                .collect(Collectors.toList());
    }
}
