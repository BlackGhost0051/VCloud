package com.example.VCloud.Managers;

import org.springframework.jdbc.core.ConnectionCallback;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.*;

public class DataBaseManager {

    private String dataBaseStructure =
            "CREATE TABLE users (\n" +
            "        id INTEGER PRIMARY KEY,\n" +
            "        email TEXT NOT NULL,\n" +
            "        login TEXT NOT NULL UNIQUE,\n" +
            "        password TEXT NOT NULL\n" +
            "    );";

    private String dataBaseName = "database.db";

    private Path resourceFolder = Paths.get("src/main/resources/database");
    private Path databaseFile = resourceFolder.resolve(dataBaseName);


    public DataBaseManager() {
        initDataBase();
    }


    public String registerUser(String login, String email, String password) { // need use int and add logic to error in rooter
        try {
            Connection connection = DriverManager.getConnection("jdbc:sqlite:" + databaseFile.toAbsolutePath());
            String query = "SELECT COUNT(*) FROM users WHERE email = ? OR login = ?";

            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, email);
                preparedStatement.setString(2, login);

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next() && resultSet.getInt(1) > 0) {
                        return "User already exists.";
                    } else {
                        String insertQuery = "INSERT INTO users (email, login, password) VALUES (?, ?, ?)";
                        try (PreparedStatement insertStatement = connection.prepareStatement(insertQuery)) {
                            insertStatement.setString(1, email);
                            insertStatement.setString(2, login);
                            insertStatement.setString(3, password);

                            int rowsInserted = insertStatement.executeUpdate();
                            if (rowsInserted > 0) {
                                return "User successfully registered";
                            } else {
                                return "User not registered";
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
        }
        return login;
    }

    private void initDataBase(){
        try {
            if (Files.notExists(databaseFile)) {
                Connection connection = DriverManager.getConnection("jdbc:sqlite:" + databaseFile.toAbsolutePath());
                Statement statement = connection.createStatement();
                statement.execute(dataBaseStructure);
                System.out.println("Database created and initialized at: " + databaseFile.toAbsolutePath());
            } else {
                System.out.println("Database exists: " + databaseFile.toAbsolutePath());
            }

        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Failed to initialize the database: " + e.getMessage());
        }
    }
}
