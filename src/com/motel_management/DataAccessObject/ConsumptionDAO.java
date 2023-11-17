package com.motel_management.DataAccessObject;
import com.motel_management.Models.ConsumptionModel;
import com.motel_management.DB_interaction.DB_connection;

import java.sql.*;
import java.util.ArrayList;

public class ConsumptionDAO implements DAOInterface<ConsumptionModel> {
    public ConsumptionDAO() {}
    public static ConsumptionDAO getInstance() {
        return new ConsumptionDAO();
    }

    @Override
    public int insert(ConsumptionModel obj) {
        Connection myConnection = DB_connection.getMMDBConnection();
        try {
            String query = "INSERT INTO Consumption VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = myConnection.prepareStatement(query);
            ps.setString(1, obj.getConsumptionId());
            ps.setString(2, obj.getRoomId());
            ps.setString(3, obj.getConsumptionMonth());
            ps.setString(4, obj.getConsumptionYear());
            ps.setDate(5, obj.getDateCreated());
            ps.setInt(6, obj.getWaterNumber());
            ps.setInt(7, obj.getElectricNumber());
            ps.setString(8, obj.getVehicle());
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
            String query = "INSERT INTO Consumption VALUES (?, ?, ?, ?, ?, ?, ?, ?);";
            PreparedStatement ps = myConnection.prepareStatement(query);
            ps.setString(1, values[0]);
            ps.setString(2, values[1]);
            ps.setString(3, values[2]);
            ps.setString(4, values[3]);
            ps.setDate(5, Date.valueOf(values[4]));
            ps.setInt(6, Integer.parseInt(values[5]));
            ps.setInt(7, Integer.parseInt(values[6]));
            ps.setString(8, values[7]);
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
            String query = "DELETE FROM Consumption WHERE consumptionId=?";
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
    public int update(ConsumptionModel obj) {
        Connection myConnection = DB_connection.getMMDBConnection();
        try {
            String query = "UPDATE Consumption SET  roomId=?, consumptionMonth=?, consumptionYear=?, dateCreated=?, waterNumber=?, electricNumber=?, vehicle=? WHERE (consumptionId=?);";
            PreparedStatement ps = myConnection.prepareStatement(query);
            ps.setString(1, obj.getConsumptionId());
            ps.setString(2, obj.getRoomId());
            ps.setString(3, obj.getConsumptionMonth());
            ps.setString(4, obj.getConsumptionYear());
            ps.setDate(5, obj.getDateCreated());
            ps.setInt(6, obj.getWaterNumber());
            ps.setInt(7, obj.getElectricNumber());
            ps.setString(8, obj.getVehicle());
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
            String query = "UPDATE Consumption SET  roomId=?, consumptionMonth=?, consumptionYear=?, dateCreated=?, waterNumber=?, electricNumber=?, vehicle=? WHERE (consumptionId=?);";
            PreparedStatement ps = myConnection.prepareStatement(query);
            ps.setString(1, values[1]);
            ps.setString(2, values[2]);
            ps.setString(3, values[3]);
            ps.setString(4, values[4]);
            ps.setDate(5, Date.valueOf(values[5]));
            ps.setInt(6, Integer.parseInt(values[6]));
            ps.setInt(7, Integer.parseInt(values[7]));
            ps.setString(8, values[0]);
            return ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DB_connection.closeMMDBConnection(myConnection);
        }
    }

    @Override
    public ConsumptionModel selectById(String id) {
        Connection myConnection = DB_connection.getMMDBConnection();
        try {
            String query = ("SELECT * FROM Consumption WHERE (comsumptionId=\""+id+"\")");
            PreparedStatement ps = myConnection.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            rs.next();
            return new ConsumptionModel(rs.getString("consumptionId"), rs.getString("roomId"),
                    rs.getString("consumptionMonth"), rs.getString("consumptionYear"),
                    rs.getDate("dateCreated"), rs.getInt("waterNumber"),
                    rs.getInt("electricNumber"), rs.getString("vehicle"));
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DB_connection.closeMMDBConnection(myConnection);
        }
        return null;
    }

    @Override
    public ArrayList<ConsumptionModel> selectAll() {
        Connection myConnection = DB_connection.getMMDBConnection();
        try {
            PreparedStatement ps = myConnection.prepareStatement("SELECT * FROM Consumption");
            ArrayList<ConsumptionModel> result = new ArrayList<>();
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                result.add(new ConsumptionModel(rs.getString("consumptionId"), rs.getString("roomId"),
                        rs.getString("consumptionMonth"), rs.getString("consumptionYear"),
                        rs.getDate("dateCreated"), rs.getInt("waterNumber"),
                        rs.getInt("electricNumber"), rs.getString("vehicle")));
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
    public ArrayList<ConsumptionModel> selectByCondition(String condition) {
        Connection myConnection = DB_connection.getMMDBConnection();
        try {
            PreparedStatement ps = myConnection.prepareStatement("SELECT * FROM Consumption " + condition);
            ArrayList<ConsumptionModel> result = new ArrayList<>();
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                result.add(new ConsumptionModel(rs.getString("consumptionId"), rs.getString("roomId"),
                        rs.getString("consumptionMonth"), rs.getString("consumptionYear"),
                        rs.getDate("dateCreated"), rs.getInt("waterNumber"),
                        rs.getInt("electricNumber"), rs.getString("vehicle")));
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
