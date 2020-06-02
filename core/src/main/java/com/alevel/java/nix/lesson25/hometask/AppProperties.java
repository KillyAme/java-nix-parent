package com.alevel.java.nix.lesson25.hometask;

public class AppProperties {

    @PropertyKey(value = "connection.URL")
    public String connectionURL;

    @PropertyKey(value = "connection.limit")
    public int maxConnections;

    @PropertyKey(value = "connection.established")
    public boolean connectionStatus;
}
