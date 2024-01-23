package com.motel_management.DataAccessObject;
import com.motel_management.DB_interaction.DB_connection;
import com.motel_management.Models.ContractModel;
import com.motel_management.Views.Configs;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;

public class ContractDAO implements DAOInterface<ContractModel>{
    private final SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

    public ContractDAO() {}
    public static ContractDAO getInstance() {return new ContractDAO();}

    @Override
    public int insert (ContractModel obj) {
        Connection myConnection = DB_connection.getMMDBConnection();
        try {
            String query = "INSERT INTO Contract VALUES (?, ?, ? ,? ,? ,?, ?, ?, ?, ?)";
            PreparedStatement ps = myConnection.prepareStatement(query);
            ps.setString(1, obj.getContractId());
            ps.setString(2,obj.getIdentifier());
            ps.setString(3, obj.getRoomId());
            ps.setInt(4, obj.getQuantity());
            ps.setInt(5, obj.getRoomDeposit());
            ps.setBoolean(6, obj.getIsFamily());
            ps.setDate(7, obj.getStartingDate());
            ps.setDate(8, obj.getEndingDate());
            ps.setBoolean(9, obj.getIsRegisteredPerAddress());
            ps.setBoolean(10, obj.getCheckedOut());
            return ps.executeUpdate(query);
        } catch (SQLException e) {
            e.fillInStackTrace();
        } finally {
            DB_connection.closeMMDBConnection(myConnection);
        }
        return -1;
    }

    public int insert (String[] values) {
        Connection myConnection = DB_connection.getMMDBConnection();
        try {
            String query = "INSERT INTO Contract VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = myConnection.prepareStatement(query);
            ps.setString(1, values[0]);
            ps.setString(2,values[1]);
            ps.setString(3,values[2]);
            ps.setInt(4, Integer.parseInt(values[3]));
            ps.setInt(5, Integer.parseInt(values[4]));
            ps.setBoolean(6, values[5].equals("1"));
            ps.setDate(7, Date.valueOf(Configs.stringToDate(values[6])));
            ps.setDate(8, Date.valueOf(Configs.stringToDate(values[7])));
            ps.setBoolean(9, values[8].equals("1"));
            ps.setBoolean(10, values[9].equals("1"));
            return ps.executeUpdate();
        } catch (SQLException e) {
            e.fillInStackTrace();
        } finally {
            DB_connection.closeMMDBConnection(myConnection);
        }
        return -1;
    }

    @Override
    public int update (ContractModel obj) {
        Connection myConnection = DB_connection.getMMDBConnection();
        try {
            String query = """
                    UPDATE Contract SET identifier=?, quantity=?, roomId=? ,roomDeposit=?, isFamily=?,
                    startingDate=?, endingDate=?, isRegisteredPerAddress=? checkedOut=? WHERE (contractId=?);""";
            PreparedStatement ps = myConnection.prepareStatement(query);

            ps.setString(1,obj.getIdentifier());
            ps.setInt(2, obj.getQuantity());
            ps.setString(3, obj.getRoomId());
            ps.setInt(4, obj.getRoomDeposit());
            ps.setBoolean(5, obj.getIsFamily());
            ps.setDate(6, obj.getStartingDate());
            ps.setDate(7, obj.getEndingDate());
            ps.setBoolean(8, obj.getIsRegisteredPerAddress());
            ps.setBoolean(9, obj.getCheckedOut());
            ps.setString(10, obj.getContractId());
            return ps.executeUpdate();
        } catch (SQLException e) {
            e.fillInStackTrace();
        } finally {
            DB_connection.closeMMDBConnection(myConnection);
        }
        return -1;
    }

    // Overload
    public int update(String[] values) {
        Connection myConnection = DB_connection.getMMDBConnection();
        try {
            String query = """
                    UPDATE Contract SET identifier=?, quantity=?, roomId=? ,roomDeposit=?, isFamily=?,
                    startingDate=?, endingDate=?, isRegisteredPerAddress=? checkedOut=? WHERE (contractId=?);""";
            PreparedStatement ps = myConnection.prepareStatement(query);

            ps.setString(1, values[1]);
            ps.setInt(2, Integer.parseInt(values[2]));
            ps.setString(3, values[3]);
            ps.setInt(4, Integer.parseInt(values[4]));
            ps.setBoolean(5, values[5].equals("1"));
            ps.setDate(6, Date.valueOf(Configs.stringToDate(values[6])));
            ps.setDate(7, Date.valueOf(Configs.stringToDate(values[7])));
            ps.setBoolean(8, values[8].equals("1"));
            ps.setBoolean(9, values[9].equals("1"));
            ps.setString(10, values[0]);
            return ps.executeUpdate();
        } catch (SQLException e) {
            e.fillInStackTrace();
        } finally {
            DB_connection.closeMMDBConnection(myConnection);
        }
        return -1;
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
            String query = "SELECT * FROM Contract WHERE contractId=?";
            PreparedStatement ps = myConnection.prepareStatement(query);
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next())
                return new ContractModel(rs.getString("contractId"), rs.getString("identifier"),
                        rs.getString("roomId"), rs.getInt("quantity"), rs.getInt("roomDeposit"),
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
                        rs.getString("roomId"), rs.getInt("quantity"), rs.getInt("roomDeposit"),
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
                        rs.getString("roomId"), rs.getInt("quantity"), rs.getInt("roomDeposit"),
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

    public ArrayList<HashMap<String, String>> selectAllPersonWithContractTableFormat(String contractConditionQuery) {
        Connection myConnection = DB_connection.getMMDBConnection();
        try {
            ResultSet rs = myConnection.prepareStatement(
                    """
                    SELECT Contract.*, SimplePerson.lastName, SimplePerson.firstName FROM Contract
                    INNER JOIN (SELECT Person.identifier, lastName, firstName FROM Person) AS SimplePerson
                    ON SimplePerson.identifier = Contract.identifier\s""" + contractConditionQuery
            ).executeQuery();

            ArrayList<HashMap<String, String>> result = new ArrayList<>();
            while (rs.next()) {
                HashMap<String, String> tempMap = new HashMap<>();
                tempMap.put("contractId", rs.getString("contractId"));
                tempMap.put("identifier", rs.getString("identifier"));
                tempMap.put("roomId", rs.getString("roomId"));
                tempMap.put("quantity", Integer.toString(rs.getInt("quantity")));
                tempMap.put("roomDeposit", Integer.toString(rs.getInt("roomDeposit")));
                tempMap.put("isFamily", Boolean.toString(rs.getBoolean("isFamily")));
                tempMap.put("startingDate", dateFormat.format(rs.getDate("startingDate")));
                tempMap.put("endingDate", dateFormat.format(rs.getDate("endingDate")));
                tempMap.put("checkedOut", Boolean.toString(rs.getBoolean("checkedOut")));
                tempMap.put("isRegisteredPerAddress", Boolean.toString(rs.getBoolean("isRegisteredPerAddress")));
                tempMap.put("fullName", rs.getString("lastName") + " " + rs.getString("firstName"));
                result.add(tempMap);
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
            PreparedStatement ps = myConnection.prepareStatement("""
                            SELECT * FROM Contract
                            WHERE (roomId=? AND startingDate=(SELECT MAX(startingDate) FROM Contract WHERE roomId=?))""");
            ps.setString(1, roomId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new ContractModel(rs.getString("contractId"), rs.getString("identifier"),
                        rs.getString("roomId"), rs.getInt("quantity"), rs.getInt("roomDeposit"),
                        rs.getBoolean("isFamily"), rs.getDate("startingDate"),
                        rs.getDate("endingDate"), rs.getBoolean("checkedOut"),
                        rs.getBoolean("isRegisteredPerAddress"));
            }
        } catch (SQLException e) {
            e.fillInStackTrace();
        } finally {
            DB_connection.closeMMDBConnection(myConnection);
        }
        return null;
    }
}