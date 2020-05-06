package com.alevel.java.nix.lesson16.hometask;


import java.util.Collection;
import java.util.List;
import java.util.OptionalInt;



public class StringFilter {
    public static void main(String[] args) {
        List<String> list = List.of("string 1 text", "2 string 3", "45");
        System.out.println(stringFilter(list));
    }
    public static OptionalInt stringFilter(Collection<String> collection){
        return collection.stream()
                .flatMapToInt(String::codePoints)
                .filter(Character::isDigit)
                .map(chars -> Character.digit(chars, 10))
                .reduce((res, n) -> res * 10 + n);
    }
}
