package com.alevel.java.nix.lesson20.hometask;

import java.io.*;
import java.util.TreeMap;


public class CountCharsInFile {
    public static void main(String[] args) {
        try (BufferedReader file = new BufferedReader(new FileReader("/text.txt"))) {
            String text;
            StringBuilder stringBuilder = new StringBuilder();
            TreeMap<Character, Integer> map = new TreeMap<>();
            while ((text = file.readLine()) != null) {
                stringBuilder.append(text);
            }
            text = stringBuilder.toString();
            for (int i = 0, length = text.length(); i < length; i++) {
                char key = text.charAt(i);
                int value = (int) (text.chars().filter(ch -> ch == key).count());
                map.put(key, value);
            }
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }

    }
}
