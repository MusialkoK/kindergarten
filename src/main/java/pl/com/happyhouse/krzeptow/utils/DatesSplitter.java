package pl.com.happyhouse.krzeptow.utils;


import lombok.Getter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


public class DatesSplitter {

    @Getter
    private static List<LocalDate> datesToMakePresent;
    @Getter
    private static List<LocalDate> datesToMakeAbsent;

    public static void createDates(List<LocalDate> form, List<LocalDate> database) {
        datesToMakeAbsent = database;
        datesToMakePresent = form;
        List<LocalDate> common = new ArrayList<>(form);
        common.retainAll(database);
        datesToMakeAbsent.removeAll(common);
        datesToMakePresent.removeAll(common);
        System.out.println("aaa");
    }
}
