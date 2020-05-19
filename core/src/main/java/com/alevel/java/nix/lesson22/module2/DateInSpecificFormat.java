package com.alevel.java.nix.lesson22.module2;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;


public class DateInSpecificFormat {
    public static List<String> filter(List<String> list) {
        DateTimeFormatter[] formats = {
                DateTimeFormatter.ofPattern("yyyy/MM/dd"),
                DateTimeFormatter.ofPattern("dd/MM/yyyy"),
                DateTimeFormatter.ofPattern("MM-dd-yyyy")
        };
        DateTimeFormatter specificFormat = DateTimeFormatter.ofPattern("yyyyMMdd");
        List<String> formatterDate = new ArrayList<>();
        for (String date : list) {
            for (DateTimeFormatter format : formats) {
                try {
                    LocalDate localDate = LocalDate.parse(date, format);
                    formatterDate.add(localDate.format(specificFormat));
                } catch (DateTimeParseException ignored) {}
            }
        }
        return formatterDate;
    }
}
