package com.alevel.java.nix.module1;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DistinctArrayNumbersTest {

    @Test
    void countUniqueNumbers() {
        int[] array = {1, 3, 1, 1, 5, 6, 5, 7, 7, 9};
        assertEquals(DistinctArrayNumbers.countUniqueNumbers(array), 6);
    }
}