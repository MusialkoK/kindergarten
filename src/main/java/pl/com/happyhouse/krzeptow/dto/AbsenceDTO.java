package pl.com.happyhouse.krzeptow.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.com.happyhouse.krzeptow.model.Child;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class AbsenceDTO {
    private List<Child> children;
    private String dates;
    private String description;
}
