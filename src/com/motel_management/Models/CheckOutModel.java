package com.motel_management.Models;

import java.sql.Date;

public class CheckOutModel {
    private String checkOutId;
    private String contractId;
    private String roomId;
    private String identifier;
    private String lastName;
    private String firstname;
    private String phone;
    private Date checkOutDate;

    public CheckOutModel(String checkOutId, String contractId, String roomId, String identifier, String lastName, String firstname, String phone, Date checkOutDate) {
        this.checkOutId = checkOutId;
        this.contractId = contractId;
        this.roomId = roomId;
        this.identifier = identifier;
        this.lastName = lastName;
        this.firstname = firstname;
        this.phone = phone;
        this.checkOutDate = checkOutDate;
    }

    public String getCheckOutId() {
        return checkOutId;
    }

    public void setCheckOutId(String checkOutId) {
        this.checkOutId = checkOutId;
    }

    public String getContractId() {
        return contractId;
    }

    public void setContractId(String contractId) {
        this.contractId = contractId;
    }

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Date getCheckOutDate() {
        return checkOutDate;
    }

    public void setCheckOutDate(Date checkOutDate) {
        this.checkOutDate = checkOutDate;
    }
}
