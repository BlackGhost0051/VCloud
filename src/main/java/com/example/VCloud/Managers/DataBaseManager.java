package com.example.VCloud.Managers;

import org.springframework.jdbc.core.ConnectionCallback;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class DataBaseManager {

    private String dataBaseStructure =
            "CREATE TABLE users (\n" +
            "        id INTEGER PRIMARY KEY,\n" +
            "        email TEXT NOT NULL,\n" +
            "        login TEXT NOT NULL UNIQUE,\n" +
            "        password TEXT NOT NULL\n" +
            "    );";

    // dir name = login + loginHash ???

    private String dataBaseName = "database.db";


    public DataBaseManager() {
        initDataBase();
    }


    public boolean registerUser(String login, String email,String password) {


        return false;
    }

    private void initDataBase(){
        try {
            Path resourceFolder = Paths.get("src/main/resources/database");
            Path databaseFile = resourceFolder.resolve(dataBaseName);

            if (Files.notExists(databaseFile)) {
                try (Connection connection = DriverManager.getConnection("jdbc:sqlite:" + databaseFile.toAbsolutePath()); Statement statement = connection.createStatement()) {
                    statement.execute(dataBaseStructure);
                    System.out.println("Database created and initialized at: " + databaseFile.toAbsolutePath());
                }
            } else {
                System.out.println("Database already exists: " + databaseFile.toAbsolutePath());
            }

        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Failed to initialize the database: " + e.getMessage());
        }
        // check folder
        // check database
    }
}
