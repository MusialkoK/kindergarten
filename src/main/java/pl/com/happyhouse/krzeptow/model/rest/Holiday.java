package pl.com.happyhouse.krzeptow.model.rest;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;


@Builder
@Data
public class Holiday {

    private LocalDate date;
    private String localName;

    @Override
    public String toString() {
        return  date + " " + localName;
    }
}
