package com.motel_management.Models;

import java.sql.Date;

public class ContractModel {
    private String contractId;
    private String identifier;
    private String lastName;
    private String firstname;
    private String birthday;
    private String phone;
    private Date gender;
    private int jobtitle;
    private int permanentAddress;
    private String email;
    private String creditCard;
    private String bank;
    private String roomId;
    private String quantity;
    private String roomDeposit;
    private String startingDate;
    private String endingDate;

    public ContractModel(String contractId, String identifier, String lastName, String firstname, String birthday, String phone, Date gender, int jobtitle, int permanentAddress, String email, String creditCard, String bank, String roomId, String quantity, String roomDeposit, String startingDate, String endingDate) {
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

    public String getContractId() {
        return contractId;
    }

    public void setContractId(String contractId) {
        this.contractId = contractId;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Date getGender() {
        return gender;
    }

    public void setGender(Date gender) {
        this.gender = gender;
    }

    public int getJobtitle() {
        return jobtitle;
    }

    public void setJobtitle(int jobtitle) {
        this.jobtitle = jobtitle;
    }

    public int getPermanentAddress() {
        return permanentAddress;
    }

    public void setPermanentAddress(int permanentAddress) {
        this.permanentAddress = permanentAddress;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCreditCard() {
        return creditCard;
    }

    public void setCreditCard(String creditCard) {
        this.creditCard = creditCard;
    }

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getRoomDeposit() {
        return roomDeposit;
    }

    public void setRoomDeposit(String roomDeposit) {
        this.roomDeposit = roomDeposit;
    }

    public String getStartingDate() {
        return startingDate;
    }

    public void setStartingDate(String startingDate) {
        this.startingDate = startingDate;
    }

    public String getEndingDate() {
        return endingDate;
    }

    public void setEndingDate(String endingDate) {
        this.endingDate = endingDate;
    }
}
