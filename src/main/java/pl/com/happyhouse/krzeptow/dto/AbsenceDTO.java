package pl.com.happyhouse.krzeptow.dto;


import lombok.*;
import pl.com.happyhouse.krzeptow.model.Child;

import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AbsenceDTO {
    @NotEmpty
    @Getter
    @Setter
    private List<Child> children;

    @NotEmpty
    @Setter
    @Getter
    private String dates;

    @Getter
    @Setter
    private String description;

    @Getter
    private List<LocalDate> singleDates;

    public void parse(){
        singleDates = getDates(dates);
    }

    private List<LocalDate> getDates(String stringOfDates) {
        stringOfDates = stringOfDates.replaceAll("[\"\\[\\]]", "");
        List<String> datesInString = Arrays.asList(stringOfDates.split(","));
        return datesInString.stream()
                .map(s -> LocalDate.parse(s, DateTimeFormatter.ofPattern("MM/dd/yyyy")))
                .collect(Collectors.toList());
    }

}
