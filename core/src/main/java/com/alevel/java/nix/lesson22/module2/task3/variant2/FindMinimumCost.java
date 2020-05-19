package com.alevel.java.nix.lesson22.module2.task3.variant2;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.util.*;


public class FindMinimumCost {

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(Path.of("C:\\Vlad\\input.txt"))) {
            int size = scanner.nextInt();
            scanner.nextLine();
            LinkedHashMap<String, Integer> cityIndexByName = new LinkedHashMap<>(size);
            int[][] cityNeighbors = new int[size][];
            int[][] cityNeighborCosts = new int[size][];

            for (int cityIndex = 1; cityIndex <= size; cityIndex++) {
                String name = scanner.nextLine();
                cityIndexByName.put(name, cityIndex);
                int countNeighbors = scanner.nextInt();


                int[] neighborIndices = cityNeighbors[cityIndex - 1] = new int[countNeighbors];
                int[] neighborCosts = cityNeighborCosts[cityIndex - 1] = new int[countNeighbors];

                for (int i = 0; i < countNeighbors; i++) {
                    neighborIndices[i] = scanner.nextInt();
                    neighborCosts[i] = scanner.nextInt();
                    scanner.nextLine();
                }
            }
            String start;
            String end;
            int countWays = scanner.nextInt();
            scanner.nextLine();
            LinkedHashMap<String, String> pathDirectionName = new LinkedHashMap<>(countWays);
            for (int i = 0; i < countWays; i++) {
                start = scanner.next();
                end = scanner.next();
                pathDirectionName.put(start, end);
                scanner.nextLine();
            }
            ArrayList<Vertex> peaks = new ArrayList<>(size);
            for (Map.Entry<String, Integer> name : cityIndexByName.entrySet()) {
                peaks.add(new Vertex(name.getKey()));
            }
            for (int j = 0;j<peaks.size(); j++) {
                Vertex peak = peaks.get(j);
                for (int i = 0; i < cityNeighbors[j].length; i++) {
                    Vertex vertex = peaks.get((cityNeighbors[j][i]) - 1);
                    peak.adjacencies.add(new Edge(vertex, cityNeighborCosts[j][i]));

                }
            }

            String startingPoint;
            String endPoint;
            int minDistance;
            var set = pathDirectionName.entrySet();
            try (FileWriter fileWriter = new FileWriter("C:\\Vlad\\output.txt")) {
            for (var way : set) {
                startingPoint = way.getKey();
                endPoint = way.getValue();
                Dijkstra.computePaths(peaks.get(cityIndexByName.get(startingPoint) - 1));
                minDistance = peaks.get(cityIndexByName.get(endPoint) - 1).minDistance;
                fileWriter.write(minDistance +"\n");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}