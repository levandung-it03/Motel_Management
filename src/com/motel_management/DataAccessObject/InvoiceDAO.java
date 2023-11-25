package com.motel_management.DataAccessObject;

import com.motel_management.Models.InvoiceModel;
import com.motel_management.DB_interaction.DB_connection;
import com.motel_management.Views.Configs;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;

public class InvoiceDAO implements DAOInterface<InvoiceModel> {
    public InvoiceDAO() {
    }

    public static InvoiceDAO getInstance() {
        return new InvoiceDAO();
    }

    @Override
    public int insert(InvoiceModel obj) {
        Connection myConnection = DB_connection.getMMDBConnection();
        try {
            String query = "INSERT INTO Invoice VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = myConnection.prepareStatement(query);
            ps.setString(1, obj.getInvoiceId());
            ps.setString(2, obj.getRoomId());
            ps.setInt(3, obj.getDefaultRoomPrice());
            ps.setDate(4, obj.getDateCreated());
            ps.setInt(5, obj.getPaymentYear());
            ps.setInt(6, obj.getPaymentMonth());
            ps.setInt(7, obj.getFormerElectricNumber());
            ps.setInt(8, obj.getNewElectricNumber());
            ps.setInt(9, obj.getFormerWaterNumber());
            ps.setInt(10, obj.getNewWaterNumber());
            ps.setInt(11, obj.getElectricPrice());
            ps.setInt(12, obj.getWaterPrice());
            ps.setInt(13, obj.getGarbage());
            ps.setInt(14, obj.getWifi());
            ps.setInt(15, obj.getVehicle());
            ps.setInt(16, obj.getTotal());
            ps.setString(17, obj.getWasPaid());
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
            String query = "INSERT INTO Invoice VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = myConnection.prepareStatement(query);
            ps.setString(1, values[0]);
            ps.setString(2, values[1]);
            ps.setInt(3, Integer.parseInt(values[2]));
            ps.setDate(4, Date.valueOf(Configs.stringToDate(values[3])));
            ps.setInt(5, Integer.parseInt(values[4]));
            ps.setInt(6, Integer.parseInt(values[5]));
            ps.setInt(7, Integer.parseInt(values[6]));
            ps.setInt(8, Integer.parseInt(values[7]));
            ps.setInt(9, Integer.parseInt(values[8]));
            ps.setInt(10, Integer.parseInt(values[9]));
            ps.setInt(11, Integer.parseInt(values[10]));
            ps.setInt(12, Integer.parseInt(values[11]));
            ps.setInt(13, Integer.parseInt(values[12]));
            ps.setInt(14, Integer.parseInt(values[13]));
            ps.setInt(15, Integer.parseInt(values[14]));
            ps.setInt(16, Integer.parseInt(values[15]));
            ps.setString(17, values[16]);
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
                    "dateCreated=?, paymentYear=?, paymentMonth=?, formerElectricNumber=?, newElectricNumber=?," +
                    "formerWaterNumber=?, newWaterNumber=?, electricPrice=?, waterPrice=?, garbage=?, wifi=?, vehicle=?," +
                    "total=?, wasPaid=? WHERE (invoiceId=?);";
            PreparedStatement ps = myConnection.prepareStatement(query);
            ps.setString(1, obj.getRoomId());
            ps.setInt(2, obj.getDefaultRoomPrice());
            ps.setDate(3, obj.getDateCreated());
            ps.setInt(4, obj.getPaymentYear());
            ps.setInt(5, obj.getPaymentMonth());
            ps.setInt(6, obj.getFormerElectricNumber());
            ps.setInt(7, obj.getNewElectricNumber());
            ps.setInt(8, obj.getFormerWaterNumber());
            ps.setInt(9, obj.getNewWaterNumber());
            ps.setInt(10, obj.getElectricPrice());
            ps.setInt(11, obj.getWaterPrice());
            ps.setInt(12, obj.getGarbage());
            ps.setInt(13, obj.getWifi());
            ps.setInt(14, obj.getVehicle());
            ps.setInt(15, obj.getTotal());
            ps.setString(16, obj.getWasPaid());
            ps.setString(17, obj.getInvoiceId());
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
                    "dateCreated=?, paymentYear=?, paymentMonth=?, formerElectricNumber=?, newElectricNumber=?," +
                    "formerWaterNumber=?, newWaterNumber=?, electricPrice=?, waterPrice=?, garbage=?, wifi=?, vehicle=?," +
                    "total=?, wasPaid=? WHERE (invoiceId=?);";
            PreparedStatement ps = myConnection.prepareStatement(query);
            ps.setString(1, values[1]);
            ps.setInt(2, Integer.parseInt(values[2]));
            ps.setDate(3, Date.valueOf(Configs.stringToDate(values[3])));
            ps.setInt(4, Integer.parseInt(values[4]));
            ps.setInt(5, Integer.parseInt(values[5]));
            ps.setInt(6, Integer.parseInt(values[6]));
            ps.setInt(7, Integer.parseInt(values[7]));
            ps.setInt(8, Integer.parseInt(values[8]));
            ps.setInt(9, Integer.parseInt(values[9]));
            ps.setInt(10, Integer.parseInt(values[10]));
            ps.setInt(11, Integer.parseInt(values[11]));
            ps.setInt(12, Integer.parseInt(values[12]));
            ps.setInt(13, Integer.parseInt(values[13]));
            ps.setInt(14, Integer.parseInt(values[14]));
            ps.setInt(15, Integer.parseInt(values[15]));
            ps.setString(16, values[16]);
            ps.setString(17, values[0]);
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
                    rs.getInt("defaultRoomPrice"), rs.getDate("dateCreated"),
                    rs.getInt("paymentYear"), rs.getInt("paymentMonth"),
                    rs.getInt("formerElectricNumber"), rs.getInt("newElectricNumber"),
                    rs.getInt("formerWaterNumber"), rs.getInt("newWaterNumber"),
                    rs.getInt("electricPrice"), rs.getInt("waterPrice"),
                    rs.getInt("garbage"), rs.getInt("wifi"), rs.getInt("vehicle"),
                    rs.getInt("total"), rs.getString("wasPaid"));
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
                        rs.getInt("defaultRoomPrice"), rs.getDate("dateCreated"),
                        rs.getInt("paymentYear"), rs.getInt("paymentMonth"),
                        rs.getInt("formerElectricNumber"), rs.getInt("newElectricNumber"),
                        rs.getInt("formerWaterNumber"), rs.getInt("newWaterNumber"),
                        rs.getInt("electricPrice"), rs.getInt("waterPrice"),
                        rs.getInt("garbage"), rs.getInt("wifi"), rs.getInt("vehicle"),
                        rs.getInt("total"), rs.getString("wasPaid")));
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
                        rs.getInt("defaultRoomPrice"), rs.getDate("dateCreated"),
                        rs.getInt("paymentYear"), rs.getInt("paymentMonth"),
                        rs.getInt("formerElectricNumber"), rs.getInt("newElectricNumber"),
                        rs.getInt("formerWaterNumber"), rs.getInt("newWaterNumber"),
                        rs.getInt("electricPrice"), rs.getInt("waterPrice"),
                        rs.getInt("garbage"), rs.getInt("wifi"), rs.getInt("vehicle"),
                        rs.getInt("total"), rs.getString("wasPaid")));
            }
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DB_connection.closeMMDBConnection(myConnection);
        }
        return null;
    }

    public HashMap<String, String> selectLastInvoice(String roomId) {
        Connection myConnection = DB_connection.getMMDBConnection();
        try {
            PreparedStatement ps = myConnection.prepareStatement("SELECT roomId, paymentYear, paymentMonth, "
                    +"newElectricNumber, newWaterNumber, garbage, wifi, vehicle FROM Invoice WHERE roomId=\""
                    + roomId + "\" ORDER BY paymentYear DESC, paymentMonth DESC LIMIT 1");
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                HashMap<String, String> result = new HashMap<>();
                result.put("roomId", rs.getString("roomId"));
                result.put("paymentYear", Integer.toString(rs.getInt("paymentYear")));
                result.put("paymentMonth", Integer.toString(rs.getInt("paymentMonth")));
                result.put("newElectricNumber", Integer.toString(rs.getInt("newElectricNumber")));
                result.put("newWaterNumber", Integer.toString(rs.getInt("newWaterNumber")));
                result.put("garbage", Integer.toString(rs.getInt("garbage")));
                result.put("wifi", Integer.toString(rs.getInt("wifi")));
                result.put("vehicle", Integer.toString(rs.getInt("vehicle")));
                return result;
            } else {
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DB_connection.closeMMDBConnection(myConnection);
        }
        return null;
    }
}