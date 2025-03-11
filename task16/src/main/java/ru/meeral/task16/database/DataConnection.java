package ru.meeral.task16.database;

public class DataConnection {
    public static final String URL = System.getenv("DATASOURCE_URL");
    public static final String USERNAME = System.getenv("DATASOURCE_USERNAME");
    public static final  String PASSWORD = System.getenv("DATASOURCE_PASSWORD");

}
