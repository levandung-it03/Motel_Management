package com.motel_management.DataAccessObject;
import com.motel_management.DB_interaction.DB_connection;
import com.motel_management.Models.PersonModel;
import com.motel_management.Views.Configs;


import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;

public class PersonDAO implements DAOInterface<PersonModel>{
    public PersonDAO() {}
    public static PersonDAO getInstance() {return new PersonDAO();}
    @Override
    public int insert(PersonModel obj) {
        Connection myConnection = DB_connection.getMMDBConnection();
        try {
            String query = "INSERT INTO Person VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
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
            ps.setString(13, obj.getIsOccupied());
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
            String query = "INSERT INTO Person VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
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
            ps.setString(13, values[12]);
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
                    "bank=?, isOccupied=? WHERE (identifier=?);";
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
            ps.setString(12, obj.getIsOccupied());
            ps.setString(13, obj.getIdentifier());
            return ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DB_connection.closeMMDBConnection(myConnection);
        }
        return 0;
    }
    // OverLOAD
    public int update(String[] values) {
        Connection myConnection = DB_connection.getMMDBConnection();
        try {
            String query = "UPDATE Person SET roomId=?, lastName=?, firstName=?,"+
                    "birthday=?, phone=?, gender=?, jobTitle=?, permanentAddress=?, email=?, bankAccountNumber=?," +
                    "bank=?, isOccupied=? WHERE (identifier=?);";
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
            ps.setString(12, values[12]);
            ps.setString(13, values[0]);
            return ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DB_connection.closeMMDBConnection(myConnection);
        }
        return 0;
    }

    public int updateDetails(String[] values) {
        Connection myConnection = DB_connection.getMMDBConnection();
        try {
            String query = "UPDATE Person SET email=?, phone=?, jobTitle=?," +
                    "bank=?, bankAccountNumber=?, permanentAddress=? WHERE (identifier=?);";
            PreparedStatement ps = myConnection.prepareStatement(query);
            ps.setString(1, values[1]);
            ps.setString(2, values[2]);
            ps.setString(3, values[3]);
            ps.setString(4, values[4]);
            ps.setString(5, values[5]);
            ps.setString(6, values[6]);
            ps.setString(7, values[0]);
            return ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DB_connection.closeMMDBConnection(myConnection);
        }
        return 0;
    }

    public int updatePersonStatus(String[] values) {
        Connection myConnection = DB_connection.getMMDBConnection();
        try {
            String query = "UPDATE Person SET isOccupied=? WHERE (identifier=?);";
            PreparedStatement ps = myConnection.prepareStatement(query);
            ps.setString(1, values[0]);
            ps.setString(2, values[1]);
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
            if (rs.next()) {
                return new PersonModel(rs.getString("identifier"), rs.getString("roomId"),
                        rs.getString("lastName"),rs.getString("firstName"),
                        rs.getDate("birthday"),rs.getString("phone"),
                        rs.getString("gender"), rs.getString("jobTitle"),
                        rs.getString("permanentAddress"), rs.getString("email"),
                        rs.getString("bankAccountNumber"), rs.getString("bank"),
                        rs.getString("isOccupied"));
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
                        rs.getString("bankAccountNumber"), rs.getString("bank"),
                        rs.getString("isOccupied")));
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
                        rs.getString("bankAccountNumber"), rs.getString("bank"),
                        rs.getString("isOccupied")));
            }
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DB_connection.closeMMDBConnection(myConnection);
        }
        return null;
    }

    public HashMap<String, String> selectAllNameById() {
        Connection myConnection = DB_connection.getMMDBConnection();
        try {
            ResultSet rs = myConnection.prepareStatement("SELECT identifier, lastName, firstName FROM Person").executeQuery();
            HashMap<String, String> result = new HashMap<>();
            while (rs.next()) {
                result.put(
                        rs.getString("identifier"),
                        rs.getString("lastName") + " " + rs.getString("firstName")
                );
            }
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DB_connection.closeMMDBConnection(myConnection);
        }
        return null;
    }

    public ArrayList<String[]> selectByInnerJoinContract(String condition) {
        Connection myConnection = DB_connection.getMMDBConnection();
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            PreparedStatement ps = myConnection.prepareStatement(
                    "SELECT roomId, Person.identifier, lastName, firstName, startingDate, endingDate, phone " +
                    "FROM Person INNER JOIN (" +
                            "SELECT identifier, startingDate, endingDate FROM Contract " + condition +
                    ") AS SimpleContract ON Person.identifier = SimpleContract.identifier"
            );
            ArrayList<String[]> result = new ArrayList<>();
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                result.add(new String[] {rs.getString("roomId"), rs.getString("identifier"),
                        rs.getString("lastName"),rs.getString("firstName"),
                        rs.getString("phone"), sdf.format(rs.getDate("startingDate")),
                        sdf.format(rs.getDate("endingDate"))});
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
