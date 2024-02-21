package com.motel_management.Controllers;

import at.favre.lib.crypto.bcrypt.BCrypt;
import com.motel_management.DataAccessObject.AccountDAO;
import com.motel_management.Models.AccountModel;

public class Controller_Login {
    // Constructor
    public Controller_Login() { super(); }

    public static String validate(String username, String password) {
        AccountModel account = AccountDAO.getInstance().selectByUsername(username);
        if (account == null)
            return null;

//        String bcryptHashString = BCrypt.withDefaults().hashToString(10, password.toCharArray());
        BCrypt.Result result = BCrypt.verifyer().verify(password.toCharArray(), account.getPassword());
         if(!result.verified)
            return null;

        return account.getName();
    }
}
