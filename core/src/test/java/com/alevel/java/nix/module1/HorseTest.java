package com.alevel.java.nix.module1;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HorseTest {

    @Test
    void canMove() {
        Horse horse = new Horse(3, 5);
        assertEquals(horse.canMove(5, 6), true);
        assertEquals(horse.canMove(4, 6), false);
    }
}