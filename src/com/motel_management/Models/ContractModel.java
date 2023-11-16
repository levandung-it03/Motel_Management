package com.motel_management.Models;

import java.sql.Date;

public class ContractModel {
    private String contractId;
    private String identifier;
    private String lastName;
    private String firstname;
    private String birthday;
    private String phone;
    private String gender;
    private String jobtitle;
    private String permanentAddress;
    private String email;
    private String creditCard;
    private String bank;
    private String roomId;
    private int quantity;
    private int roomDeposit;
    private Date startingDate;
    private Date endingDate;

    public ContractModel (String contractId , String identifier , String lastName , String firstname , String birthday ,
                          String phone , String gender , String jobtitle , String permanentAddress , String email ,
                          String creditCard , String bank , String roomId , int quantity , int roomDeposit ,
                          Date startingDate , Date endingDate) {
        this.contractId = contractId;
        this.identifier = identifier;
        this.lastName = lastName;
        this.firstname = firstname;
        this.birthday = birthday;
        this.phone = phone;
        this.gender = gender;
        this.jobtitle = jobtitle;
        this.permanentAddress = permanentAddress;
        this.email = email;
        this.creditCard = creditCard;
        this.bank = bank;
        this.roomId = roomId;
        this.quantity = quantity;
        this.roomDeposit = roomDeposit;
        this.startingDate = startingDate;
        this.endingDate = endingDate;
    }

    public String getContractId () {
        return contractId;
    }

    public void setContractId (String contractId) {
        this.contractId = contractId;
    }

    public String getIdentifier () {
        return identifier;
    }

    public void setIdentifier (String identifier) {
        this.identifier = identifier;
    }

    public String getLastName () {
        return lastName;
    }

    public void setLastName (String lastName) {
        this.lastName = lastName;
    }

    public String getFirstname () {
        return firstname;
    }

    public void setFirstname (String firstname) {
        this.firstname = firstname;
    }

    public String getBirthday () {
        return birthday;
    }

    public void setBirthday (String birthday) {
        this.birthday = birthday;
    }

    public String getPhone () {
        return phone;
    }

    public void setPhone (String phone) {
        this.phone = phone;
    }

    public String getGender () {
        return gender;
    }

    public void setGender (String gender) {
        this.gender = gender;
    }

    public String getJobtitle () {
        return jobtitle;
    }

    public void setJobtitle (String jobtitle) {
        this.jobtitle = jobtitle;
    }

    public String getPermanentAddress () {
        return permanentAddress;
    }

    public void setPermanentAddress (String permanentAddress) {
        this.permanentAddress = permanentAddress;
    }

    public String getEmail () {
        return email;
    }

    public void setEmail (String email) {
        this.email = email;
    }

    public String getCreditCard () {
        return creditCard;
    }

    public void setCreditCard (String creditCard) {
        this.creditCard = creditCard;
    }

    public String getBank () {
        return bank;
    }

    public void setBank (String bank) {
        this.bank = bank;
    }

    public String getRoomId () {
        return roomId;
    }

    public void setRoomId (String roomId) {
        this.roomId = roomId;
    }

    public int getQuantity () {
        return quantity;
    }

    public void setQuantity (int quantity) {
        this.quantity = quantity;
    }

    public int getRoomDeposit () {
        return roomDeposit;
    }

    public void setRoomDeposit (int roomDeposit) {
        this.roomDeposit = roomDeposit;
    }

    public Date getStartingDate () {
        return startingDate;
    }

    public void setStartingDate (Date startingDate) {
        this.startingDate = startingDate;
    }

    public Date getEndingDate () {
        return endingDate;
    }

    public void setEndingDate (Date endingDate) {
        this.endingDate = endingDate;
    }
}
