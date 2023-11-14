package com.motel_management.Cotrollers;

import com.motel_management.DataAccessObject.AccountDAO;
import com.motel_management.Models.Account;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Controller_Login {
    public static String validate(String username, String password) {
        ArrayList<Account> a = AccountDAO.getInstance().selectByCondition("WHERE (username=\"" + username + "\")");
        if (!a.isEmpty() && a.get(0).getPassword().equals(password))
            return a.name;
        else
            return null;
    }
}
