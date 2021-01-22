package pl.com.happyhouse.krzeptow.utils;

import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class LocalTimeConverter {

    public static List<LocalTime> createTimeListFromString(String times){
        return Arrays.stream(times.split(","))
                .mapToInt(Integer::parseInt)
                .mapToObj(i->LocalTime.of(i,0))
                .collect(Collectors.toList());
    }
}
