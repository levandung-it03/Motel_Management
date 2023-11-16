package com.motel_management.Models;

public class AccountModel {
    private String userId;
    private String name;
    private String username;
    private String password;

    public AccountModel(String userId, String name, String username, String password) {
        this.userId = userId;
        this.name = name;
        this.username = username;
        this.password = password;
    }

    public String getUserId() { return userId; }
    public String getName() { return name; }
    public String getUsername() { return username; }
    public String getPassword() { return password; }

    public void setName(String name) { this.name = name; }
    public void setUserId(String userId) { this.userId = userId; }
    public void setUsername(String username) { this.username = username; }
    public void setPassword(String password) { this.password = password; }
}
