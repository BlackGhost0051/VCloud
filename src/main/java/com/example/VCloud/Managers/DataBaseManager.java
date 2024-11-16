package com.example.VCloud.Managers;

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
        // check folder
        // check database
    }
}
