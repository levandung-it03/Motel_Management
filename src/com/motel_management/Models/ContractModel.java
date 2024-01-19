package com.motel_management.Models;

import java.sql.Date;

public class ContractModel {
    private String contractId;
    private String identifier;
    private int quantity;
    private int roomDeposit;
    private boolean isFamily;
    private Date startingDate;
    private Date endingDate;
    private boolean checkedOut;
    private boolean isRegisteredPerAddress;

    public ContractModel (String contractId , String identifier, int quantity , int roomDeposit,
                          boolean isFamily, Date startingDate , Date endingDate, boolean checkedOut,
                          boolean isRegisteredPerAddress) {
        this.contractId = contractId;
        this.identifier = identifier;
        this.quantity = quantity;
        this.roomDeposit = roomDeposit;
        this.startingDate = startingDate;
        this.endingDate = endingDate;
        this.isFamily = isFamily;
        this.isRegisteredPerAddress = isRegisteredPerAddress;
        this.checkedOut = checkedOut;
    }

    // Getters
    public String getContractId() { return contractId; }
    public String getIdentifier() { return identifier; }
    public int getQuantity() { return quantity; }
    public int getRoomDeposit() { return roomDeposit; }
    public boolean getIsFamily() { return isFamily; }
    public Date getStartingDate() { return startingDate; }
    public Date getEndingDate() { return endingDate; }
    public boolean getCheckedOut() { return checkedOut; }
    public boolean getIsRegisteredPerAddress() { return isRegisteredPerAddress; }

    // Setters
    public void setContractId(String contractId) { this.contractId = contractId; }
    public void setIdentifier(String identifier) { this.identifier = identifier; }
    public void setQuantity(int quantity) { this.quantity = quantity; }
    public void setRoomDeposit(int roomDeposit) { this.roomDeposit = roomDeposit; }
    public void setFamily(boolean family) { isFamily = family; }
    public void setStartingDate(Date startingDate) { this.startingDate = startingDate; }
    public void setEndingDate(Date endingDate) { this.endingDate = endingDate; }
    public void setCheckedOut(boolean checkedOut) { this.checkedOut = checkedOut; }
    public void setRegisteredPerAddress(boolean registeredPerAddress) { isRegisteredPerAddress = registeredPerAddress; }
}