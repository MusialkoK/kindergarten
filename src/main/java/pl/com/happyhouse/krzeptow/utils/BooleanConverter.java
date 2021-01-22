package pl.com.happyhouse.krzeptow.utils;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class BooleanConverter {

    public static List<Boolean> createBooleanListFromString(String booleans){
        return Arrays.stream(booleans.split(","))
                .map(Boolean::valueOf)
                .collect(Collectors.toList());
    }
}
