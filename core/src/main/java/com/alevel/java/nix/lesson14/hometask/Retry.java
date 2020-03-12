package com.alevel.java.nix.lesson14.hometask;

public class Retry implements Block {
    @Override
    public void run() throws Exception {
        int[] array = new int[10];
        int random = (int) (Math.random() * 99);
        array[random] = 1;
    }

    public static void numberOfRepetitions(int count) throws Exception {
        Retry retry = new Retry();
        long wait = 100;
        for (int i = 1; i <= count; i++) {
            try {
                retry.run();
                return;
            } catch (Exception e) {
                System.out.println("Catch: " + e);
                Thread.sleep(wait * i);
            }
            if (i == count) {
                throw new Exception();
            }
        }
    }

    public static void main(String[] args) {
        try {
            numberOfRepetitions(10);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
