package com.alavel.jdbcbox.lesson24.hometask;

import java.util.PriorityQueue;

public class Dijkstra {
    public static void computePaths(Vertex source) {
        source.minDistance = 0;
        PriorityQueue<Vertex> vertexPriorityQueue = new PriorityQueue<>();
        vertexPriorityQueue.add(source);

        while (!vertexPriorityQueue.isEmpty()) {
            Vertex u = vertexPriorityQueue.poll();


            for (Edge e : u.adjacencies) {
                Vertex v = e.target;
                int weight = e.weight;
                int distanceThroughU = u.minDistance + weight;
                if (distanceThroughU < v.minDistance) {
                    vertexPriorityQueue.remove(v);

                    v.minDistance = distanceThroughU;
                    v.previous = u;
                    vertexPriorityQueue.add(v);
                }
            }
        }
    }
}