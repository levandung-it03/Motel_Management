package com.motel_management.DataAccessObject;
import com.motel_management.DB_interaction.DB_connection;
import com.motel_management.Models.ContractModel;
import com.motel_management.Views.Configs;


import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class ContractDAO implements DAOInterface<ContractModel>{
    public ContractDAO() {}
    public static ContractDAO getInstance() {return new ContractDAO();}

    @Override
    public int insert (ContractModel obj) {
        Connection myConnection = DB_connection.getMMDBConnection();
        try {
            String query = "INSERT INTO Contract VALUES (?, ?, ? ,? ,? ,?, ?)";
            PreparedStatement ps = myConnection.prepareStatement(query);
            ps.setString(1, obj.getContractId());
            ps.setString(2,obj.getIdentifier());
            ps.setString(3, obj.getRoomId());
            ps.setInt(4, obj.getQuantity());
            ps.setInt(5, obj.getRoomDeposit());
            ps.setDate(6, obj.getStartingDate());
            ps.setDate(7, obj.getEndingDate());
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
            String query = "INSERT INTO Contract VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = myConnection.prepareStatement(query);
            ps.setString(1, values[0]);
            ps.setString(2,values[1]);
            ps.setString(3, values[2]);
            ps.setInt(4, Integer.parseInt(values[3]));
            ps.setInt(5, Integer.parseInt(values[4]));
            ps.setDate(6,Date.valueOf(Configs.stringToDate(values[5])));
            ps.setDate(7, Date.valueOf(Configs.stringToDate(values[6])));
            return ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DB_connection.closeMMDBConnection(myConnection);
        }
        return 0;
    }

    @Override
    public int update (ContractModel obj) {
        Connection myConnection = DB_connection.getMMDBConnection();
        try {
            String query = "UPDATE Contract SET identifier=?, roomId=?, quantity=? " +
                    "roomDeposit=?, startingDate=? + endingDate=? WHERE (contractId=?);";
            PreparedStatement ps = myConnection.prepareStatement(query);

            ps.setString(1,obj.getIdentifier());
            ps.setString(2, obj.getRoomId());
            ps.setInt(3, obj.getQuantity());
            ps.setInt(4, obj.getRoomDeposit());
            ps.setDate(5, obj.getStartingDate());
            ps.setDate(6, obj.getEndingDate());
            ps.setString(7, obj.getContractId());
            return ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DB_connection.closeMMDBConnection(myConnection);
        }
    }

    // Overload
    public int update(String[] values) {             //-------------------------------------------------------------
        Connection myConnection = DB_connection.getMMDBConnection();
        try {
            String query = "UPDATE Contract SET identifier=?, roomId=?, quantity=? " +
                    "roomDeposit=?, startingDate=? + endingDate=? WHERE (contractId=?);";
            PreparedStatement ps = myConnection.prepareStatement(query);

            ps.setString(1,values[1]);
            ps.setString(2, values[2]);
            ps.setInt(3, Integer.parseInt(values[3]));
            ps.setInt(4, Integer.parseInt(values[4]));
            ps.setDate(5,Date.valueOf(Configs.stringToDate(values[5])));
            ps.setDate(6, Date.valueOf(Configs.stringToDate(values[6])));
            ps.setString(7, values[0]);
            return ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DB_connection.closeMMDBConnection(myConnection);
        }
    }

    @Override
    public int delete (String id) {
        Connection myConnection = DB_connection.getMMDBConnection();
        try {
            String query = "DELETE FROM Contract WHERE contractId=?";
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
    public ContractModel selectById (String id) {
        Connection myConnection = DB_connection.getMMDBConnection();
        try {
            String query = ("SELECT * FROM Contract WHERE (contractId=?)");
            PreparedStatement ps = myConnection.prepareStatement(query);
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();
            rs.next();
            return new ContractModel(rs.getString("contractId"),rs.getString("identifier"),
                    rs.getString("roomId"), rs.getInt("quantity"),
                    rs.getInt("roomDeposit"), rs.getDate("startingDate"),
                    rs.getDate("endingDate"));
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DB_connection.closeMMDBConnection(myConnection);
        }
        return null;
    }

    @Override
    public ArrayList<ContractModel> selectAll () {
        Connection myConnection = DB_connection.getMMDBConnection();
        try {
            PreparedStatement ps = myConnection.prepareStatement("SELECT * FROM Contract");
            ArrayList<ContractModel> result = new ArrayList<>();
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                result.add(new ContractModel(rs.getString("contractId"),rs.getString("identifier"),
                        rs.getString("roomId"), rs.getInt("quantity"),
                        rs.getInt("roomDeposit"), rs.getDate("startingDate"),
                        rs.getDate("endingDate")));
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
    public ArrayList<ContractModel> selectByCondition(String condition) {
        Connection myConnection = DB_connection.getMMDBConnection();
        try {
            PreparedStatement ps = myConnection.prepareStatement("SELECT * FROM Contract " + condition);
            ArrayList<ContractModel> result = new ArrayList<>();
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                result.add(new ContractModel(rs.getString("contractId"),rs.getString("identifier"),
                        rs.getString("roomId"), rs.getInt("quantity"),
                        rs.getInt("roomDeposit"), rs.getDate("startingDate"),
                        rs.getDate("endingDate")));
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
