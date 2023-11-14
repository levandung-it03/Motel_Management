package com.motel_management.DataAccessObject;
import com.motel_management.Models.AccountModel;
import com.motel_management.DB_interaction.DB_connection;

import java.sql.*;
import java.util.ArrayList;

public class AccountDAO implements DAOInterface<AccountModel> {
    // Fixing, Waiting for me
    public AccountDAO() { }
    public static AccountDAO getInstance() {
        return new AccountDAO();
    }

    @Override
    public int insert(AccountModel obj) {
        Connection myConnection = DB_connection.getMMDBConnection();
        try {
            String query = "INSERT INTO Account VALUES (?, ?, ?, ?)";
            PreparedStatement ps = myConnection.prepareStatement(query);
            ps.setString(1, obj.getUserId());
            ps.setString(2, obj.getName());
            ps.setString(3, obj.getUsername());
            ps.setString(4, obj.getPassword());
            return ps.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DB_connection.closeMMDBConnection(myConnection);
        }
        return 0;
    }

    @Override
    public int delete(AccountModel obj) {
        Connection myConnection = DB_connection.getMMDBConnection();
        try {
            String query = "DELETE FROM Account WHERE userId=?";
            PreparedStatement ps = myConnection.prepareStatement(query);
            ps.setString(1, obj.getUserId());
            return ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DB_connection.closeMMDBConnection(myConnection);
        }
        return 0;
    }

    @Override
    public int update(AccountModel obj) {
        Connection myConnection = DB_connection.getMMDBConnection();
        try {
            String query = "UPDATE Account SET  name=?, username=?, password=? WHERE (userId=?);";
            PreparedStatement ps = myConnection.prepareStatement(query);
            ps.setString(1, obj.getName());
            ps.setString(2, obj.getUsername());
            ps.setString(3, obj.getPassword());
            ps.setString(4, obj.getUserId());
            return ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DB_connection.closeMMDBConnection(myConnection);
        }
    }

    @Override
    public AccountModel selectById(String id) {
        Connection myConnection = DB_connection.getMMDBConnection();
        try {
            String query = ("SELECT * FROM Account WHERE (userId=?)");
            PreparedStatement ps = myConnection.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            rs.next();
            return new AccountModel(rs.getString("userId"), rs.getString("name"),
                    rs.getString("username"), rs.getString("password"));
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DB_connection.closeMMDBConnection(myConnection);
        }
        return null;
    }

    @Override
    public ArrayList<AccountModel> selectAll() {
        Connection myConnection = DB_connection.getMMDBConnection();
        try {
            PreparedStatement ps = myConnection.prepareStatement("SELECT * FROM Account");
            ArrayList<AccountModel> result = new ArrayList<>();
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                result.add(new AccountModel(rs.getString("userId"), rs.getString("name"),
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
    public ArrayList<AccountModel> selectByCondition(String condition) {
        Connection myConnection = DB_connection.getMMDBConnection();
        try {
            PreparedStatement ps = myConnection.prepareStatement("SELECT * FROM Account " + condition);
            ArrayList<AccountModel> result = new ArrayList<>();
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                result.add(new AccountModel(rs.getString("userId"), rs.getString("name"),
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
