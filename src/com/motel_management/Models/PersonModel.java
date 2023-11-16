package com.motel_management.Models;

import java.sql.Date;

public class PersonModel {
    private String identifier;
    private String roomCode;
    private String lastName;
    private String firstName;
    private Date birthday;
    private String phone;
    private String gender;
    private String permanentAddress;
    private String email;
    private String creditCard;
    private String bank;

    public PersonModel() {}
    public PersonModel(String identifier, String roomCode, String lastName, String firstName, Date birthday,
                       String phone, String gender, String permanentAddress, String email, String creditCard, String bank) {
        this.identifier = identifier;
        this.roomCode = roomCode;
        this.lastName = lastName;
        this.firstName = firstName;
        this.birthday = birthday;
        this.phone = phone;
        this.gender = gender;
        this.permanentAddress = permanentAddress;
        this.email = email;
        this.creditCard = creditCard;
        this.bank = bank;
    }

    // Getters
    public String getIdentifier() { return identifier; }
    public String getRoomCode() { return roomCode; }
    public String getLastName() { return lastName; }
    public String getFirstName() { return firstName; }
    public Date getBirthday() { return birthday; }
    public String getPhone() { return phone; }
    public String getGender() {return gender; }
    public String getPermanentAddress() { return permanentAddress; }
    public String getEmail() { return email; }
    public String getCreditCard() { return creditCard; }
    public String getBank() { return bank; }

    // Setters
    public void setIdentifier(String identifier) { this.identifier = identifier; }
    public void setRoomCode(String roomCode) { this.roomCode = roomCode; }
    public void setLastName(String lastName) { this.lastName = lastName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }
    public void setBirthday(Date birthday) { this.birthday = birthday; }
    public void setPhone(String phone) { this.phone = phone; }
    public void setGender (String gender) {this.gender = gender;}
    public void setPermanentAddress(String permanentAddress) { this.permanentAddress = permanentAddress; }
    public void setEmail(String email) { this.email = email; }
    public void setCreditCard(String creditCard) { this.creditCard = creditCard; }
    public void setBank(String bank) { this.bank = bank; }
}
