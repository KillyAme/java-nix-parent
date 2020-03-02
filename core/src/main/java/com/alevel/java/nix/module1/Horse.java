package com.alevel.java.nix.module1;

public class Horse {
    private int row;
    private int column;

    public Horse(int row, int column) {
        this.row = row;
        this.column = column;
    }

    public boolean canMove(int row, int column) {
        int rowMove = Math.abs(this.row - row);
        int columnMove = Math.abs(this.column - column);
        return (rowMove == 1 && columnMove == 2) || (rowMove == 2 && columnMove == 1);
    }
}
