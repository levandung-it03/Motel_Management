package com.motel_management.Controllers;

import com.motel_management.DataAccessObject.AccountDAO;
import com.motel_management.Models.Account;

import java.util.ArrayList;

public class Controller_Login {
    public static String validate(String username, String password) {
        ArrayList<Account> a = AccountDAO.getInstance().selectByCondition("WHERE (username=\"" + username + "\")");
        if (!a.isEmpty() && a.get(0).getPassword().equals(password))
            return a.get(0).getName();
        else
            return null;
    }
}
