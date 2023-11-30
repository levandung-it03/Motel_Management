package com.motel_management.Controllers;

import at.favre.lib.crypto.bcrypt.BCrypt;
import com.motel_management.DataAccessObject.AccountDAO;
import com.motel_management.Models.AccountModel;

import java.util.ArrayList;
import java.util.HashMap;

public class Controller_ChangePassword {
    public Controller_ChangePassword() { super(); }

    public static HashMap<String, String> changePassword(String user, String oldPassword, String newPassAgain) {
        HashMap<String, String> result = new HashMap<>();
        ArrayList<AccountModel> selectResult = AccountDAO.getInstance().selectByCondition("WHERE username=\"" + user + "\"");

        if (selectResult.size() == 0) {
            result.put("result", "0");
            result.put("message", "Username Is Not Existed!");
            return result;
        }

        AccountModel account = selectResult.get(0);
        BCrypt.Result verifyOldPassResult = BCrypt.verifyer().verify(oldPassword.toCharArray(), account.getPassword());
        if (!verifyOldPassResult.verified) {
            result.put("result", "0");
            result.put("message", "Old Password Is Not Correct!");
            return result;
        }

        String hashingNewPassword = BCrypt.withDefaults().hashToString(10, newPassAgain.toCharArray());
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
