package com.motel_management.DataAccessObject;
import com.motel_management.DB_interaction.DB_connection;
import com.motel_management.Models.ContractModel;
import com.motel_management.Views.Configs;

import java.sql.*;
import java.util.ArrayList;

public class ContractDAO implements DAOInterface<ContractModel>{
    public ContractDAO() {}
    public static ContractDAO getInstance() {return new ContractDAO();}

    @Override
    public int insert (ContractModel obj) {
        Connection myConnection = DB_connection.getMMDBConnection();
        try {
            String query = "INSERT INTO Contract VALUES (?, ?, ? ,? ,? ,?, ?, ?, ?)";
            PreparedStatement ps = myConnection.prepareStatement(query);
            ps.setString(1, obj.getContractId());
            ps.setString(2,obj.getIdentifier());
            ps.setInt(3, obj.getQuantity());
            ps.setInt(4, obj.getRoomDeposit());
            ps.setBoolean(5, obj.getIsFamily());
            ps.setDate(6, obj.getStartingDate());
            ps.setDate(7, obj.getEndingDate());
            ps.setBoolean(8, obj.getIsRegisteredPerAddress());
            ps.setBoolean(9, obj.getCheckedOut());
            return ps.executeUpdate(query);
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
            String query = "INSERT INTO Contract VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = myConnection.prepareStatement(query);
            ps.setString(1, values[0]);
            ps.setString(2,values[1]);
            ps.setInt(3, Integer.parseInt(values[2]));
            ps.setInt(4, Integer.parseInt(values[3]));
            ps.setBoolean(5, values[4].equals("1"));
            ps.setDate(6,Date.valueOf(Configs.stringToDate(values[5])));
            ps.setDate(7, Date.valueOf(Configs.stringToDate(values[6])));
            ps.setBoolean(8, values[8].equals("1"));
            ps.setBoolean(9, values[8].equals("1"));
            return ps.executeUpdate();
        } catch (SQLException e) {
            e.fillInStackTrace();
        } finally {
            DB_connection.closeMMDBConnection(myConnection);
        }
        return 0;
    }

    @Override
    public int update (ContractModel obj) {
        Connection myConnection = DB_connection.getMMDBConnection();
        try {
            String query = "UPDATE Contract SET identifier=?, quantity=? ,roomDeposit=?, isFamily=?, startingDate=?," +
                    "endingDate=?, isRegisteredPerAddress=? checkedOut=? WHERE (contractId=?);";
            PreparedStatement ps = myConnection.prepareStatement(query);

            ps.setString(1,obj.getIdentifier());
            ps.setInt(2, obj.getQuantity());
            ps.setInt(3, obj.getRoomDeposit());
            ps.setBoolean(4, obj.getIsFamily());
            ps.setDate(5, obj.getStartingDate());
            ps.setDate(6, obj.getEndingDate());
            ps.setBoolean(7, obj.getIsRegisteredPerAddress());
            ps.setBoolean(8, obj.getCheckedOut());
            ps.setString(9, obj.getContractId());
            return ps.executeUpdate();
        } catch (SQLException e) {
            e.fillInStackTrace();
        } finally {
            DB_connection.closeMMDBConnection(myConnection);
        }
        return 0;
    }

    // Overload
    public int update(String[] values) {
        Connection myConnection = DB_connection.getMMDBConnection();
        try {
            String query = "UPDATE Contract SET identifier=?, quantity=? ,roomDeposit=?, isFamily=?, startingDate=?," +
                    "endingDate=?, isRegisteredPerAddress=? checkedOut=? WHERE (contractId=?);";
            PreparedStatement ps = myConnection.prepareStatement(query);

            ps.setString(1,values[1]);
            ps.setInt(2, Integer.parseInt(values[2]));
            ps.setInt(3, Integer.parseInt(values[3]));
            ps.setBoolean(4, values[4].equals("1"));
            ps.setDate(5,Date.valueOf(Configs.stringToDate(values[5])));
            ps.setDate(6, Date.valueOf(Configs.stringToDate(values[6])));
            ps.setBoolean(7, values[7].equals("1"));
            ps.setBoolean(8, values[8].equals("1"));
            ps.setString(9, values[0]);
            return ps.executeUpdate();
        } catch (SQLException e) {
            e.fillInStackTrace();
        } finally {
            DB_connection.closeMMDBConnection(myConnection);
        }
        return 0;
    }

    public int updateContractStatus(String[] values) {
        Connection myConnection = DB_connection.getMMDBConnection();
        try {
            String query = "UPDATE Contract SET checkedOut=? WHERE (contractId=?);";
            PreparedStatement ps = myConnection.prepareStatement(query);

            ps.setBoolean(1,values[0].equals("1"));
            ps.setString(2, values[1]);
            return ps.executeUpdate();
        } catch (SQLException e) {
            e.fillInStackTrace();
        } finally {
            DB_connection.closeMMDBConnection(myConnection);
        }
        return 0;
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
            e.fillInStackTrace();
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
            if (rs.next())
                return new ContractModel(rs.getString("contractId"), rs.getString("identifier"),
                    rs.getInt("quantity"), rs.getInt("roomDeposit"),
                    rs.getBoolean("isFamily"), rs.getDate("startingDate"),
                    rs.getDate("endingDate"), rs.getBoolean("checkedOut"),
                    rs.getBoolean("isRegisteredPerAddress"));
            else
                return null;
        } catch (SQLException e) {
            e.fillInStackTrace();
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
                result.add(new ContractModel(rs.getString("contractId"), rs.getString("identifier"),
                        rs.getInt("quantity"), rs.getInt("roomDeposit"),
                        rs.getBoolean("isFamily"), rs.getDate("startingDate"),
                        rs.getDate("endingDate"), rs.getBoolean("checkedOut"),
                        rs.getBoolean("isRegisteredPerAddress")));
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
    public ArrayList<ContractModel> selectByCondition(String condition) {
        Connection myConnection = DB_connection.getMMDBConnection();
        try {
            PreparedStatement ps = myConnection.prepareStatement("SELECT * FROM Contract " + condition);
            ArrayList<ContractModel> result = new ArrayList<>();
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                result.add(new ContractModel(rs.getString("contractId"), rs.getString("identifier"),
                        rs.getInt("quantity"), rs.getInt("roomDeposit"),
                        rs.getBoolean("isFamily"), rs.getDate("startingDate"),
                        rs.getDate("endingDate"), rs.getBoolean("checkedOut"),
                        rs.getBoolean("isRegisteredPerAddress")));
            }
            return result;
        } catch (SQLException e) {
            e.fillInStackTrace();
        } finally {
            DB_connection.closeMMDBConnection(myConnection);
        }
        return null;
    }

    public ContractModel selectLastContractByRoomId(String roomId) {
        Connection myConnection = DB_connection.getMMDBConnection();
        try {
            PreparedStatement ps = myConnection.prepareStatement(
                    "SELECT Contract.*, SimplePerson.roomId FROM Contract" +
                    "INNER JOIN (SELECT Person.identifier, roomId FROM Person WHERE roomId=?) AS SimplePerson" +
                    "ON SimplePerson.identifier = Contract.identifier" +
                    "ORDER BY Contract.startingDate DESC LIMIT 1"
            );
            ps.setString(1, roomId);
            ResultSet rs = ps.executeQuery();
            if (rs.next())
                return new ContractModel(rs.getString("contractId"),rs.getString("identifier"),
                        rs.getInt("quantity"), rs.getInt("roomDeposit"),
                        rs.getBoolean("isFamily"), rs.getDate("startingDate"),
                        rs.getDate("endingDate"), rs.getBoolean("checkedOut"),
                        rs.getBoolean("isRegisteredPerAddress"));
            else
                return null;
        } catch (SQLException e) {
            e.fillInStackTrace();
        } finally {
            DB_connection.closeMMDBConnection(myConnection);
        }
        return null;
    }
}
