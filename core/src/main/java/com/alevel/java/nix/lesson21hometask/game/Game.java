package com.alevel.java.nix.lesson21hometask.game;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Game {
    private String word;
    private Character enteredLetter;
    private final List<Character> guessableWordHidden = new ArrayList<>();
    private final List<Character> guessableWordCorrect = new ArrayList<>();

    public void setGuessableWordHidden() {
        for (int i = 0, length = word.length(); i < length; i++) {
            guessableWordHidden.add('*');
        }
    }

    public void setGuessableWordCorrect() {
        for (int i = 0, length = word.length(); i < length; i++) {
            guessableWordCorrect.add(word.charAt(i));
        }
    }


    public void setWord() {
        List<String> words = new ArrayList<>();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader
                ("C:\\Vlad\\text.txt"))) {

            String word;
            while ((word = bufferedReader.readLine()) != null) {
                words.add(word);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        word = words.get(new Random().nextInt(words.size() - 1));
    }

    public Character setEnteredLetter() {
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

    public List<Integer> getIndicesLetter() {
        ArrayList<Integer> indicesLetterList = new ArrayList<>();
        for (int i = 0, length = word.length(); i < length; i++) {
            if (enteredLetter == word.charAt(i)) {
                indicesLetterList.add(i);
            }
        }
        return indicesLetterList;
    }

    public void disclosureLetter(List<Integer> indicesLetter) {
        if (indicesLetter.size() > 0) {
            for (Integer index : indicesLetter) {
                guessableWordHidden.remove((int) index);
                guessableWordHidden.add(index, enteredLetter);
            }
        }
    }

    public void playing() {
        setWord();

        setGuessableWordCorrect();
        setGuessableWordHidden();

        int countAttempts = 5;

        while (countAttempts > 0) {
            System.out.println(guessableWordHidden);
            System.out.println("Count attempts: " + countAttempts + "\nEnter the char: ");
            enteredLetter = setEnteredLetter();
            List<Integer> indicesLetter = getIndicesLetter();
            if (indicesLetter.size() == 0)
                countAttempts--;
            disclosureLetter(indicesLetter);

            if (guessableWordHidden.equals(guessableWordCorrect)) {
                System.out.println(guessableWordCorrect + "\n Congratulations!");
                return;
            }
        }
        System.out.println("You lose");
    }

    public static void main(String[] args) {
        Game game = new Game();
        game.playing();
    }
}
