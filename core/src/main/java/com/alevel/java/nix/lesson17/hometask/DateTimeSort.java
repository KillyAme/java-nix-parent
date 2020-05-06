package com.alevel.java.nix.lesson17.hometask;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;
import java.util.stream.Collectors;

public class DateTimeSort {
    public static void main(String[] args) {
        List<LocalDateTime> list = List.of(LocalDateTime.now(),
                LocalDateTime.of(2020, 5, 12, 6, 1),
                LocalDateTime.of(2020, 6, 6, 12, 0));
        TreeMap<LocalDate, TreeSet<LocalTime>> sortedTime = sort(list);
    }

    public static TreeMap<LocalDate, TreeSet<LocalTime>> sort(Collection<LocalDateTime> collection) {
        return collection.stream().collect(Collectors.groupingBy(
                LocalDateTime::toLocalDate, TreeMap::new,
                Collectors.mapping(LocalDateTime::toLocalTime,
                        Collectors.toCollection(TreeSet::new))));
    }
}