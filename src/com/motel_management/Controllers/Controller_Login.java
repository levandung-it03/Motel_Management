package com.motel_management.Controllers;

import com.motel_management.DataAccessObject.AccountDAO;
import com.motel_management.Models.AccountModel;

import java.util.ArrayList;

public class Controller_Login {
    // Constructor
    public Controller_Login() { super(); }

    public static String validate(String username, String password) {
        ArrayList<AccountModel> a = AccountDAO.getInstance().selectByCondition("WHERE (username=\"" + username + "\")");
        if (!a.isEmpty() && a.get(0).getPassword().equals(password))
            return a.get(0).getName();
        else
            return null;
    }
}
