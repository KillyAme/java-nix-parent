package com.alavel.jdbcbox.lesson24.hometask;

import java.util.ArrayList;

class Vertex implements Comparable<Vertex> {
    public final String name;
    public ArrayList<Edge> adjacencies = new ArrayList<>();
    public Integer minDistance = Integer.MAX_VALUE;
    public Vertex previous;

    public Vertex(String argName) {
        name = argName;
    }

    public String toString() {
        return name;
    }

    public int compareTo(Vertex other) {
        return Integer.compare(minDistance, other.minDistance);
    }

}


class Edge {
    public final Vertex target;
    public final Integer weight;

    public Edge(Vertex argTarget, Integer argWeight) {
        target = argTarget;
        weight = argWeight;
    }

    @Override
    public String toString() {
        return "Edge{" +
                "target=" + target +
                ", weight=" + weight +
                '}';
    }
}