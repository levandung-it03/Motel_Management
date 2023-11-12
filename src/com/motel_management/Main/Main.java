package com.motel_management.Main;

import com.motel_management.DataAccessObject.AccountDAO;
import com.motel_management.Models.Account;

public class Main {
    public static void main(String[] args) {
        Account a = AccountDAO.getInstance().selectById("admin000");
        System.out.println(a.getUserId());
        System.out.println(a.getName());
        System.out.println(a.getUsername());
        System.out.println(a.getPassword());
    }
}
