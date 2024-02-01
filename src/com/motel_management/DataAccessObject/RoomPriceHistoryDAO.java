package com.motel_management.DataAccessObject;

import com.motel_management.DB_interaction.DB_connection;
import com.motel_management.Models.RoomPriceHistoryModel;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;

public class RoomPriceHistoryDAO implements DAOInterface<RoomPriceHistoryModel>{

    public RoomPriceHistoryDAO() {}
    public static RoomPriceHistoryDAO getInstance() {
        return new RoomPriceHistoryDAO();
    }

    @Override
    public int delete(String id) {
        Connection myConnection = DB_connection.getMMDBConnection();
        try {
            String query = "DELETE FROM RoomPriceHistory WHERE roomId=?";
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
    public int insert(RoomPriceHistoryModel obj) {
        Connection myConnection = DB_connection.getMMDBConnection();
        try {
            String query = "INSERT INTO Room VALUES (?, ?, ?)";
            PreparedStatement ps = myConnection.prepareStatement(query);
            ps.setString(1, obj.getRoomId());
            ps.setDate(2, obj.getPriceRaisedDate());
            ps.setInt(3, obj.getRoomPrice());
            return ps.executeUpdate();
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
            String query = "INSERT INTO RoomPriceHistory VALUES (?, ?, ?);";
            PreparedStatement ps = myConnection.prepareStatement(query);
            ps.setString(1, values[0]);
            ps.setDate(2, Date.valueOf(values[1]));
            ps.setInt(3, Integer.parseInt(values[2]));

            return ps.executeUpdate();
        } catch (SQLException e) {
            e.fillInStackTrace();
        } finally {
            DB_connection.closeMMDBConnection(myConnection);
        }
        return 0;
    }

    @Override
    public int update(RoomPriceHistoryModel obj) {
        Connection myConnection = DB_connection.getMMDBConnection();
        try {
            String query = "UPDATE RoomPriceHistory SET roomPrice=? WHERE (roomId=? AND priceRaisedDate=?);";
            PreparedStatement ps = myConnection.prepareStatement(query);
            ps.setInt(1, obj.getRoomPrice());
            ps.setString(2, obj.getRoomId());
            ps.setDate(3, obj.getPriceRaisedDate());
            return ps.executeUpdate();
        } catch (SQLException e) {
            e.fillInStackTrace();
        } finally {
            DB_connection.closeMMDBConnection(myConnection);
        }
        return 0;
    }

    @Override
    public RoomPriceHistoryModel selectById(String id) {
        return null;
    }

    // OverLOAD
    public int update(String[] values) {
        Connection myConnection = DB_connection.getMMDBConnection();
        try {
            String query = "UPDATE RoomPriceHistory SET roomPrice=? WHERE (roomId=? AND priceRaisedDate=?);";
            PreparedStatement ps = myConnection.prepareStatement(query);
            ps.setInt(1, Integer.parseInt(values[2]));
            ps.setString(2, values[0]);
            ps.setDate(3, Date.valueOf(values[1]));
            return ps.executeUpdate();
        } catch (SQLException e) {
            e.fillInStackTrace();
        } finally {
            DB_connection.closeMMDBConnection(myConnection);
        }
        return 0;
    }

    @Override
    public ArrayList<RoomPriceHistoryModel> selectAll() {
        Connection myConnection = DB_connection.getMMDBConnection();
        try {
            PreparedStatement ps = myConnection.prepareStatement("SELECT * FROM RoomPriceHistory");
            ArrayList<RoomPriceHistoryModel> result = new ArrayList<>();
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                result.add(new RoomPriceHistoryModel(rs.getString("roomId"), rs.getDate("priceRaisedDate"),
                        rs.getInt("roomPrice")));
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
    public ArrayList<RoomPriceHistoryModel> selectByCondition(String condition) {
        Connection myConnection = DB_connection.getMMDBConnection();
        try {
            PreparedStatement ps = myConnection.prepareStatement("SELECT * FROM RoomPriceHistory " + condition);
            ArrayList<RoomPriceHistoryModel> result = new ArrayList<>();
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                result.add(new RoomPriceHistoryModel(rs.getString("roomId"), rs.getDate("priceRaisedDate"),
                        rs.getInt("roomPrice")));
            }
            return result;
        } catch (SQLException e) {
            e.fillInStackTrace();
        } finally {
            DB_connection.closeMMDBConnection(myConnection);
        }
        return null;
    }

    public HashMap<String, RoomPriceHistoryModel> selectAllLastPriceOfEachRoom() {
        Connection myConnection = DB_connection.getMMDBConnection();
        try {
            PreparedStatement ps = myConnection.prepareStatement("""
                    SELECT * FROM RoomPriceHistory
                    WHERE (roomId, priceRaisedDate) IN (
                        SELECT roomId, MAX(priceRaisedDate) FROM RoomPriceHistory
                        GROUP BY roomId
                    );"""
            );
            HashMap<String, RoomPriceHistoryModel> result = new HashMap<>();
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                result.put(
                        rs.getString("roomId"),
                        new RoomPriceHistoryModel(
                                rs.getString("roomId"),
                                rs.getDate("priceRaisedDate"),
                                rs.getInt("roomPrice")
                        )
                );
            }
            return result;
        } catch (SQLException e) {
            e.fillInStackTrace();
        } finally {
            DB_connection.closeMMDBConnection(myConnection);
        }
        return null;
    }

    public int selectCurrentRoomPriceWithRoomId(String roomId) {
        Connection myConnection = DB_connection.getMMDBConnection();
        try {
            PreparedStatement ps = myConnection.prepareStatement(
                    "SELECT * FROM RoomPriceHistory WHERE roomId=? ORDER BY priceRaisedDate DESC LIMIT 1"
            );
            ps.setString(1, roomId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt("roomPrice");
            }
        } catch (SQLException e) {
            e.fillInStackTrace();
        } finally {
            DB_connection.closeMMDBConnection(myConnection);
        }
        return 0;
    }

    public RoomPriceHistoryModel selectCurrentRoomPriceHistoryWithRoomId(String roomId) {
        Connection myConnection = DB_connection.getMMDBConnection();
        try {
            PreparedStatement ps = myConnection.prepareStatement(
                    "SELECT * FROM RoomPriceHistory WHERE roomId=? ORDER BY priceRaisedDate DESC LIMIT 1"
            );
            ps.setString(1, roomId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new RoomPriceHistoryModel(roomId, rs.getDate("priceRaisedDate"),
                        rs.getInt("roomPrice"));
            }
        } catch (SQLException e) {
            e.fillInStackTrace();
        } finally {
            DB_connection.closeMMDBConnection(myConnection);
        }
        return null;
    }
}