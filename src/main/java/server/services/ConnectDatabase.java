package server.services;

import server.configs.Configs;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectDatabase {
    static Connection conn = null;
    static String url = "jdbc:mysql://" + Configs.MSQL_HOST + ":" + Configs.MSQL_PORT + "/" + Configs.MSQL_DATABASE;

    public static Connection getConnection() {
        try {
            if (conn == null || conn.isClosed()) {
                conn = DriverManager.getConnection(url, Configs.MSQL_USER, Configs.MSQL_PASSWORD);
                System.out.println("Connected to database");
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
        return conn;
    }

    static {
        conn = getConnection();
    }

}
