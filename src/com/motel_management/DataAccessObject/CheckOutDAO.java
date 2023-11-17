package com.motel_management.DataAccessObject;
import com.motel_management.Models.CheckOutModel;
import com.motel_management.DB_interaction.DB_connection;

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
            String query = "INSERT INTO CheckOut VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = myConnection.prepareStatement(query);
            ps.setString(1, obj.getCheckOutId());
            ps.setString(2, obj.getContractId());
            ps.setString(3, obj.getRoomId());
            ps.setString(4, obj.getPersonId());
            ps.setString(5, obj.getIdentifier());
            ps.setString(6, obj.getLastName());
            ps.setString(7, obj.getFirstname());
            ps.setString(8, obj.getPhone());
            ps.setDate(9, obj.getCheckOutDate());
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
            String query = "INSERT INTO CheckOut VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?);";
            PreparedStatement ps = myConnection.prepareStatement(query);
            ps.setString(1, values[0]);
            ps.setString(2, values[1]);
            ps.setString(3, values[2]);
            ps.setString(4, values[3]);
            ps.setString(5, values[4]);
            ps.setString(6, values[5]);
            ps.setString(7, values[6]);
            ps.setString(8, values[7]);
            ps.setDate(9, Date.valueOf(values[8]));
            System.out.println(ps);
            return ps.executeUpdate();
        } catch (SQLException e) {
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
            String query = "UPDATE CheckOut SET  contractId=?, roomId=?, personId=?, identifier=?, lastName=?, firstName=?, phone=?, checkOutDate=? WHERE (checkOutId=?);";
            PreparedStatement ps = myConnection.prepareStatement(query);
            ps.setString(1, obj.getContractId());
            ps.setString(2, obj.getRoomId());
            ps.setString(3, obj.getPersonId());
            ps.setString(4, obj.getIdentifier());
            ps.setString(5, obj.getLastName());
            ps.setString(6, obj.getFirstname());
            ps.setString(7, obj.getPhone());
            ps.setDate(8, obj.getCheckOutDate());
            ps.setString(9, obj.getCheckOutId());
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
            String query = "UPDATE CheckOut SET  contractId=?, roomId=?, personId=?, identifier=?, lastName=?, firstName=?, phone=?, checkOutDate=? WHERE (checkOutId=?);";
            PreparedStatement ps = myConnection.prepareStatement(query);
            ps.setString(1, values[1]);
            ps.setString(2, values[2]);
            ps.setString(3, values[3]);
            ps.setString(4, values[4]);
            ps.setString(5, values[5]);
            ps.setString(6, values[6]);
            ps.setString(7, values[7]);
            ps.setDate(8, Date.valueOf(values[8]));
            ps.setString(9, values[0]);
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
                    rs.getString("roomId"), rs.getString("personId"), rs.getString("identifier"),
                    rs.getString("lastName"), rs.getString("firstName"), rs.getString("phone"),
                    rs.getDate("checkOutDate"));
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
                        rs.getString("roomId"), rs.getString("personId"), rs.getString("identifier"),
                        rs.getString("lastName"), rs.getString("firstName"), rs.getString("phone"),
                        rs.getDate("checkOutDate")));
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
                        rs.getString("roomId"), rs.getString("personId"), rs.getString("identifier"),
                        rs.getString("lastName"), rs.getString("firstName"), rs.getString("phone"),
                        rs.getDate("checkOutDate")));
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
