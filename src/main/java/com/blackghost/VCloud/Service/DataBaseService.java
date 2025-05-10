package com.blackghost.VCloud.Service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.sql.*;

@Service
public class DataBaseService {

    private final Connection connection;

    public DataBaseService(
            @Value("${spring.datasource.url}") String url,
            @Value("${spring.datasource.username}") String user,
            @Value("${spring.datasource.password}") String pass
    ) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            this.connection = DriverManager.getConnection(url, user, pass);
            System.out.println("Connected.");
        } catch (Exception e) {
            throw new RuntimeException("Failed to connect", e);
        }
    }

    void init(){

    }

    void addUser(){}

    void deleteUser(){}

    void updateUser(){}


}
