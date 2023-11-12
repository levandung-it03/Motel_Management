package com.motel_management.DB_interaction;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DB_connection {
    public static Connection getMMDBConnection() {
        Connection myConnection = null;
        final String dbURL = "jdbc:mysql://localhost:3306/MOTEL_MANAGEMENT";
        final String username = "root";
        final String password = "";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            myConnection = DriverManager.getConnection(dbURL, username, password);
        } catch (ClassNotFoundException e) {
            System.out.println("Can not find com.mysql.cj.jdbc.Driver or you haven't installed Driver yet!");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("Some Problems Occur With Your Server!");
            e.printStackTrace();
        }

        if (myConnection != null) { System.out.println("Connect to DB successfully!"); }
        else { System.out.println("Failed to connect to DB!"); }
        return myConnection;
    }

    public static void closeMMDBConnection(Connection myConnection) {
            try {
                if (myConnection != null) {
                    myConnection.close();
                }
            } catch (SQLException e) {
                System.out.println("Failed to close DB");
                e.printStackTrace();
            }
    }
}

