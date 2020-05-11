package com.alevel.java.nix.lesson21hometask;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Game {
    public static void main(String[] args) {
        ArrayList<String> words = new ArrayList<>();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader
                ("C:\\Vlad\\text.txt"))) {

            String word;
            while ((word = bufferedReader.readLine()) != null) {
                words.add(word);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        String word = words.get(new Random().nextInt(words.size() - 1));
        playing(word);
    }

    public static void playing(String word) {
        ArrayList<Character> guessableWordHidden = new ArrayList<>();
        for (int i = 0, length = word.length(); i < length; i++) {
            guessableWordHidden.add('*');
        }
        ArrayList<Character> guessableWordCorrect = new ArrayList<>();
        for (int i = 0, length = word.length(); i < length; i++) {
            guessableWordCorrect.add(word.charAt(i));
        }


        char enteredLetter = '0';
        int countAttempts = 5;

        while (countAttempts > 0) {
            System.out.println(guessableWordHidden);
            System.out.println("Count attempts: " + countAttempts + "\nEnter the char: ");
            enteredLetter = letterInput(enteredLetter);
            ArrayList<Integer> indicesLetter = indicesEnteredLetter(enteredLetter, word);
            if (indicesLetter.size() == 0)
                countAttempts--;
            disclosureLetter(enteredLetter, indicesLetter, guessableWordHidden);

            if (guessableWordHidden.equals(guessableWordCorrect)) {
                System.out.println(guessableWordCorrect + "\n Congratulations!");
                return;
            }
        }
        System.out.println("You lose");
    }

    public static Character letterInput(char enteredLetter) {

        Pattern pattern = Pattern.compile("[абвгдеёжзийклмнопрстуфчцшщъыьэюя]");

        Scanner letter = new Scanner(System.in);
        while (letter.hasNext()) {
            if (letter.hasNext(pattern)) {
                enteredLetter = letter.next().charAt(0);
                break;
            } else {
                System.out.println("invalid character, try again");
                letter = new Scanner(System.in);
            }
        }
        return enteredLetter;
    }

    public static void disclosureLetter(char letter, ArrayList<Integer> indicesLetter, ArrayList<Character> guessableWord) {
        if (indicesLetter.size() > 0) {
            for (Integer index : indicesLetter) {
                guessableWord.remove((int) index);
                guessableWord.add(index, letter);
            }
        }
    }

    public static ArrayList<Integer> indicesEnteredLetter(char letter, String word) {
        ArrayList<Integer> indicesLetterList = new ArrayList<>();
        for (int i = 0, length = word.length(); i < length; i++) {
            if (letter == word.charAt(i)) {
                indicesLetterList.add(i);
            }
        }
        return indicesLetterList;
    }
}
