package com.motel_management.Models;

import java.sql.Date;

public class Consumption {
    private String consumptionId;
    private String roomId;
    private String consumptionMonth;
    private String consumptionYear;
    private Date dateCreated;
    private int waterNumber;
    private int electricNumber;
    private String vehicle;

    public Consumption(String consumptionId, String roomId, String consumptionMonth, String consumptionYear, Date dateCreated, int waterNumber, int electricNumber, String vehicle) {
        this.consumptionId = consumptionId;
        this.roomId = roomId;
        this.consumptionMonth = consumptionMonth;
        this.consumptionYear = consumptionYear;
        this.dateCreated = dateCreated;
        this.waterNumber = waterNumber;
        this.electricNumber = electricNumber;
        this.vehicle = vehicle;
    }

    public String getConsumptionId() {
        return consumptionId;
    }

    public void setConsumptionId(String consumptionId) {
        this.consumptionId = consumptionId;
    }

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public String getConsumptionMonth() {
        return consumptionMonth;
    }

    public void setConsumptionMonth(String consumptionMonth) {
        this.consumptionMonth = consumptionMonth;
    }

    public String getConsumptionYear() {
        return consumptionYear;
    }

    public void setConsumptionYear(String consumptionYear) {
        this.consumptionYear = consumptionYear;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public int getWaterNumber() {
        return waterNumber;
    }

    public void setWaterNumber(int waterNumber) {
        this.waterNumber = waterNumber;
    }

    public int getElectricNumber() {
        return electricNumber;
    }

    public void setElectricNumber(int electricNumber) {
        this.electricNumber = electricNumber;
    }

    public String getVehicle() {
        return vehicle;
    }

    public void setVehicle(String vehicle) {
        this.vehicle = vehicle;
    }
}
