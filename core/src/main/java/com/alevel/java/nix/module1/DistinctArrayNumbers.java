package com.alevel.java.nix.module1;

import java.util.HashSet;

public class DistinctArrayNumbers {
    public static int countUniqueNumbers(int[] array) {
        HashSet uniqueElements = new HashSet();
        for (int value : array) {
            uniqueElements.add(value);
        }
        return uniqueElements.size();
    }
}
