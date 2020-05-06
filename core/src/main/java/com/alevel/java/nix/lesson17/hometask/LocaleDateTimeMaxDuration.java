package com.alevel.java.nix.lesson17.hometask;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;

public class LocaleDateTimeMaxDuration {
    public static void main(String[] args) {
        List<LocalDateTime> list = List.of(LocalDateTime.now(),
                LocalDateTime.of(2020, 5, 12, 6, 1),
                LocalDateTime.of(2020, 6, 6, 12, 0));
        System.out.println(duration(list).toHours());
    }
    public static Duration duration(Collection<LocalDateTime> collection){
        LocalDateTime min = collection.stream().min(Comparator.naturalOrder()).get();
        LocalDateTime max = collection.stream().max(Comparator.naturalOrder()).get();
        return Duration.between(min,max);
    }
}
