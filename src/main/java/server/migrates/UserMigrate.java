package server.migrates;

import server.services.ConnectDatabase;

import java.io.Serializable;
import java.sql.Connection;


public class UserMigrate {
    Connection connection = ConnectDatabase.getConnection();
    String sql = "CREATE TABLE users (id INTEGER PRIMARY KEY AUTO_INCREMENT, username TEXT, password TEXT, email TEXT, role TEXT)";

    void createTable() {
        try {
            connection.createStatement().execute(sql);
            System.out.println("Table created");
        } catch (Exception e) {
            System.out.println("Error");
            e.printStackTrace();

        }
    }

    public static void main(String[] args) {
        UserMigrate userMigrate = new UserMigrate();
        userMigrate.createTable();
    }
}
