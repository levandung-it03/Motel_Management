package com.motel_management.DataAccessObject;
import com.motel_management.Models.RoomModel;
import com.motel_management.DB_interaction.DB_connection;

import java.sql.*;
import java.util.ArrayList;

public class RoomDAO implements DAOInterface<RoomModel> {
    public RoomDAO() {}
    public static RoomDAO getInstance() {
        return new RoomDAO();
    }

    @Override
    public int insert(RoomModel obj) {
        Connection myConnection = DB_connection.getMMDBConnection();
        try {
            String query = "INSERT INTO Room VALUES (?, ?, ?, ?)";
            PreparedStatement ps = myConnection.prepareStatement(query);
            ps.setString(1, obj.getRoomId());
            ps.setInt(2, obj.getQuantity());
            ps.setInt(3, obj.getMaxQuantity());
            ps.setInt(4, obj.getDefaultRoomPrice());
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
            String query = "INSERT INTO Room VALUES (?, ?, ?, ?);";
            PreparedStatement ps = myConnection.prepareStatement(query);
            ps.setString(1, values[0]);
            ps.setInt(2, Integer.parseInt(values[1]));
            ps.setInt(3, Integer.parseInt(values[2]));
            ps.setInt(4, Integer.parseInt(values[3]));

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
            String query = "DELETE FROM Room WHERE roomId=?";
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
    public int update(RoomModel obj) {
        Connection myConnection = DB_connection.getMMDBConnection();
        try {
            String query = "UPDATE Room SET  quantity=?, maxQuantity=?, defaultRoomPrice=? WHERE (roomId=?);";
            PreparedStatement ps = myConnection.prepareStatement(query);
            ps.setInt(1, obj.getQuantity());
            ps.setInt(2, obj.getMaxQuantity());
            ps.setInt(3, obj.getDefaultRoomPrice());
            ps.setString(4, obj.getRoomId());
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
        System.out.println(values[0]);
        System.out.println(values[1]);
        System.out.println(values[2]);
        System.out.println(values[3]);
        try {
            String query = "UPDATE Room SET  quantity=?, maxQuantity=?, defaultRoomPrice=? WHERE (roomId=?);";
            PreparedStatement ps = myConnection.prepareStatement(query);
            ps.setInt(1, Integer.parseInt(values[1]));
            ps.setInt(2, Integer.parseInt(values[2]));
            ps.setInt(3, Integer.parseInt(values[3]));
            ps.setString(4, values[0]);
            return ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DB_connection.closeMMDBConnection(myConnection);
        }
    }
    public int resetRoomStatus(String[] values) {
        Connection myConnection = DB_connection.getMMDBConnection();
        try {
            String query = "UPDATE Room SET quantity=? WHERE (roomId=?);";
            PreparedStatement ps = myConnection.prepareStatement(query);
            ps.setInt(1, Integer.parseInt(values[0]));
            ps.setString(2, values[1]);
            return ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DB_connection.closeMMDBConnection(myConnection);
        }
    }

    @Override
    public RoomModel selectById(String id) {
        Connection myConnection = DB_connection.getMMDBConnection();
        try {
            String query = ("SELECT * FROM Room WHERE (roomId=?)");
            PreparedStatement ps = myConnection.prepareStatement(query);
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();
            rs.next();
            return new RoomModel(rs.getString("roomId"), rs.getInt("quantity"),
                    rs.getInt("maxQuantity"), rs.getInt("defaultRoomPrice"));
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DB_connection.closeMMDBConnection(myConnection);
        }
        return null;
    }

    @Override
    public ArrayList<RoomModel> selectAll() {
        Connection myConnection = DB_connection.getMMDBConnection();
        try {
            PreparedStatement ps = myConnection.prepareStatement("SELECT * FROM Room");
            ArrayList<RoomModel> result = new ArrayList<>();
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                result.add(new RoomModel(rs.getString("roomId"), rs.getInt("quantity"),
                        rs.getInt("maxQuantity"), rs.getInt("defaultRoomPrice")));
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
    public ArrayList<RoomModel> selectByCondition(String condition) {
        Connection myConnection = DB_connection.getMMDBConnection();
        try {
            PreparedStatement ps = myConnection.prepareStatement("SELECT * FROM Room " + condition);
            ArrayList<RoomModel> result = new ArrayList<>();
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                result.add(new RoomModel(rs.getString("roomId"), rs.getInt("quantity"),
                        rs.getInt("maxQuantity"), rs.getInt("defaultRoomPrice")));
            }
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DB_connection.closeMMDBConnection(myConnection);
        }
        return null;
    }

    public ArrayList<String> selectAllOccupiedRoomId() {
        Connection myConnection = DB_connection.getMMDBConnection();
        try {
            PreparedStatement ps = myConnection
                    .prepareStatement("SELECT roomId FROM Room WHERE (quantity > 0 OR quantity = -1) ORDER BY roomId ASC");
            ArrayList<String> result = new ArrayList<>();
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                result.add(rs.getString("roomId"));
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
