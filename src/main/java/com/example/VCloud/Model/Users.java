package com.example.VCloud.Model;

import org.springframework.stereotype.Component;

import javax.swing.plaf.basic.BasicDesktopIconUI;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@Component
public class Users {
    public List<User> getAllUsers() {
        List<User> userList = new ArrayList<>();
        try {
            Class.forName("org.sqlite.JDBC");
            String url = "jdbc:sqlite:/src/main/resources/database/base.db";

            Connection connection = DriverManager.getConnection(url);

            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM users");

            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong("id"));
                user.setLogin(resultSet.getString("login"));
                user.setPassword(resultSet.getString("password"));
                userList.add(user);
            }

            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return userList;
    }
}
