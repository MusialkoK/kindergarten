package pl.com.happyhouse.krzeptow.dto;

import lombok.*;
import pl.com.happyhouse.krzeptow.model.Activity;
import pl.com.happyhouse.krzeptow.model.Child;

import javax.validation.Valid;
import java.util.List;

@RequiredArgsConstructor
@Data
@Builder
public class ActivityForChildDto {
    private final Child child;
    private final List<Activity> activity;

}
