package com.motel_management.DataAccessObject;
import com.motel_management.Models.RoomModel;
import com.motel_management.DB_interaction.DB_connection;

import java.sql.*;
import java.util.ArrayList;

public class RoomsDAO implements DAOInterface<RoomModel> {
    public RoomsDAO() {}
    public static RoomsDAO getInstance() {
        return new RoomsDAO();
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
            return ps.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DB_connection.closeMMDBConnection(myConnection);
        }
        return 0;
    }

    @Override
    public int delete(RoomModel obj) {
        Connection myConnection = DB_connection.getMMDBConnection();
        try {
            String query = "DELETE FROM Room WHERE roomId=?";
            PreparedStatement ps = myConnection.prepareStatement(query);
            ps.setString(1, obj.getRoomId());
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
            String query = "UPDATE Account SET  quantity=?, maxQuantity=?, defaultRoomPrice=? WHERE (roomId=?);";
            PreparedStatement ps = myConnection.prepareStatement(query);
            ps.setString(1, obj.getRoomId());
            ps.setInt(2, obj.getQuantity());
            ps.setInt(3, obj.getMaxQuantity());
            ps.setInt(4, obj.getDefaultRoomPrice());
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
            String query = ("SELECT * FROM Room WHERE (userId=?)");
            PreparedStatement ps = myConnection.prepareStatement(query);
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
            PreparedStatement ps = myConnection.prepareStatement("SELECT * FROM Account " + condition);
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
}
