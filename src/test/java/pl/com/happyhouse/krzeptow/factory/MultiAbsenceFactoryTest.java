package pl.com.happyhouse.krzeptow.factory;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;



public class MultiAbsenceFactoryTest {

    MultiAbsenceFactory multiAbsenceFactory = new MultiAbsenceFactory();

    @Test
    void isDateParsingCorrect() {

        //given
        String inputString = "[\"01/01/2021\",\"01/08/2021\"]";
        List<LocalDate> result = List.of(LocalDate.of(2021, 1, 1), LocalDate.of(2021, 1, 8));

        //when
        List<LocalDate> testedList = multiAbsenceFactory.getDates(inputString);

        //then
        assertEquals(testedList, result);
    }


}
