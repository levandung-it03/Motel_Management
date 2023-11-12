package com.motel_management.DataAccessObject;
import com.motel_management.Models.Account;
import com.motel_management.DB_interaction.DB_connection;

import java.sql.*;
import java.util.ArrayList;

public class AccountDAO implements DAOInterface<Account> {

    public AccountDAO() { }
    public static AccountDAO getInstance() {
        return new AccountDAO();
    }

    @Override
    public int insert(Account obj) {
        Connection myConnection = DB_connection.getMMDBConnection();
        try {
            Statement st = myConnection.createStatement();
            String query = "INSERT INTO Account VALUES ("
                    + "\"" + obj.getUserId() + "\", "
                    + "\"" + obj.getName() + "\", "
                    + "\"" + obj.getUsername() + "\", "
                    + "\"" + obj.getPassword() + "\")";
            return st.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DB_connection.closeMMDBConnection(myConnection);
        }
        return 0;
    }

    @Override
    public int delete(Account obj) {
        Connection myConnection = DB_connection.getMMDBConnection();
        try {
            Statement st = myConnection.createStatement();
            return st.executeUpdate("DELETE FROM Account WHERE userId=\"" + obj.getUserId() + "\"");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DB_connection.closeMMDBConnection(myConnection);
        }
        return 0;
    }

    @Override
    public int update(Account obj) {
        Connection myConnection = DB_connection.getMMDBConnection();
        try {
            Statement st = myConnection.createStatement();
            String query = "UPDATE Account SET "
                    + "name=\"" + obj.getName() + "\", "
                    + "username=\"" + obj.getUsername() + "\", "
                    + "password=\"" + obj.getPassword() + "\" "
                    + "WHERE (userId=\"" + obj.getUserId() +"\");";
            System.out.println(query);
            return st.executeUpdate(query);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DB_connection.closeMMDBConnection(myConnection);
        }
    }

    @Override
    public Account selectById(String id) {
        Connection myConnection = DB_connection.getMMDBConnection();
        try {
            Statement st = myConnection.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM Account WHERE (userId=\"" + id + "\")");
            rs.next();
            return new Account(rs.getString("userId"), rs.getString("name"),
                    rs.getString("username"), rs.getString("password"));
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DB_connection.closeMMDBConnection(myConnection);
        }
        return null;
    }

    @Override
    public ArrayList<Account> selectAll() {
        Connection myConnection = DB_connection.getMMDBConnection();
        try {
            Statement st = myConnection.createStatement();
            ArrayList<Account> result = new ArrayList<>();
            ResultSet rs = st.executeQuery("SELECT * FROM Account");
            while (rs.next()) {
                result.add(new Account(rs.getString("userId"), rs.getString("name"),
                        rs.getString("username"), rs.getString("password")));
            }
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DB_connection.closeMMDBConnection(myConnection);
        }
        return null;
    }

    @Override
    public ArrayList<Account> selectByCondition(String condition) {
        Connection myConnection = DB_connection.getMMDBConnection();
        try {
            Statement st = myConnection.createStatement();
            ArrayList<Account> result = new ArrayList<>();
            ResultSet rs = st.executeQuery("SELECT * FROM Account " + condition);
            while (rs.next()) {
                result.add(new Account(rs.getString("userId"), rs.getString("name"),
                        rs.getString("username"), rs.getString("password")));
            }
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DB_connection.closeMMDBConnection(myConnection);
        }
        return null;
    }
}
