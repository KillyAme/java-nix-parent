package com.alevel.java.nix.lesson22.module2;

import java.util.LinkedHashSet;
import java.util.List;

public class FirstUniqueElement {
    static String findFirstUnique(List<String> list) {
        LinkedHashSet<String> set = new LinkedHashSet<>();
        LinkedHashSet<String> unique = new LinkedHashSet<>();
        for (String s : list) {
            if (set.add(s)) {
                unique.add(s);
            } else unique.remove(s);
        }
        return unique.stream().findFirst().orElse(null);
    }
}
