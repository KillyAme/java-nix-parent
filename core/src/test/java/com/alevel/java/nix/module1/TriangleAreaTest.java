package com.alevel.java.nix.module1;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TriangleAreaTest {

    @Test
    void areaOfTriangle() {
        TriangleArea triangleArea = new TriangleArea(-1, 1, -2, 5, -5, 3);
        assertEquals(triangleArea.areaOfTriangle(),7.0);
    }
}