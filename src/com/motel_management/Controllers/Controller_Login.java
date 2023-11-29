package com.motel_management.Controllers;

import at.favre.lib.crypto.bcrypt.BCrypt;
import com.motel_management.DataAccessObject.AccountDAO;
import com.motel_management.Models.AccountModel;

import java.util.ArrayList;

public class Controller_Login {
    // Constructor
    public Controller_Login() { super(); }

    public static String validate(String username, String password) {
        if (username.isEmpty() || password.isEmpty())
            return null;
        ArrayList<AccountModel> account = AccountDAO.getInstance().selectByCondition("WHERE (username=\"" + username + "\")");

//        String bcryptHashString = BCrypt.withDefaults().hashToString(10, password.toCharArray());
        BCrypt.Result result = BCrypt.verifyer().verify(password.toCharArray(), account.get(0).getPassword());
        if (account.isEmpty() || !result.verified)
            return null;

        return account.get(0).getName();
    }
}
