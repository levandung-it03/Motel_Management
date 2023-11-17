package com.motel_management.DataAccessObject;

import com.motel_management.DB_interaction.DB_connection;
import com.motel_management.Models.ElectricRangeModel;

import java.sql.*;
import java.util.ArrayList;

public class ElectricRangeDAO implements DAOInterface<ElectricRangeModel>{
    public ElectricRangeDAO() {}
    public static ElectricRangeDAO getInstance() {
        return new ElectricRangeDAO();
    }

    @Override
    public int insert(ElectricRangeModel obj) {
        Connection myConnection = DB_connection.getMMDBConnection();
        try {
            String query = "INSERT INTO ElectricRange VALUES (?, ?, ?, ?, ?)";
            PreparedStatement ps = myConnection.prepareStatement(query);
            ps.setString(1, obj.getRangeId());
            ps.setString(2, obj.getRangeName());
            ps.setInt(3, obj.getMinRangeValue());
            ps.setInt(4, obj.getMaxRangeValue());
            ps.setInt(5, obj.getPrice());
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
            String query = "INSERT INTO ElectricRange VALUES (?, ?, ?, ?, ?)";
            PreparedStatement ps = myConnection.prepareStatement(query);
            ps.setString(1, values[0]);
            ps.setString(2, values[1]);
            ps.setInt(3, Integer.parseInt(values[2]));
            ps.setInt(4, Integer.parseInt(values[3]));
            ps.setInt(5, Integer.parseInt(values[4]));
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
            String query = "DELETE FROM ElectricRange WHERE rangeId=?";
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
    public int update(ElectricRangeModel obj) {
        Connection myConnection = DB_connection.getMMDBConnection();
        try {
            String query = "UPDATE ElectricRange SET  rangeName=?, minRangeValue=?, maxRangeValue=?, price=? " +
                    "WHERE (rangeId=?);";
            PreparedStatement ps = myConnection.prepareStatement(query);
            ps.setString(1, obj.getRangeName());
            ps.setInt(2, obj.getMinRangeValue());
            ps.setInt(3, obj.getMaxRangeValue());
            ps.setInt(4, obj.getPrice());
            ps.setString(5, obj.getRangeId());
            return ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DB_connection.closeMMDBConnection(myConnection);
        }
    }
    // OverLOAD
    public int update(String[] values) {
        Connection myConnection = DB_connection.getMMDBConnection();
        try {
            String query = "UPDATE ElectricRange SET  rangeName=?, minRangeValue=?, maxRangeValue=?, price=? " +
                    "WHERE (rangeId=?);";
            PreparedStatement ps = myConnection.prepareStatement(query);
            ps.setString(1, values[1]);
            ps.setInt(2, Integer.parseInt(values[2]));
            ps.setInt(3, Integer.parseInt(values[3]));
            ps.setInt(4, Integer.parseInt(values[4]));
            ps.setString(5, values[0]);
            return ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DB_connection.closeMMDBConnection(myConnection);
        }
    }

    @Override
    public ElectricRangeModel selectById(String id) {
        Connection myConnection = DB_connection.getMMDBConnection();
        try {
            String query = ("SELECT * FROM ElectricRange WHERE (rangeId=?)");
            PreparedStatement ps = myConnection.prepareStatement(query);
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();
            rs.next();
            return new ElectricRangeModel(rs.getString("rangeId"), rs.getString("rangeName"),
                    rs.getInt("minRangeValue"), rs.getInt("maxRangeValue"),
                    rs.getInt("price"));
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DB_connection.closeMMDBConnection(myConnection);
        }
        return null;
    }

    @Override
    public ArrayList<ElectricRangeModel> selectAll() {
        Connection myConnection = DB_connection.getMMDBConnection();
        try {
            PreparedStatement ps = myConnection.prepareStatement("SELECT * FROM ElectricRange");
            ArrayList<ElectricRangeModel> result = new ArrayList<>();
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                result.add(new ElectricRangeModel(rs.getString("rangeId"), rs.getString("rangeName"),
                        rs.getInt("minRangeValue"),rs.getInt("maxRangeValue"),
                        rs.getInt("price")));
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
    public ArrayList<ElectricRangeModel> selectByCondition(String condition) {
        Connection myConnection = DB_connection.getMMDBConnection();
        try {
            PreparedStatement ps = myConnection.prepareStatement("SELECT * FROM ElectricRange " + condition);
            ArrayList<ElectricRangeModel> result = new ArrayList<>();
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                result.add(new ElectricRangeModel(rs.getString("rangeId"), rs.getString("rangeName"),
                        rs.getInt("minRangeValue"),rs.getInt("maxRangeValue"),
                        rs.getInt("price")));
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
