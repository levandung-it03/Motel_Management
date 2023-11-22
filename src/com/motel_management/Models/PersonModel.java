package com.motel_management.Models;

import java.sql.Date;

public class PersonModel {
    private String identifier;
    private String roomId;
    private String lastName;
    private String firstName;
    private Date birthday;
    private String phone;
    private String gender;
    private String jobTitle;
    private String permanentAddress;
    private String email;
    private String bankAccountNumber;
    private String bank;
    private String isOccupied;

    public PersonModel() {}
    public PersonModel(String identifier, String roomId, String lastName, String firstName, Date birthday,
                       String phone, String gender,String jobTitle, String permanentAddress, String email,
                       String bankAccountNumber, String bank, String isOccupied) {
        this.identifier = identifier;
        this.roomId = roomId;
        this.lastName = lastName;
        this.firstName = firstName;
        this.birthday = birthday;
        this.phone = phone;
        this.gender = gender;
        this.jobTitle = jobTitle;
        this.permanentAddress = permanentAddress;
        this.email = email;
        this.bankAccountNumber = bankAccountNumber;
        this.bank = bank;
        this.isOccupied = isOccupied;
    }

    // Getters
    public String getIdentifier() { return identifier; }
    public String getRoomId() { return roomId; }
    public String getLastName() { return lastName; }
    public String getFirstName() { return firstName; }
    public Date getBirthday() { return birthday; }
    public String getPhone() { return phone; }
    public String getGender() {return gender; }

    public String getJobTitle () {return jobTitle;}

    public String getPermanentAddress() { return permanentAddress; }
    public String getEmail() { return email; }
    public String getBankAccountNumber() { return bankAccountNumber; }
    public String getBank() { return bank; }

    // Setters
    public void setIdentifier(String identifier) { this.identifier = identifier; }
    public void setRoomId(String roomId) { this.roomId = roomId; }
    public void setLastName(String lastName) { this.lastName = lastName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }
    public void setBirthday(Date birthday) { this.birthday = birthday; }
    public void setPhone(String phone) { this.phone = phone; }
    public void setGender (String gender) {this.gender = gender;}
    public String getIsOccupied() { return isOccupied; }

    public void setJobTitle (String jobTitle) {this.jobTitle = jobTitle;}
    public void setPermanentAddress(String permanentAddress) { this.permanentAddress = permanentAddress; }
    public void setEmail(String email) { this.email = email; }
    public void setBankAccountNumber(String bankAccountNumber) { this.bankAccountNumber = bankAccountNumber; }
    public void setBank(String bank) { this.bank = bank; }
    public void setIsOccupied(String isOccupied) { this.isOccupied = isOccupied; }
}
