package com.motel_management.Controllers;

import at.favre.lib.crypto.bcrypt.BCrypt;
import com.motel_management.DataAccessObject.AccountDAO;
import com.motel_management.Models.AccountModel;

import java.util.ArrayList;
import java.util.HashMap;

public class Controller_ChangePassword {
    public Controller_ChangePassword() { super(); }

    public static HashMap<String, String> changePassword(String username, String oldPassword, String newPass) {
        HashMap<String, String> result = new HashMap<>();
        AccountModel account = AccountDAO.getInstance().selectByUsername(username);

        if (account == null) {
            result.put("result", "0");
            result.put("message", "Username Is Not Existed!");
            return result;
        }

        BCrypt.Result verifyOldPassResult = BCrypt.verifyer().verify(oldPassword.toCharArray(), account.getPassword());
        if (!verifyOldPassResult.verified) {
            result.put("result", "0");
            result.put("message", "Old Password Is Not Correct!");
            return result;
        }

        String hashingNewPassword = BCrypt.withDefaults().hashToString(10, newPass.toCharArray());
        account.setPassword(hashingNewPassword);

        if (AccountDAO.getInstance().update(account) != 0) {
            result.put("result", "1");
            result.put("message", "Change Password Successfully!");
            return result;
        } else {
            result.put("result", "1");
            result.put("message", "Something Wrong!");
            return result;
        }
    }
}
