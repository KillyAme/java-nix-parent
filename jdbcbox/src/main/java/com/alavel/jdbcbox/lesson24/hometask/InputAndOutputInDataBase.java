package com.alavel.jdbcbox.lesson24.hometask;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.*;


public class InputAndOutputInDataBase {

    private final Map<String, Integer> cityIndexByName = new LinkedHashMap<>();
    private int size;
    private int[][] cityNeighbors;
    private int[][] cityNeighborCosts;
    private List<Vertex> peaks;


    public static void main(String[] args) {
        var object = new InputAndOutputInDataBase();
        object.output();
        object.createVertexes();
        object.insert();
    }

    public void output() {
        Connection con = getConnection();
        try {
            String sql = "SELECT name, id FROM cities.city ORDER BY id";
            try (PreparedStatement getIndexByName = con.prepareStatement(sql)) {
                ResultSet resultSet = getIndexByName.executeQuery();

                while (resultSet.next()) {
                    String name = resultSet.getString(1);
                    int index = resultSet.getInt(2);
                    cityIndexByName.put(name, index);
                }
                size = cityIndexByName.size();

            }
            try (PreparedStatement getCost = con.prepareStatement
                    ("SELECT \"from\", \"to\" ,cost FROM cities.connection WHERE \"from\" = ?",
                            ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)) {

                cityNeighbors = new int[size][];
                cityNeighborCosts = new int[size][];

                for (int i = 1; i <= size; i++) {

                    getCost.setInt(1, i);
                    ResultSet resultSet = getCost.executeQuery();
                    resultSet.last();
                    int countRows = resultSet.getRow();
                    resultSet.first();

                    int[] neighborIndices = cityNeighbors[i - 1] = new int[countRows];
                    int[] neighborCosts = cityNeighborCosts[i - 1] = new int[countRows];

                    for (int j = 0; j < countRows; j++) {
                        neighborIndices[j] = resultSet.getInt(2);
                        neighborCosts[j] = resultSet.getInt(3);
                        resultSet.next();
                    }
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void createVertexes() {
        peaks = new ArrayList<>(size);
        for (Map.Entry<String, Integer> name : cityIndexByName.entrySet()) {
            peaks.add(new Vertex(name.getKey()));
        }
        for (int j = 0; j < peaks.size(); j++) {
            Vertex peak = peaks.get(j);
            for (int i = 0; i < cityNeighbors[j].length; i++) {
                Vertex vertex = peaks.get((cityNeighbors[j][i]) - 1);
                peak.adjacencies.add(new Edge(vertex, cityNeighborCosts[j][i]));
            }
        }
    }

    public void insert() {
        Connection connection = getConnection();
        int minDistance;
        try (PreparedStatement statement = connection.prepareStatement(
                "SELECT * FROM cities.problems;")) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int problemID = resultSet.getInt(1);
                int from = resultSet.getInt(2);
                int to = resultSet.getInt(3);
                Dijkstra.computePaths(peaks.get(from - 1));
                minDistance = peaks.get(to - 1).minDistance;
                try (PreparedStatement preparedStatement = connection.prepareStatement(
                        "INSERT INTO cities.found_routs VALUES (?,?)")) {
                    preparedStatement.setInt(1, problemID);
                    preparedStatement.setInt(2, minDistance);
                    preparedStatement.addBatch();
                    preparedStatement.executeBatch();

                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection() {
        Properties props = loadProperties();
        String url = props.getProperty("url");
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(url, props);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }


    public Properties loadProperties() {

        Properties properties = new Properties();
        try (var input = new FileInputStream("jdbcbox/src/main/resources/com/alavel/jdbcbox/lesson24.hometask/Props.properties")) {
            properties.load(input);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return properties;
    }
}
