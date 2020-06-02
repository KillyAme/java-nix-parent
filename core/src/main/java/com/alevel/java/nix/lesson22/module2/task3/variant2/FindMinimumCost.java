package com.alevel.java.nix.lesson22.module2.task3.variant2;


import java.nio.file.Path;



public class FindMinimumCost {

    public static void main(String[] args) {
        Path input = Path.of("input.txt");
        Path output = Path.of("output.txt");
        InputAndOutput inputAndOutput = new InputAndOutput();
        inputAndOutput.input(input);
        inputAndOutput.createVertexes();
        inputAndOutput.output(output);
    }
}