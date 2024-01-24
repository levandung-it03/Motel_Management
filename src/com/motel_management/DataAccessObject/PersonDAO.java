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
            ps.setBoolean(12, obj.getIsOccupied());
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
            String query = "INSERT INTO Person VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = myConnection.prepareStatement(query);
            ps.setString(1, values[0]);
            ps.setString(2, values[1]);
            ps.setString(3, values[2]);
            ps.setDate(4, Date.valueOf(Configs.stringToDate(values[3])));
            ps.setString(5, values[4]);
            ps.setString(6, values[5]);
            ps.setString(7,values[6]);
            ps.setString(8, values[7]);
            ps.setString(9, values[8]);
            ps.setString(10, values[9]);
            ps.setString(11, values[10]);
            ps.setBoolean(12, values[11].equals("1"));
            return ps.executeUpdate();
        } catch (SQLException e) {
            e.fillInStackTrace();
        } finally {
            DB_connection.closeMMDBConnection(myConnection);
        }
        return 0;
    }

    @Override
    public int update (PersonModel obj) {
        Connection myConnection = DB_connection.getMMDBConnection();
        try {
            String query = """
                    UPDATE Person SET lastName=?, firstName=?, birthday=?, phone=?, gender=?, jobTitle=?,
                    permanentAddress=?, email=?, bankAccountNumber=?, bank=?, isOccupied=? WHERE (identifier=?);""";
            PreparedStatement ps = myConnection.prepareStatement(query);
            ps.setString(1, obj.getLastName());
            ps.setString(2, obj.getFirstName());
            ps.setDate(3, obj.getBirthday());
            ps.setString(4, obj.getPhone());
            ps.setString(5,obj.getGender());
            ps.setString(6,obj.getJobTitle());
            ps.setString(7, obj.getPermanentAddress());
            ps.setString(8, obj.getEmail());
            ps.setString(9, obj.getBankAccountNumber());
            ps.setString(10, obj.getBank());
            ps.setBoolean(11, obj.getIsOccupied());
            ps.setString(12, obj.getIdentifier());
            return ps.executeUpdate();
        } catch (SQLException e) {
            e.fillInStackTrace();
        } finally {
            DB_connection.closeMMDBConnection(myConnection);
        }
        return 0;
    }
    // OverLOAD
    public int update(String[] values) {
        Connection myConnection = DB_connection.getMMDBConnection();
        try {
            String query = """
                    UPDATE Person SET lastName=?, firstName=?, birthday=?, phone=?, gender=?, jobTitle=?,
                    permanentAddress=?, email=?, bankAccountNumber=?, bank=?, isOccupied=? WHERE (identifier=?);""";
            PreparedStatement ps = myConnection.prepareStatement(query);
            ps.setString(1, values[1]);
            ps.setString(2, values[2]);
            ps.setDate(3, Date.valueOf(Configs.stringToDate(values[3])));
            ps.setString(4, values[4]);
            ps.setString(5, values[5]);
            ps.setString(6,values[6]);
            ps.setString(7, values[7]);
            ps.setString(8, values[8]);
            ps.setString(9, values[9]);
            ps.setString(10, values[10]);
            ps.setBoolean(11, values[11].equals("1"));
            ps.setString(12, values[0]);
            return ps.executeUpdate();
        } catch (SQLException e) {
            e.fillInStackTrace();
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
            e.fillInStackTrace();
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
            ps.setBoolean(1, values[0].equals("1"));
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
    public int delete(String id) {
        Connection myConnection = DB_connection.getMMDBConnection();
        try {
            String query = "DELETE FROM Person WHERE identifier=?";
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
    public PersonModel selectById(String id) {
        Connection myConnection = DB_connection.getMMDBConnection();
        try {
            String query = ("""
                    SELECT Person.*, roomId FROM Person
                    INNER JOIN (SELECT Contract.identifier, roomId FROM Contract) AS SimpleContract
                    ON Person.identifier = SimpleContract.identifier
                    WHERE (identifier=?)""");
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
                        rs.getBoolean("isOccupied"));
            } else {
                return null;
            }
        } catch (SQLException e) {
            e.fillInStackTrace();
        } finally {
            DB_connection.closeMMDBConnection(myConnection);
        }
        return null;
    }

    @Override
    public ArrayList<PersonModel> selectAll() {
        Connection myConnection = DB_connection.getMMDBConnection();
        try {
            PreparedStatement ps = myConnection.prepareStatement("""
                    SELECT Person.*, roomId FROM Person
                    INNER JOIN (SELECT Contract.identifier, roomId FROM Contract) AS SimpleContract
                    ON Person.identifier = SimpleContract.identifier""");
            ArrayList<PersonModel> result = new ArrayList<>();
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                result.add(new PersonModel(rs.getString("identifier"), rs.getString("roomId"),
                        rs.getString("lastName"),rs.getString("firstName"),
                        rs.getDate("birthday"),rs.getString("phone"),
                        rs.getString("gender"), rs.getString("jobTitle"),
                        rs.getString("permanentAddress"), rs.getString("email"),
                        rs.getString("bankAccountNumber"), rs.getString("bank"),
                        rs.getBoolean("isOccupied")));
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
    public ArrayList<PersonModel> selectByCondition(String condition) {
        Connection myConnection = DB_connection.getMMDBConnection();
        try {
            PreparedStatement ps = myConnection.prepareStatement("""
                    SELECT Person.*, roomId FROM Person
                    INNER JOIN (SELECT Contract.identifier, roomId FROM Contract) AS SimpleContract
                    ON Person.identifier = SimpleContract.identifier\s""" + condition);
            ArrayList<PersonModel> result = new ArrayList<>();
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                result.add(new PersonModel(rs.getString("identifier"), rs.getString("roomId"),
                        rs.getString("lastName"),rs.getString("firstName"),
                        rs.getDate("birthday"),rs.getString("phone"),
                        rs.getString("gender"), rs.getString("jobTitle"),
                        rs.getString("permanentAddress"), rs.getString("email"),
                        rs.getString("bankAccountNumber"), rs.getString("bank"),
                        rs.getBoolean("isOccupied")));
            }
            return result;
        } catch (SQLException e) {
            e.fillInStackTrace();
        } finally {
            DB_connection.closeMMDBConnection(myConnection);
        }
        return null;
    }

    public ArrayList<String[]> selectByInnerJoinContract(String condition) {
        Connection myConnection = DB_connection.getMMDBConnection();
        try {
            PreparedStatement ps = myConnection.prepareStatement("""
                    SELECT SimpleContract.roomId, Person.identifier, lastName, firstName, startingDate, endingDate, phone
                    FROM Person INNER JOIN (
                        SELECT roomId, Contract.identifier, startingDate, endingDate FROM Contract\s""" + condition +
                    ") AS SimpleContract ON Person.identifier = SimpleContract.identifier"
            );
            ArrayList<String[]> result = new ArrayList<>();
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                result.add(new String[] {rs.getString("roomId"), rs.getString("identifier"),
                        rs.getString("lastName"),rs.getString("firstName"),
                        rs.getString("phone"), Configs.simpleDateFormat.format(rs.getDate("startingDate")),
                        Configs.simpleDateFormat.format(rs.getDate("endingDate"))});
            }
            return result;
        } catch (SQLException e) {
            e.fillInStackTrace();
        } finally {
            DB_connection.closeMMDBConnection(myConnection);
        }
        return null;
    }

    public String selectRoomIdByIdentifier (String id) {
        Connection myConnection = DB_connection.getMMDBConnection();
        String res = "";
        try {
            String query = ("SELECT roomId FROM Contract WHERE (identifier=?)");
            PreparedStatement ps = myConnection.prepareStatement(query);
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();
            res = rs.getString("roomId");
            return res;
        } catch (SQLException e) {
            e.fillInStackTrace();
        } finally {
            DB_connection.closeMMDBConnection(myConnection);
        }
        return res;
    }
}