package com.alevel.java.nix.module1;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ValidStringTest {

    @Test
    void isValidString() {
        String string = "{([])}";
        assertEquals(ValidString.isValidString(string),true);
        String string2 = "{([))}";
        assertEquals(ValidString.isValidString(string2),false);
    }
}