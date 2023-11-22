package com.motel_management.Models;

import java.sql.Date;

public class ContractModel {
    private String contractId;
    private String identifier;
    private String roomId;
    private int quantity;
    private int roomDeposit;
    private String isFamily;
    private Date startingDate;
    private Date endingDate;
    private String isRegisteredPerAddress;
    private int totalMonths;
    private String checkedOut;

    public ContractModel (String contractId , String identifier, String roomId , int quantity , int roomDeposit,
                          String isFamily, Date startingDate , Date endingDate, int totalMonths, String checkedOut,
                          String isRegisteredPerAddress) {
        this.contractId = contractId;
        this.identifier = identifier;
        this.roomId = roomId;
        this.quantity = quantity;
        this.roomDeposit = roomDeposit;
        this.startingDate = startingDate;
        this.endingDate = endingDate;
        this.isFamily = isFamily;
        this.totalMonths = totalMonths;
        this.isRegisteredPerAddress = isRegisteredPerAddress;
        this.checkedOut = checkedOut;
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

    public String getIsFamily() { return isFamily; }

    public void setIsFamily(String isFamily) { this.isFamily = isFamily; }

    public int getTotalMonths() { return totalMonths; }

    public void setTotalMonths(int totalMonths) { this.totalMonths = totalMonths; }

    public String getIsRegisteredPerAddress() {
        return isRegisteredPerAddress;
    }

    public void setIsRegisteredPerAddress(String isRegisteredPerAddress) {
        this.isRegisteredPerAddress = isRegisteredPerAddress;
    }

    public String getCheckedOut() { return checkedOut; }

    public void setCheckedOut(String checkedOut) { this.checkedOut = checkedOut; }
}
