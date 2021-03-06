package com.alevel.java.nix.lesson22.module2.task3.variant2;

import java.util.PriorityQueue;

public class Dijkstra {
    public static void computePaths(Vertex source) {
        source.minDistance = 0;
        PriorityQueue<Vertex> vertexQueue = new PriorityQueue<>();
        vertexQueue.add(source);

        while (!vertexQueue.isEmpty()) {
            Vertex u = vertexQueue.poll();


            for (Edge e : u.adjacencies) {
                Vertex v = e.target;
                int weight = e.weight;
                int distanceThroughU = u.minDistance + weight;
                if (distanceThroughU < v.minDistance) {
                    vertexQueue.remove(v);

                    v.minDistance = distanceThroughU;
                    v.previous = u;
                    vertexQueue.add(v);
                }
            }
        }
    }
}