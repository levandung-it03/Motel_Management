package com.motel_management.Cotrollers;

import com.motel_management.DataAccessObject.AccountDAO;
import com.motel_management.Models.Account;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Controller_Login {
    public static boolean validate(String username, String password) {
        ArrayList<Account> a = AccountDAO.getInstance().selectByCondition("WHERE (username=\"" + username + "\")");
        return !a.isEmpty() && a.get(0).getPassword().equals(password);
    }
}
