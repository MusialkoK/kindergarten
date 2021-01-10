package pl.com.happyhouse.krzeptow.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.com.happyhouse.krzeptow.model.Child;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class AbsenceDTO {

    private List<Child> children;

    private String dates;

    private String description;

    private List<LocalDate> localDates;

    public void parse(){
        localDates = getLocalDates(dates);
    }

    private List<LocalDate> getLocalDates(String stringOfDates) {
        stringOfDates = stringOfDates.replaceAll("[\"\\[\\]]", "");
        List<String> datesInString = Arrays.asList(stringOfDates.split(","));
        return datesInString.stream()
                .map(s -> LocalDate.parse(s, DateTimeFormatter.ofPattern("MM/dd/yyyy")))
                .collect(Collectors.toList());
    }

    private String getStringDates(List<LocalDate> dates){
        return "";
    }

}
