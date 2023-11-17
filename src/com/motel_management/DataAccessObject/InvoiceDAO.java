package com.motel_management.DataAccessObject;
import com.motel_management.Models.InvoiceModel;
import com.motel_management.DB_interaction.DB_connection;
import com.motel_management.Models.RoomModel;

import java.sql.*;
import java.util.ArrayList;

public class InvoiceDAO implements DAOInterface<InvoiceModel>{
    public InvoiceDAO() {}
    public static RoomDAO getInstance() {
        return new RoomDAO();
    }
    @Override
    public int insert(InvoiceModel obj) {
        Connection myConnection = DB_connection.getMMDBConnection();
        try {
            String query = "INSERT INTO Invoice VALUES (?, ?, ?, ?, ?, ?, ? ,? ,? ,? ,? ,? ,?)";
            PreparedStatement ps = myConnection.prepareStatement(query);
            ps.setString(1, obj.getInvoiceId());
            ps.setString(2, obj.getRoomId());
            ps.setString(3, obj.getConsumptionId());
            ps.setInt(4, obj.getDefaultRoomPrice());
            ps.setString(5, obj.getMonthPayment());
            ps.setString(6, obj.getYearPayment());
            ps.setDate(7, obj.getDateCreated());
            ps.setInt(8, obj.getTotalWaterBill());
            ps.setInt(9, obj.getTotalElectricBill());
            ps.setInt(10, obj.getGarbage());
            ps.setInt(11, obj.getVehicle());
            ps.setInt(12, obj.getTotal());
            ps.setString(13, obj.getWasPaid());
            return ps.executeUpdate(query);
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
            String query = "DELETE FROM Invoice WHERE invoiceId=?";
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
    public int update(InvoiceModel obj) {
        Connection myConnection = DB_connection.getMMDBConnection();
        try {
            String query = "UPDATE Invoice SET  roomId=?, consumptionId=?, defaultRoomPrice=?," +
                    "monthPayment=?, yearPayment=?, dateCreated=?, totalWaterBill=?, totalElectricBill=?," +
                    "garbage=?, vehicle=?, total=?, wasPaid=? WHERE (invoiceId=?);";
            PreparedStatement ps = myConnection.prepareStatement(query);
            ps.setString(1, obj.getRoomId());
            ps.setString(2, obj.getConsumptionId());
            ps.setInt(3, obj.getDefaultRoomPrice());
            ps.setString(4, obj.getMonthPayment());
            ps.setString(5, obj.getYearPayment());
            ps.setDate(6, obj.getDateCreated());
            ps.setInt(7, obj.getTotalWaterBill());
            ps.setInt(8, obj.getTotalElectricBill());
            ps.setInt(9, obj.getGarbage());
            ps.setInt(10, obj.getVehicle());
            ps.setInt(111, obj.getTotal());
            ps.setString(12, obj.getWasPaid());
            ps.setString(13, obj.getInvoiceId());
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
            String query = "UPDATE Invoice SET  roomId=?, consumptionId=?, defaultRoomPrice=?," +
                    "monthPayment=?, yearPayment=?, dateCreated=?, totalWaterBill=?, totalElectricBill=?," +
                    "garbage=?, vehicle=?, total=?, wasPaid=? WHERE (invoiceId=?);";
            PreparedStatement ps = myConnection.prepareStatement(query);
            ps.setString(1, values[1]);
            ps.setString(2, values[2]);
            ps.setInt(3, Integer.parseInt(values[3]));
            ps.setString(4, values[4]);
            ps.setString(5, values[5]);
            ps.setDate(6, java.sql.Date.valueOf(values[6]));
            ps.setInt(7, Integer.parseInt(values[7]));
            ps.setInt(8, Integer.parseInt(values[8]));
            ps.setInt(9, Integer.parseInt(values[9]));
            ps.setInt(10, Integer.parseInt(values[10]));
            ps.setInt(111, Integer.parseInt(values[11]));
            ps.setString(12, values[12]);
            ps.setString(13, values[0]);
            return ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DB_connection.closeMMDBConnection(myConnection);
        }
    }

    @Override
    public InvoiceModel selectById(String id) {
        Connection myConnection = DB_connection.getMMDBConnection();
        try {
            String query = ("SELECT * FROM Invoice WHERE (invoiceId=?)");
            PreparedStatement ps = myConnection.prepareStatement(query);
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();
            rs.next();
            return new InvoiceModel(rs.getString("invoiceId"), rs.getString("roomId"),
                    rs.getString("consumptionId"),rs.getInt("defaultRoomPrice"),
                    rs.getString("monthPayment"),rs.getString("yearPayment"),
                    rs.getDate("dateCreated"),rs.getInt("totalWaterBill"),
                    rs.getInt("totalElectricBill"),rs.getInt("garbage"),
                    rs.getInt("vehicle"),rs.getInt("total"),
                    rs.getString("wasPaid"));
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DB_connection.closeMMDBConnection(myConnection);
        }
        return null;
    }
    @Override
    public ArrayList<InvoiceModel> selectAll() {
        Connection myConnection = DB_connection.getMMDBConnection();
        try {
            PreparedStatement ps = myConnection.prepareStatement("SELECT * FROM Invoice");
            ArrayList<InvoiceModel> result = new ArrayList<>();
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                result.add(new InvoiceModel(rs.getString("invoiceId"), rs.getString("roomId"),
                        rs.getString("consumptionId"),rs.getInt("defaultRoomPrice"),
                        rs.getString("monthPayment"),rs.getString("yearPayment"),
                        rs.getDate("dateCreated"),rs.getInt("totalWaterBill"),
                        rs.getInt("totalElectricBill"),rs.getInt("garbage"),
                        rs.getInt("vehicle"),rs.getInt("total"),
                        rs.getString("wasPaid")));
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
    public ArrayList<InvoiceModel> selectByCondition(String condition) {
        Connection myConnection = DB_connection.getMMDBConnection();
        try {
            PreparedStatement ps = myConnection.prepareStatement("SELECT * FROM Invoice " + condition);
            ArrayList<InvoiceModel> result = new ArrayList<>();
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                result.add(new InvoiceModel(rs.getString("invoiceId"), rs.getString("roomId"),
                        rs.getString("consumptionId"),rs.getInt("defaultRoomPrice"),
                        rs.getString("monthPayment"),rs.getString("yearPayment"),
                        rs.getDate("dateCreated"),rs.getInt("totalWaterBill"),
                        rs.getInt("totalElectricBill"),rs.getInt("garbage"),
                        rs.getInt("vehicle"),rs.getInt("total"),
                        rs.getString("wasPaid")));
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
