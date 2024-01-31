package com.motel_management.DataAccessObject;

import com.motel_management.DB_interaction.DB_connection;
import com.motel_management.Configs;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PersonDAO extends PersonStereoTypeDAO {
    public PersonDAO() { super("Person"); }

    public static PersonDAO getInstance() { return new PersonDAO(); }

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
}
