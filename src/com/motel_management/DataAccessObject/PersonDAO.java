package com.motel_management.DataAccessObject;
import com.motel_management.DB_interaction.DB_connection;
import com.motel_management.Models.PersonModel;
import com.motel_management.Views.Configs;


import java.sql.*;
import java.util.ArrayList;

public class PersonDAO implements DAOInterface<PersonModel>{
    public PersonDAO() {}
    public static PersonDAO getInstance() {return new PersonDAO();}
    @Override
    public int insert(PersonModel obj) {
        Connection myConnection = DB_connection.getMMDBConnection();
        try {
            String query = "INSERT INTO Person VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = myConnection.prepareStatement(query);
            ps.setString(1, obj.getIdentifier());
            ps.setString(2, obj.getRoomId());
            ps.setString(3, obj.getLastName());
            ps.setString(4, obj.getFirstName());
            ps.setDate(5, obj.getBirthday());
            ps.setString(6, obj.getPhone());
            ps.setString(7,obj.getGender());
            ps.setString(8,obj.getJobTitle());
            ps.setString(9, obj.getPermanentAddress());
            ps.setString(10, obj.getEmail());
            ps.setString(11, obj.getBankAccountNumber());
            ps.setString(12, obj.getBank());
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
            String query = "INSERT INTO Person VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = myConnection.prepareStatement(query);
            ps.setString(1, values[0]);
            ps.setString(2, values[1]);
            ps.setString(3, values[2]);
            ps.setString(4, values[3]);
            ps.setDate(5, Date.valueOf(Configs.stringToDate(values[4])));
            ps.setString(6, values[5]);
            ps.setString(7, values[6]);
            ps.setString(8,values[7]);
            ps.setString(9, values[8]);
            ps.setString(10, values[9]);
            ps.setString(11, values[10]);
            ps.setString(12, values[11]);
            return ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DB_connection.closeMMDBConnection(myConnection);
        }
        return 0;
    }

    @Override
    public int update (PersonModel obj) {
        Connection myConnection = DB_connection.getMMDBConnection();
        try {
            String query = "UPDATE Person SET  roomId=?, lastName=?, firstName=?,"+
                    "birthday=?, phone=?, gender=?, jobTitle=?, permanentAddress=?, email=?, bankAccountNumber=?," +
                    "bank=? WHERE (identifier=?);";
            PreparedStatement ps = myConnection.prepareStatement(query);
            ps.setString(1, obj.getRoomId());
            ps.setString(2, obj.getLastName());
            ps.setString(3, obj.getFirstName());
            ps.setDate(4, obj.getBirthday());
            ps.setString(5, obj.getPhone());
            ps.setString(6,obj.getGender());
            ps.setString(7,obj.getJobTitle());
            ps.setString(8, obj.getPermanentAddress());
            ps.setString(9, obj.getEmail());
            ps.setString(10, obj.getBankAccountNumber());
            ps.setString(11, obj.getBank());
            ps.setString(12, obj.getIdentifier());
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
            String query = "UPDATE Person SET  roomId=?, lastName=?, firstName=?,"+
                    "birthday=?, phone=?, gender=?, jobTitle=?, permanentAddress=?, email=?, bankAccountNumber=?," +
                    "bank=? WHERE (identifier=?);";
            PreparedStatement ps = myConnection.prepareStatement(query);
            ps.setString(1, values[1]);
            ps.setString(2, values[2]);
            ps.setString(3, values[3]);
            ps.setDate(4, Date.valueOf(Configs.stringToDate(values[4])));
            ps.setString(5, values[5]);
            ps.setString(6, values[6]);
            ps.setString(7,values[7]);
            ps.setString(8, values[8]);
            ps.setString(9, values[9]);
            ps.setString(10, values[10]);
            ps.setString(11, values[11]);
            ps.setString(12, values[0]);
            return ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DB_connection.closeMMDBConnection(myConnection);
        }
    }

    @Override
    public int delete(String id) {
        Connection myConnection = DB_connection.getMMDBConnection();
        try {
            String query = "DELETE FROM Person WHERE identifier=?";
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
    public PersonModel selectById(String id) {
        Connection myConnection = DB_connection.getMMDBConnection();
        try {
            String query = ("SELECT * FROM Person WHERE (identifier=?)");
            PreparedStatement ps = myConnection.prepareStatement(query);
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();
            rs.next();
            return new PersonModel(rs.getString("identifier"), rs.getString("roomId"),
                    rs.getString("lastName"),rs.getString("firstName"),
                    rs.getDate("birthday"),rs.getString("phone"),
                    rs.getString("gender"), rs.getString("jobTitle"),
                    rs.getString("permanentAddress"), rs.getString("email"),
                    rs.getString("bankAccountNumber"), rs.getString("bank"));
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DB_connection.closeMMDBConnection(myConnection);
        }
        return null;
    }

    @Override
    public ArrayList<PersonModel> selectAll() {
        Connection myConnection = DB_connection.getMMDBConnection();
        try {
            PreparedStatement ps = myConnection.prepareStatement("SELECT * FROM Person");
            ArrayList<PersonModel> result = new ArrayList<>();
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                result.add(new PersonModel(rs.getString("identifier"), rs.getString("roomId"),
                        rs.getString("lastName"),rs.getString("firstName"),
                        rs.getDate("birthday"),rs.getString("phone"),
                        rs.getString("gender"), rs.getString("jobTitle"),
                        rs.getString("permanentAddress"), rs.getString("email"),
                        rs.getString("bankAccountNumber"), rs.getString("bank")));
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
    public ArrayList<PersonModel> selectByCondition(String condition) {
        Connection myConnection = DB_connection.getMMDBConnection();
        try {
            PreparedStatement ps = myConnection.prepareStatement("SELECT * FROM Person " + condition);
            ArrayList<PersonModel> result = new ArrayList<>();
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                result.add(new PersonModel(rs.getString("identifier"), rs.getString("roomId"),
                        rs.getString("lastName"),rs.getString("firstName"),
                        rs.getDate("birthday"),rs.getString("phone"),
                        rs.getString("gender"), rs.getString("jobTitle"),
                        rs.getString("permanentAddress"), rs.getString("email"),
                        rs.getString("bankAccountNumber"), rs.getString("bank")));
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