package com.alevel.java.nix.lesson33.hometask;

import java.util.Scanner;
import java.util.concurrent.*;

public class Hippodrome {

    private static final int NUMBER_OF_HORSES = 5;

    public static void main(String[] args) {

        System.out.println("Choose your horse from 1 to " + NUMBER_OF_HORSES);
        Scanner scanner = new Scanner(System.in);
        int selectHorse = scanner.nextInt();
        while (true) {
            if (selectHorse < 1 || selectHorse > NUMBER_OF_HORSES) {
                System.out.println("Enter a number from 1 to " + NUMBER_OF_HORSES + ".\nYou have selected " + selectHorse);
                scanner = new Scanner(System.in);
                selectHorse = scanner.nextInt();
                continue;
            }
            break;
        }
        ExecutorService pool = Executors.newFixedThreadPool(NUMBER_OF_HORSES);
        CompletionService<Integer> completionService = new ExecutorCompletionService<>(pool);
        for (int i = 1; i <= NUMBER_OF_HORSES; i++) {
            completionService.submit(new Horse(i));
        }
        pool.shutdown();
        int i = 0;
        int pos = 0;
        while (!pool.isTerminated()) {
            try {
                i++;
                Future<Integer> future = completionService.take();
                System.out.println("Horse number " + future.get() + " has finished");
                if (future.get().equals(selectHorse)) {
                    pos = i;
                }
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Your horse №" + selectHorse + " has arrived " + pos);
    }

}


