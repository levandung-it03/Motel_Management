package com.motel_management.DataAccessObject;
import com.motel_management.Models.CheckOutModel;
import com.motel_management.DB_interaction.DB_connection;
import com.motel_management.Views.Configs;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;

public class CheckOutDAO implements DAOInterface<CheckOutModel> {
    public CheckOutDAO() {}
    public static CheckOutDAO getInstance() {
        return new CheckOutDAO();
    }

    @Override
    public int insert(CheckOutModel obj) {
        Connection myConnection = DB_connection.getMMDBConnection();
        try {
            String query = "INSERT INTO CheckOut VALUES ( ?, ?, ?, ?)";
            PreparedStatement ps = myConnection.prepareStatement(query);
            ps.setString(1, obj.getCheckOutId());
            ps.setString(2, obj.getContractId());
            ps.setDate(3, obj.getCheckOutDate());
            ps.setString(4,obj.getReason());
            return ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DB_connection.closeMMDBConnection(myConnection);
        }
        return 0;
    }

    public int insert(String[] values) {
        Connection myConnection = DB_connection.getMMDBConnection();
        try {
            String query = "INSERT INTO CheckOut VALUES ( ?, ?, ?, ?);";
            PreparedStatement ps = myConnection.prepareStatement(query);
            ps.setString(1, values[0]);
            ps.setString(2, values[1]);
            ps.setDate(3, Date.valueOf(Configs.stringToDate(values[2])));
            ps.setString(4,values[3]);

            return ps.executeUpdate();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(new JPanel(),
                    "The reason is too long ",
                    "Notice",
                    JOptionPane.PLAIN_MESSAGE
            );
            e.printStackTrace();
        } finally {
            DB_connection.closeMMDBConnection(myConnection);
        }
        return 0;
    }

    @Override
    public int delete(String id) {
        Connection myConnection = DB_connection.getMMDBConnection();
        try {
            String query = "DELETE FROM CheckOut WHERE checkOutId=?";
            PreparedStatement ps = myConnection.prepareStatement(query);
            ps.setString(1, id);
            return ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DB_connection.closeMMDBConnection(myConnection);
        }
        return 0;
    }

    @Override
    public int update(CheckOutModel obj) {
        Connection myConnection = DB_connection.getMMDBConnection();
        try {
            String query = "UPDATE CheckOut SET  contractId=?, checkOutDate=?, reason=? WHERE (checkOutId=?);";
            PreparedStatement ps = myConnection.prepareStatement(query);
            ps.setString(1, obj.getContractId());
            ps.setDate(2, obj.getCheckOutDate());
            ps.setString(3,obj.getReason());
            ps.setString(4, obj.getCheckOutId());
            return ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DB_connection.closeMMDBConnection(myConnection);
        }
    }
    // Overload
    public int update(String[] values) {
        Connection myConnection = DB_connection.getMMDBConnection();
        try {
            String query = "UPDATE CheckOut SET  contractId=?, checkOutDate=?, reason=? WHERE (checkOutId=?);";
            PreparedStatement ps = myConnection.prepareStatement(query);
            ps.setString(1, values[1]);
            ps.setDate(2, Date.valueOf(Configs.stringToDate(values[2])));
            ps.setString(3,values[3]);
            ps.setString(4, values[0]);
            return ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DB_connection.closeMMDBConnection(myConnection);
        }
    }

    @Override
    public CheckOutModel selectById(String id) {
        Connection myConnection = DB_connection.getMMDBConnection();
        try {
            String query = ("SELECT * FROM CheckOut WHERE (checkOutId=?)");
            PreparedStatement ps = myConnection.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            rs.next();
            return new CheckOutModel(rs.getString("checkOutId"), rs.getString("contractId"),
                     rs.getDate("checkOutDate"),rs.getString("reason"));
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DB_connection.closeMMDBConnection(myConnection);
        }
        return null;
    }

    @Override
    public ArrayList<CheckOutModel> selectAll() {
        Connection myConnection = DB_connection.getMMDBConnection();
        try {
            PreparedStatement ps = myConnection.prepareStatement("SELECT * FROM CheckOut");
            ArrayList<CheckOutModel> result = new ArrayList<>();
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                result.add(new CheckOutModel(rs.getString("checkOutId"), rs.getString("contractId"),
                        rs.getDate("checkOutDate"),rs.getString("reason")));
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
    public ArrayList<CheckOutModel> selectByCondition(String condition) {
        Connection myConnection = DB_connection.getMMDBConnection();
        try {
            PreparedStatement ps = myConnection.prepareStatement("SELECT * FROM CheckOut " + condition);
            ArrayList<CheckOutModel> result = new ArrayList<>();
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                result.add(new CheckOutModel(rs.getString("checkOutId"), rs.getString("contractId"),
                        rs.getDate("checkOutDate"),rs.getString("reason")));
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
