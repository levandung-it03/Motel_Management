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
            e.fillInStackTrace();
        } finally {
            DB_connection.closeMMDBConnection(myConnection);
        }
        return 0;
    }
    public int insert(String[] values) {
        Connection myConnection = DB_connection.getMMDBConnection();
        try {
            String query = "INSERT INTO Account VALUES (?, ?, ?, ?)";
            PreparedStatement ps = myConnection.prepareStatement(query);
            ps.setString(1, values[0]);
            ps.setString(2, values[1]);
            ps.setString(3, values[2]);
            ps.setString(4, values[3]);
            return ps.executeUpdate();
        } catch (SQLException e) {
            e.fillInStackTrace();
        } finally {
            DB_connection.closeMMDBConnection(myConnection);
        }
        return 0;
    }

    @Override
    public int delete(String id) {
        Connection myConnection = DB_connection.getMMDBConnection();
        try {
            String query = "DELETE FROM Account WHERE userId=?";
            PreparedStatement ps = myConnection.prepareStatement(query);
            ps.setString(1, id);
            return ps.executeUpdate();
        } catch (SQLException e) {
            e.fillInStackTrace();
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
            e.fillInStackTrace();
        } finally {
            DB_connection.closeMMDBConnection(myConnection);
        }
        return 0;
    }
    // OverLOAD
    public int update(String[] values) {
        Connection myConnection = DB_connection.getMMDBConnection();
        try {
            String query = "UPDATE Account SET  name=?, username=?, password=? WHERE (userId=?);";
            PreparedStatement ps = myConnection.prepareStatement(query);
            ps.setString(1, values[1]);
            ps.setString(2, values[2]);
            ps.setString(3, values[3]);
            ps.setString(4, values[0]);
            return ps.executeUpdate();
        } catch (SQLException e) {
            e.fillInStackTrace();
        } finally {
            DB_connection.closeMMDBConnection(myConnection);
        }
        return 0;
    }

    @Override
    public AccountModel selectById(String id) {
        Connection myConnection = DB_connection.getMMDBConnection();
        try {
            String query = ("SELECT * FROM Account WHERE (userId=?)");
            PreparedStatement ps = myConnection.prepareStatement(query);
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new AccountModel(rs.getString("userId"), rs.getString("name"),
                        rs.getString("username"), rs.getString("password"));
            } else {
                return null;
            }
        } catch (SQLException e) {
            e.fillInStackTrace();
        } finally {
            DB_connection.closeMMDBConnection(myConnection);
        }
        return null;
    }
    public AccountModel selectByUsername(String username) {
        Connection myConnection = DB_connection.getMMDBConnection();
        try {
            String query = ("SELECT * FROM Account WHERE (username=?)");
            PreparedStatement ps = myConnection.prepareStatement(query);
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new AccountModel(rs.getString("userId"), rs.getString("name"),
                        rs.getString("username"), rs.getString("password"));
            } else {
                return null;
            }
        } catch (SQLException e) {
            e.fillInStackTrace();
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
            e.fillInStackTrace();
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
            e.fillInStackTrace();
        } finally {
            DB_connection.closeMMDBConnection(myConnection);
        }
        return null;
    }
}
