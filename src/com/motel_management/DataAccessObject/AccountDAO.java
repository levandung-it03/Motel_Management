package com.motel_management.DataAccessObject;
import com.motel_management.Models.Account;
import com.motel_management.DB_interaction.DB_connection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class AccountDAO implements DAOInterface<Account> {

    public AccountDAO() { }
    public static AccountDAO getInstance() {
        return new AccountDAO();
    }

    @Override
    public int delete(Account obj) {
        return 0;
    }

    @Override
    public int insert(Account obj) {
        return 0;
    }

    @Override
    public int update(Account obj) {
        return 0;
    }

    @Override
    public Account selectById(String id) {
        Connection myConnection = DB_connection.getMMDBConnection();
        try {
            Statement st = myConnection.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM Account WHERE (userId=\"" + id + "\")");
            rs.next();
            return new Account(rs.getString("userId"), rs.getString("name"),
                    rs.getString("username"), rs.getString("password"));
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DB_connection.closeMMDBConnection();
        }
        return null;
    }

    @Override
    public ArrayList<Account> selectAll() {
        return null;
    }

    @Override
    public ArrayList<Account> selectByCondition(String condition) {
        return null;
    }
}
