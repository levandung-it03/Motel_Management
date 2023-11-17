package com.motel_management.Models;

import java.sql.Date;

public class ContractModel {
    private String contractId;
    private String identifier;
    private String roomId;
    private int quantity;
    private int roomDeposit;
    private Date startingDate;
    private Date endingDate;

    public ContractModel (String contractId , String identifier, String roomId , int quantity , int roomDeposit ,
                          Date startingDate , Date endingDate) {
        this.contractId = contractId;
        this.identifier = identifier;
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
