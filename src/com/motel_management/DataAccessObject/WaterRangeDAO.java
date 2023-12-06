package com.motel_management.DataAccessObject;

import com.motel_management.DB_interaction.DB_connection;
import com.motel_management.Models.WaterRangeModel;

import java.sql.*;
import java.util.ArrayList;

public class WaterRangeDAO implements DAOInterface<WaterRangeModel>{
    public WaterRangeDAO() {}
    public static WaterRangeDAO getInstance() {
        return new WaterRangeDAO();
    }

    @Override
    public int insert(WaterRangeModel obj) {
        Connection myConnection = DB_connection.getMMDBConnection();
        try {
            String query = "INSERT INTO WaterRange VALUES (?, ?, ?, ?, ?)";
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
            String query = "INSERT INTO WaterRange VALUES (?, ?, ?, ?, ?)";
            PreparedStatement ps = myConnection.prepareStatement(query);
            ps.setString(1, values[0]);
            ps.setString(2, values[1]);
            ps.setInt(3, Integer.parseInt(values[2]));
            ps.setInt(4, Integer.parseInt(values[3]));
            ps.setInt(5, Integer.parseInt(values[4]));

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
            String query = "DELETE FROM WaterRange WHERE rangeId=?";
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
    public int update(WaterRangeModel obj) {
        Connection myConnection = DB_connection.getMMDBConnection();
        try {
            String query = "UPDATE WaterRange SET  rangeName=?, minRangeValue=?, maxRangeValue=?, price=? " +
                    "WHERE (rangeId=?);";
            PreparedStatement ps = myConnection.prepareStatement(query);
            ps.setString(1, obj.getRangeName());
            ps.setInt(2, obj.getMinRangeValue());
            ps.setInt(3, obj.getMaxRangeValue());
            ps.setInt(4, obj.getPrice());
            ps.setString(5, obj.getRangeId());
            return ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DB_connection.closeMMDBConnection(myConnection);
        }
        return 0;
    }
    // OverLOAD
    public int update(String[] values) {
        Connection myConnection = DB_connection.getMMDBConnection();
        try {
            String query = "UPDATE WaterRange SET  rangeName=?, minRangeValue=?, maxRangeValue=?, price=? " +
                    "WHERE (rangeId=?);";
            PreparedStatement ps = myConnection.prepareStatement(query);
            ps.setString(1, values[1]);
            ps.setInt(2, Integer.parseInt(values[2]));
            ps.setInt(3, Integer.parseInt(values[3]));
            ps.setInt(4, Integer.parseInt(values[4]));
            ps.setString(5, values[0]);
            return ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DB_connection.closeMMDBConnection(myConnection);
        }
        return 0;
    }

    @Override
    public WaterRangeModel selectById(String id) {
        Connection myConnection = DB_connection.getMMDBConnection();
        try {
            String query = ("SELECT * FROM WaterRange WHERE (rangeId=?)");
            PreparedStatement ps = myConnection.prepareStatement(query);
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();
            rs.next();
            return new WaterRangeModel(rs.getString("rangeId"), rs.getString("rangeName"),
                    rs.getInt("minRangeValue"),rs.getInt("maxRangeValue"),
                    rs.getInt("price"));
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DB_connection.closeMMDBConnection(myConnection);
        }
        return null;
    }

    @Override
    public ArrayList<WaterRangeModel> selectAll() {
        Connection myConnection = DB_connection.getMMDBConnection();
        try {
            PreparedStatement ps = myConnection.prepareStatement("SELECT * FROM WaterRange");
            ArrayList<WaterRangeModel> result = new ArrayList<>();
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                result.add(new WaterRangeModel(rs.getString("rangeId"), rs.getString("rangeName"),
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
    public ArrayList<WaterRangeModel> selectByCondition(String condition) {
        Connection myConnection = DB_connection.getMMDBConnection();
        try {
            PreparedStatement ps = myConnection.prepareStatement("SELECT * FROM WaterRange " + condition);
            ArrayList<WaterRangeModel> result = new ArrayList<>();
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                result.add(new WaterRangeModel(rs.getString("rangeId"), rs.getString("rangeName"),
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