package pl.com.happyhouse.krzeptow.model.rest;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class HolidayRest {

    private String date;
    private String localName;

}
