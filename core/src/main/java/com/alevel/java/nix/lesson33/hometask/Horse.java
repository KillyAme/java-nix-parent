package com.alevel.java.nix.lesson33.hometask;


import java.util.Random;
import java.util.concurrent.Callable;

public class Horse implements Callable<Integer> {

    private final Integer numberHorse;

    private final Random random;

    public Horse(Integer numberHorse) {
        this.numberHorse = numberHorse;
        this.random = new Random();
    }

    @Override
    public Integer call() {
        int distanceRace = 1000;
        while (distanceRace > 0) {
            try {
                distanceRace = distanceRace - (random.nextInt(100) + 100);
                Thread.sleep(random.nextInt(100) + 400);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return numberHorse;
    }
}
