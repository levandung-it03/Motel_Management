package com.motel_management.DataAccessObject;
import com.motel_management.DB_interaction.DB_connection;
import com.motel_management.Models.ContractModel;


import java.sql.*;
import java.util.ArrayList;

public class ContractDAO implements DAOInterface<ContractModel>{
    public ContractDAO() {}
    public static ContractDAO getInstance() {return new ContractDAO();}

    @Override
    public int insert (ContractModel obj) {
        Connection myConnection = DB_connection.getMMDBConnection();
        try {
            String query = "INSERT INTO Contract VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? ,? ,? ,?, ?, ?)";
            PreparedStatement ps = myConnection.prepareStatement(query);
            ps.setString(1, obj.getContractId());
            ps.setString(2,obj.getIdentifier());
            ps.setString(3, obj.getLastName());
            ps.setString(4, obj.getFirstname());
            ps.setString(5, obj.getBirthday());
            ps.setString(6,obj.getPhone());
            ps.setString(7,obj.getGender());
            ps.setString(8,obj.getJobtitle());
            ps.setString(9, obj.getPermanentAddress());
            ps.setString(10, obj.getEmail());
            ps.setString(11, obj.getCreditCard());
            ps.setString(12, obj.getBank());
            ps.setString(13, obj.getRoomId());
            ps.setInt(14, obj.getQuantity());
            ps.setInt(15, obj.getRoomDeposit());
            ps.setDate(16, obj.getStartingDate());
            ps.setDate(17, obj.getEndingDate());
            return ps.executeUpdate(query);
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
            String query = "UPDATE Contract SET identifier=?, " +
                    "lastName=? ,firstname=?,  birthday=?, phone=?, gender=?, jobtitle=? " +
                    "permanentAddress=?, email=?, creditCard=?, bank=?, roomId=?, quantity=? " +
                    "roomDeposit=?, startingDate=? + endingDate=? WHERE (contractId=?);";
            PreparedStatement ps = myConnection.prepareStatement(query);

            ps.setString(1,obj.getIdentifier());
            ps.setString(2, obj.getLastName());
            ps.setString(3, obj.getFirstname());
            ps.setString(4, obj.getBirthday());
            ps.setString(5,obj.getPhone());
            ps.setString(6,obj.getGender());
            ps.setString(7,obj.getJobtitle());
            ps.setString(8, obj.getPermanentAddress());
            ps.setString(9, obj.getEmail());
            ps.setString(10, obj.getCreditCard());
            ps.setString(11, obj.getBank());
            ps.setString(12, obj.getRoomId());
            ps.setInt(13, obj.getQuantity());
            ps.setInt(14, obj.getRoomDeposit());
            ps.setDate(15, obj.getStartingDate());
            ps.setDate(16, obj.getEndingDate());
            ps.setString(17, obj.getContractId());
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
            String query = "UPDATE Contract SET identifier=?, " +
                    "lastName=? ,firstname=?,  birthday=?, phone=?, gender=?, jobtitle=? " +
                    "permanentAddress=?, email=?, creditCard=?, bank=?, roomId=?, quantity=? " +
                    "roomDeposit=?, startingDate=? + endingDate=? WHERE (contractId=?);";
            PreparedStatement ps = myConnection.prepareStatement(query);

            ps.setString(1,values[1]);
            ps.setString(2, values[2]);
            ps.setString(3, values[3]);
            ps.setString(4, values[4]);
            ps.setString(5,values[5]);
            ps.setString(6, values[6]);
            ps.setString(7,values[7]);
            ps.setString(8, values[8]);
            ps.setString(9, values[9]);
            ps.setString(10, values[10]);
            ps.setString(11, values[11]);
            ps.setString(12, values[12]);
            ps.setInt(13, Integer.parseInt(values[13]));
            ps.setInt(14, Integer.parseInt(values[14]));
            ps.setDate(15,java.sql.Date.valueOf(values[15]));
            ps.setDate(16, java.sql.Date.valueOf(values[16]));
            ps.setString(17, values[0]);
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
                    rs.getString("lastName"), rs.getString("firstname"),
                    rs.getString("birthday"), rs.getString("phone"),
                    rs.getString("gender"), rs.getString("jobtitle"),
                    rs.getString("permanentAddress"), rs.getString("email"),
                    rs.getString("creditCard"), rs.getString("bank"),
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
                        rs.getString("lastName"), rs.getString("firstname"),
                        rs.getString("birthday"), rs.getString("phone"),
                        rs.getString("gender"), rs.getString("jobtitle"),
                        rs.getString("permanentAddress"), rs.getString("email"),
                        rs.getString("creditCard"), rs.getString("bank"),
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
                        rs.getString("lastName"), rs.getString("firstname"),
                        rs.getString("birthday"), rs.getString("phone"),
                        rs.getString("gender"), rs.getString("jobtitle"),
                        rs.getString("permanentAddress"), rs.getString("email"),
                        rs.getString("creditCard"), rs.getString("bank"),
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
