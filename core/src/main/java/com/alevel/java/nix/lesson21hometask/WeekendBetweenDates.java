package com.alevel.java.nix.lesson21hometask;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class WeekendBetweenDates {
    public List<LocalDate> returnWeekendBetweenDates(String start, String end) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyyMMdd");

        LocalDate startDate = LocalDate.parse(start, dateTimeFormatter);
        LocalDate endDate = LocalDate.parse(end, dateTimeFormatter);

        Stream<LocalDate> dates = startDate.datesUntil(endDate.plusDays(1));
        return dates.filter(date ->
                (date.getDayOfWeek() == DayOfWeek.SATURDAY) ||
                        (date.getDayOfWeek() == DayOfWeek.SUNDAY))
                .collect(Collectors.toList());
    }

    public static void main(String[] args) {
        System.out.println("Enter first date in format yyyyMMdd: ");
        String firstDate = "";
        Scanner start = new Scanner(System.in);
        if (start.hasNextLine()) {
            firstDate = start.nextLine();
        }
        start.close();
        System.out.print("Enter second date in format yyyyMMdd: ");
        String secondDate = "";
        Scanner end = new Scanner(System.in);
        if (end.hasNextLine()) {
            secondDate = end.nextLine();
        }
        end.close();

        WeekendBetweenDates weekendBetweenDates = new WeekendBetweenDates();
        List<LocalDate> list = weekendBetweenDates.returnWeekendBetweenDates(firstDate, secondDate);
        for (LocalDate localDate : list) {
            System.out.println(localDate);
        }
    }
}
