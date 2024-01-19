package com.motel_management.DataAccessObject;

import com.motel_management.DB_interaction.DB_connection;
import com.motel_management.Models.RoomPriceHistoryModel;
import com.motel_management.Views.Configs;

import java.sql.*;
import java.util.ArrayList;

public class RoomPriceHistoryDAO implements DAOInterface<RoomPriceHistoryModel>{

    public RoomPriceHistoryDAO() {}
    public static RoomPriceHistoryDAO getInstance() {
        return new RoomPriceHistoryDAO();
    }

    @Override
    public int delete(String id) {
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
            ps.setDate(2, Date.valueOf(Configs.stringToDate(values[1])));
            ps.setInt(3, Integer.parseInt(values[2]));

            return ps.executeUpdate();
        } catch (SQLException e) {
            e.fillInStackTrace();
        } finally {
            DB_connection.closeMMDBConnection(myConnection);
        }
        return 0;
    }

    public int deleteLastHistory(String id, String date) {
        Connection myConnection = DB_connection.getMMDBConnection();
        try {
            String query = "DELETE FROM RoomPriceHistory WHERE roomId=? and priceRaisedDate=?";
            PreparedStatement ps = myConnection.prepareStatement(query);
            ps.setString(1, id);
            ps.setDate(2, Date.valueOf(Configs.stringToDate(date)));
            return ps.executeUpdate();
        } catch (SQLException e) {
            e.fillInStackTrace();
        } finally {
            DB_connection.closeMMDBConnection(myConnection);
        }
        return 0;
    }

    // Overloads
    public int deleteLastHistory(String id, Date date) {
        Connection myConnection = DB_connection.getMMDBConnection();
        try {
            String query = "DELETE FROM RoomPriceHistory WHERE roomId=? and priceRaisedDate=?";
            PreparedStatement ps = myConnection.prepareStatement(query);
            ps.setString(1, id);
            ps.setDate(2, date);
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
            ps.setInt(3, Integer.parseInt(values[1]));
            return ps.executeUpdate();
        } catch (SQLException e) {
            e.fillInStackTrace();
        } finally {
            DB_connection.closeMMDBConnection(myConnection);
        }
        return 0;
    }

    public RoomPriceHistoryModel selectByIdAndDate(String id, String date) {
        Connection myConnection = DB_connection.getMMDBConnection();
        try {
            String query = ("SELECT * FROM RoomPriceHistory WHERE (roomId=? AND priceRaisedDate=?)");
            PreparedStatement ps = myConnection.prepareStatement(query);
            ps.setString(1, id);
            ps.setDate(2, Date.valueOf(Configs.stringToDate(date)));
            ResultSet rs = ps.executeQuery();
            rs.next();
            return new RoomPriceHistoryModel(rs.getString("roomId"), rs.getDate("priceRaisedDate"),
                    rs.getInt("roomPrice"));
        } catch (SQLException e) {
            e.fillInStackTrace();
        } finally {
            DB_connection.closeMMDBConnection(myConnection);
        }
        return null;
    }

    // Overloads
    public RoomPriceHistoryModel selectByIdAndDate(String id, Date date) {
        Connection myConnection = DB_connection.getMMDBConnection();
        try {
            String query = ("SELECT * FROM RoomPriceHistory WHERE (roomId=? AND priceRaisedDate=?)");
            PreparedStatement ps = myConnection.prepareStatement(query);
            ps.setString(1, id);
            ps.setDate(2, date);
            ResultSet rs = ps.executeQuery();
            rs.next();
            return new RoomPriceHistoryModel(rs.getString("roomId"), rs.getDate("priceRaisedDate"),
                    rs.getInt("roomPrice"));
        } catch (SQLException e) {
            e.fillInStackTrace();
        } finally {
            DB_connection.closeMMDBConnection(myConnection);
        }
        return null;
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

    public ArrayList<RoomPriceHistoryModel> selectAllRoomPrice(String roomId) {
        Connection myConnection = DB_connection.getMMDBConnection();
        try {
            PreparedStatement ps = myConnection.prepareStatement(
                    "SELECT RoomPriceHistory.*, MAX(priceRaisedDate) AS currentPriceDate FROM RoomPriceHistory" +
                    "GROUP BY roomId HAVING RoomPriceHistory.priceRaisedDate = currentPriceDate;"
            );
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

    public int selectCurrentRoomPriceWithRoomId(String roomId) {
        Connection myConnection = DB_connection.getMMDBConnection();
        try {
            PreparedStatement ps = myConnection.prepareStatement(
                    "SELECT * FROM RoomPriceHistory WHERE roomId=? ORDER BY priceRaisedDate DESC LIMIT 1"
            );
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

}
