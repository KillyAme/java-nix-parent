package com.alevel.java.nix.module1;

public class TriangleArea {
    private int xCoordinatePointA;
    private int yCoordinatePointA;
    private int xCoordinatePointB;
    private int yCoordinatePointB;
    private int xCoordinatePointC;
    private int yCoordinatePointC;

    public TriangleArea(int xCoordinatePointA, int yCoordinatePointA,
                        int xCoordinatePointB, int yCoordinatePointB,
                        int xCoordinatePointC, int yCoordinatePointC) {
        this.xCoordinatePointA = xCoordinatePointA;
        this.yCoordinatePointA = yCoordinatePointA;
        this.xCoordinatePointB = xCoordinatePointB;
        this.yCoordinatePointB = yCoordinatePointB;
        this.xCoordinatePointC = xCoordinatePointC;
        this.yCoordinatePointC = yCoordinatePointC;
    }

    public double areaOfTriangle() {
        double result = Math.abs(0.5
                * ((xCoordinatePointA - xCoordinatePointC)
                * (yCoordinatePointB - yCoordinatePointC)
                - (yCoordinatePointA - yCoordinatePointC)
                * (xCoordinatePointB - xCoordinatePointC)));
        return result;
    }

}