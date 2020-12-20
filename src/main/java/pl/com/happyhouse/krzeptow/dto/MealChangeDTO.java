package pl.com.happyhouse.krzeptow.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.com.happyhouse.krzeptow.model.Child;
import pl.com.happyhouse.krzeptow.model.MealPlan;

import javax.validation.constraints.NotEmpty;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class MealChangeDTO {

    @NotEmpty
    private MealPlan mealPlan;
    @NotEmpty
    private List<Child> children;
}
