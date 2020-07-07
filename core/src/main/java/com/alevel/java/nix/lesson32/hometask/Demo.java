package com.alevel.java.nix.lesson32.hometask;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Demo implements Runnable {

    private volatile boolean running = true;

    private volatile StringBuffer input = new StringBuffer();

    private volatile StringBuffer output = new StringBuffer();

    public StringBuffer getInput() {
        return input;
    }

    public void stop() {
        running = false;

    }

    public StringBuffer checkAndOutput(StringBuffer stringBuffer) {

        try (FileWriter fileWriter = new FileWriter("core/src/main/java/com/alevel/java/nix/lesson31/hometask/output.txt")) {
            fileWriter.write(stringBuffer.toString());

        } catch (IOException e) {
            e.printStackTrace();
        }
        return new StringBuffer(stringBuffer.toString());
    }


    @Override
    public synchronized void run() {


        while (running) {
            try {

                if (!input.toString().equals(output.toString())) {
                    output = new StringBuffer(checkAndOutput(input).toString());
                }
                wait(1000);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    public static void main(String[] args) {


        Scanner scanner = new Scanner(System.in);

        Demo run = new Demo();
        new Thread(run).start();

        while (scanner.hasNext()) {
            String string = scanner.nextLine();
            if (string.equals("quit")) {
                run.stop();
                break;
            } else {
                run.getInput().append(string);
            }
            scanner = new Scanner(System.in);
        }
    }
}
