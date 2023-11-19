package com.motel_management.DataAccessObject;
import com.motel_management.Models.InvoiceModel;
import com.motel_management.DB_interaction.DB_connection;
import com.motel_management.Views.Configs;

import java.sql.*;
import java.util.ArrayList;

public class InvoiceDAO implements DAOInterface<InvoiceModel>{
    public InvoiceDAO() {}
    public static InvoiceDAO getInstance() {
        return new InvoiceDAO();
    }
    @Override
    public int insert(InvoiceModel obj) {
        Connection myConnection = DB_connection.getMMDBConnection();
        try {
            String query = "INSERT INTO Invoice VALUES (?, ?, ?, ?, ?, ?, ? ,? ,? ,? ,? ,? ,?, ?, ?)";
            PreparedStatement ps = myConnection.prepareStatement(query);
            ps.setString(1, obj.getInvoiceId());
            ps.setString(2, obj.getRoomId());
            ps.setInt(3, obj.getDefaultRoomPrice());
            ps.setDate(4, obj.getDateCreated());
            ps.setString(5, obj.getYearPayment());
            ps.setString(6, obj.getMonthPayment());
            ps.setInt(7, obj.getFormerElectricNumber());
            ps.setInt(8, obj.getNewElectricNumber());
            ps.setInt(9, obj.getFormerWaterNumber());
            ps.setInt(10, obj.getNewWaterNumber());
            ps.setInt(11, obj.getGarbage());
            ps.setInt(12, obj.getWifi());
            ps.setInt(13, obj.getVehicle());
            ps.setInt(14, obj.getTotal());
            ps.setString(15, obj.getWasPaid());
            return ps.executeUpdate(query);
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
            String query = "INSERT INTO Invoice VALUES (?, ?, ?, ?, ?, ?, ? ,? ,? ,? ,? ,? ,?, ?, ?)";
            PreparedStatement ps = myConnection.prepareStatement(query);
            ps.setString(1, values[0]);
            ps.setString(2, values[1]);
            ps.setInt(3, Integer.parseInt(values[2]));
            ps.setDate(4, Date.valueOf(Configs.stringToDate(values[3])));
            ps.setString(5, values[4]);
            ps.setString(6, values[5]);
            ps.setInt(7, Integer.parseInt(values[6]));
            ps.setInt(8, Integer.parseInt(values[7]));
            ps.setInt(9, Integer.parseInt(values[8]));
            ps.setInt(10, Integer.parseInt(values[9]));
            ps.setInt(11, Integer.parseInt(values[10]));
            ps.setInt(12, Integer.parseInt(values[11]));
            ps.setInt(13, Integer.parseInt(values[12]));
            ps.setInt(14, Integer.parseInt(values[13]));
            ps.setString(15, values[14]);

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
            String query = "UPDATE Invoice SET  roomId=?, defaultRoomPrice=?," +
                    "dateCreated=?, paymentYear=?, paymentMonth=?, formerElectricNumber=?, newElectricNumber=?" +
                    "formerWaterNumber=?, newWaterNumber=?, garbage=?, wifi=?, vehicle=?, total=?, wasPaid=? WHERE (invoiceId=?);";
            PreparedStatement ps = myConnection.prepareStatement(query);
            ps.setString(1, obj.getRoomId());
            ps.setInt(2, obj.getDefaultRoomPrice());
            ps.setDate(3, obj.getDateCreated());
            ps.setString(4, obj.getYearPayment());
            ps.setString(5, obj.getMonthPayment());
            ps.setInt(6, obj.getFormerElectricNumber());
            ps.setInt(7, obj.getNewElectricNumber());
            ps.setInt(8, obj.getFormerWaterNumber());
            ps.setInt(9, obj.getNewWaterNumber());
            ps.setInt(10, obj.getGarbage());
            ps.setInt(11, obj.getWifi());
            ps.setInt(12, obj.getVehicle());
            ps.setInt(13, obj.getTotal());
            ps.setString(14, obj.getWasPaid());
            ps.setString(15, obj.getInvoiceId());
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
            String query = "UPDATE Invoice SET  roomId=?, defaultRoomPrice=?," +
                    "dateCreated=?, paymentYear=?, paymentMonth=?, formerElectricNumber=?, newElectricNumber=?" +
                    "formerWaterNumber=?, newWaterNumber=?, garbage=?, wifi=?, vehicle=?, total=?, wasPaid=? WHERE (invoiceId=?);";
            PreparedStatement ps = myConnection.prepareStatement(query);
            ps.setString(1, values[1]);
            ps.setInt(2, Integer.parseInt(values[2]));
            ps.setDate(3, Date.valueOf(Configs.stringToDate(values[3])));
            ps.setString(4, values[4]);
            ps.setString(5, values[5]);
            ps.setInt(6, Integer.parseInt(values[6]));
            ps.setInt(7, Integer.parseInt(values[7]));
            ps.setInt(8, Integer.parseInt(values[8]));
            ps.setInt(9, Integer.parseInt(values[9]));
            ps.setInt(10, Integer.parseInt(values[10]));
            ps.setInt(11, Integer.parseInt(values[11]));
            ps.setInt(12, Integer.parseInt(values[12]));
            ps.setInt(13, Integer.parseInt(values[13]));
            ps.setString(14, values[14]);
            ps.setString(15, values[0]);
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
                    rs.getInt("defaultRoomPrice"),rs.getDate("dateCreated"),
                    rs.getString("paymentYear"),rs.getString("paymentMonth"),
                    rs.getInt("formerElectricNumber"),rs.getInt("newElectricNumber"),
                    rs.getInt("formerWaterNumber"),rs.getInt("newWaterNumber"),
                    rs.getInt("garbage"),rs.getInt("wifi"),
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
                        rs.getInt("defaultRoomPrice"),rs.getDate("dateCreated"),
                        rs.getString("paymentYear"),rs.getString("paymentMonth"),
                        rs.getInt("formerElectricNumber"),rs.getInt("newElectricNumber"),
                        rs.getInt("formerWaterNumber"),rs.getInt("newWaterNumber"),
                        rs.getInt("garbage"),rs.getInt("wifi"),
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
                        rs.getInt("defaultRoomPrice"),rs.getDate("dateCreated"),
                        rs.getString("paymentYear"),rs.getString("paymentMonth"),
                        rs.getInt("formerElectricNumber"),rs.getInt("newElectricNumber"),
                        rs.getInt("formerWaterNumber"),rs.getInt("newWaterNumber"),
                        rs.getInt("garbage"),rs.getInt("wifi"),
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
