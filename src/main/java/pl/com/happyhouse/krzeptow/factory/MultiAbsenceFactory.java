package pl.com.happyhouse.krzeptow.factory;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.com.happyhouse.krzeptow.dto.AbsenceDTO;
import pl.com.happyhouse.krzeptow.model.Absence;
import pl.com.happyhouse.krzeptow.model.Child;
import pl.com.happyhouse.krzeptow.model.User;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class MultiAbsenceFactory {

    public List<Absence> create(AbsenceDTO absenceDTO, User reporter) {
        List<Absence> result = new ArrayList<>();
        for (Child child : absenceDTO.getChildren()) {
            for (LocalDate date : getDates(absenceDTO.getDates())) {
                Absence absence = Absence.builder()
                        .date(date)
                        .child(child)
                        .description(absenceDTO.getDescription())
                        .reporter(reporter)
                        .build();
                result.add(absence);
            }
        }
        return result;
    }

    public List<LocalDate> getDates(String stringOfDates) {
        stringOfDates = stringOfDates.replaceAll("[\"\\[\\]]", "");
        List<String> datesInString = Arrays.asList(stringOfDates.split(","));
        return datesInString.stream()
                .map(s -> LocalDate.parse(s, DateTimeFormatter.ofPattern("MM/dd/yyyy")))
                .collect(Collectors.toList());
    }


}
