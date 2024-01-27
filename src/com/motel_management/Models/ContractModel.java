package com.motel_management.Models;

import java.sql.Date;
import java.sql.Timestamp;

public class ContractModel {
    private String contractId;
    private String identifier;
    private String roomId;
    private int quantity;
    private int roomDeposit;
    private boolean isFamily;
    private Date startingDate;
    private Date endingDate;
    private boolean checkedOut;
    private boolean isRegisteredPerAddress;
    private Timestamp creatingTime;

    public ContractModel (String contractId , String identifier, String roomId, int quantity , int roomDeposit,
                          boolean isFamily, Date startingDate , Date endingDate, boolean checkedOut,
                          boolean isRegisteredPerAddress, Timestamp creatingTime) {
        this.contractId = contractId;
        this.identifier = identifier;
        this.roomId = roomId;
        this.quantity = quantity;
        this.roomDeposit = roomDeposit;
        this.startingDate = startingDate;
        this.endingDate = endingDate;
        this.isFamily = isFamily;
        this.isRegisteredPerAddress = isRegisteredPerAddress;
        this.checkedOut = checkedOut;
        this.creatingTime = creatingTime;
    }

    // Getters
    public String getContractId() { return contractId; }
    public String getIdentifier() { return identifier; }
    public String getRoomId() { return roomId; }
    public int getQuantity() { return quantity; }
    public int getRoomDeposit() { return roomDeposit; }
    public boolean getIsFamily() { return isFamily; }
    public Date getStartingDate() { return startingDate; }
    public Date getEndingDate() { return endingDate; }
    public boolean getCheckedOut() { return checkedOut; }
    public boolean getIsRegisteredPerAddress() { return isRegisteredPerAddress; }
    public Timestamp getCreatingTime() { return creatingTime; }

    // Setters
    public void setContractId(String contractId) { this.contractId = contractId; }
    public void setIdentifier(String identifier) { this.identifier = identifier; }
    public void setRoomId(String roomId) { this.roomId = roomId; }
    public void setQuantity(int quantity) { this.quantity = quantity; }
    public void setRoomDeposit(int roomDeposit) { this.roomDeposit = roomDeposit; }
    public void setFamily(boolean family) { isFamily = family; }
    public void setStartingDate(Date startingDate) { this.startingDate = startingDate; }
    public void setEndingDate(Date endingDate) { this.endingDate = endingDate; }
    public void setCheckedOut(boolean checkedOut) { this.checkedOut = checkedOut; }
    public void setRegisteredPerAddress(boolean registeredPerAddress) { isRegisteredPerAddress = registeredPerAddress; }
    public void setCreatingTime(Timestamp creatingTime) { this.creatingTime = creatingTime; }
}