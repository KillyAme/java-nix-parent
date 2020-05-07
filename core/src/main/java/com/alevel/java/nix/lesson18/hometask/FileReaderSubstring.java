package com.alevel.java.nix.lesson18.hometask;

import java.io.*;

public class FileReaderSubstring {
    public static void main(String[] args) {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("/ReadFile.txt"))) {
            String substring = "123";
            String line;
            StringBuilder stringBuilder = new StringBuilder();
            while ((line = bufferedReader.readLine()) != null) {
                if (line.contains(substring))
                    stringBuilder.append(line).append(System.lineSeparator());
                System.out.println(stringBuilder);
            }
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }
}
