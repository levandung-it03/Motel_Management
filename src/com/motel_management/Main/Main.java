package com.motel_management.Main;

import com.motel_management.DataAccessObject.AccountDAO;
import com.motel_management.Models.Account;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        AccountDAO.getInstance().update(new Account("admin001", "Dung", "levandung", "123456"));
        AccountDAO.getInstance().selectAll().forEach(a -> {
            System.out.println(a.getUserId());
            System.out.println(a.getName());
            System.out.println(a.getUsername());
            System.out.println(a.getPassword());
        });
    }
}
