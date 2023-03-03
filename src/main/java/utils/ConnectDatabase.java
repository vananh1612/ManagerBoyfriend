package utils;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectDatabase {

    // final DB_USER, DB_PASSWORD, DB_HOST, DB_PORT, DB_NAME
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "12345";
    private static final String DB_HOST = "localhost";
    private static final String DB_PORT = "3306";
    private static final String DB_NAME = "quanlynguoiyeu";

    public  static Connection connection;

    public static Connection getConnection() {
       if (connection != null){
           return connection;
       }
       else {
           try {
               connection = connectDB();
               return connection;
           } catch (Exception e) {
               e.printStackTrace();
               return null;
           }
       }
    }

    private static Connection connectDB() {
        try {
            connection = DriverManager.getConnection(
                    "jdbc:mysql://" + DB_HOST + ":" + DB_PORT + "/" + DB_NAME, DB_USER, DB_PASSWORD);
            System.out.println("Connected to database: " + DB_NAME);
            return connection;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Failed to connect to database: " + DB_NAME);
            return null;
        }
    }

    public static void main(String[] args) {
        getConnection();
    }
}
