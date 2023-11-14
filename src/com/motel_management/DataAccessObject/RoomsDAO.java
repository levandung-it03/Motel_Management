package com.motel_management.DataAccessObject;
import com.motel_management.Models.Account;
import com.motel_management.Models.Room;
import com.motel_management.DB_interaction.DB_connection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class RoomsDAO {
    public RoomsDAO() {}
    public static RoomsDAO getInstance() {
        return new RoomsDAO();
    }


    @Override
    public int insert(Room obj) {
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
    public int delete(Account obj) {
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
    public int update(Account obj) {
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
    public Account selectById(String id) {
        Connection myConnection = DB_connection.getMMDBConnection();
        try {
            String query = ("SELECT * FROM Account WHERE (userId=?)");
            PreparedStatement ps = myConnection.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
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
            PreparedStatement ps = myConnection.prepareStatement("SELECT * FROM Account");
            ArrayList<Account> result = new ArrayList<>();
            ResultSet rs = ps.executeQuery();
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
            PreparedStatement ps = myConnection.prepareStatement("SELECT * FROM Account " + condition);
            ArrayList<Account> result = new ArrayList<>();
            ResultSet rs = ps.executeQuery();
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
