package com.motel_management.Models;

import java.sql.Date;

public class InvoiceModel {
    private String invoiceId;
    private String roomId;
    private int defaultRoomPrice;
    private Date dateCreated;
    private String yearPayment;
    private String monthPayment;
    private int formerElectricNumber;
    private int currentElectricNumber;
    private int formerWaterNumber;
    private int currentWaterNumber;
    private int garbage;
    private int wifi;
    private int vehicle;
    private int total;
    private String wasPaid;

    public InvoiceModel(String invoiceId, String roomId, int defaultRoomPrice, Date dateCreated, String yearPayment, String monthPayment, int formerElectricNumber, int currentElectricNumber, int formerWaterNumber, int currentWaterNumber, int garbage, int wifi, int vehicle, int total, String wasPaid) {
        this.invoiceId = invoiceId;
        this.roomId = roomId;
        this.defaultRoomPrice = defaultRoomPrice;
        this.dateCreated = dateCreated;
        this.yearPayment = yearPayment;
        this.monthPayment = monthPayment;
        this.formerElectricNumber = formerElectricNumber;
        this.currentElectricNumber = currentElectricNumber;
        this.formerWaterNumber = formerWaterNumber;
        this.currentWaterNumber = currentWaterNumber;
        this.garbage = garbage;
        this.wifi = wifi;
        this.vehicle = vehicle;
        this.total = total;
        this.wasPaid = wasPaid;
    }

    public String getInvoiceId () {return invoiceId;}

    public String getRoomId () {return roomId;}

    public int getDefaultRoomPrice () {return defaultRoomPrice;}

    public Date getDateCreated () {return dateCreated;}

    public String getYearPayment () {return yearPayment;}

    public String getMonthPayment () {return monthPayment;}

    public int getFormerElectricNumber() {return formerElectricNumber;}

    public int getCurrentElectricNumber() {return currentElectricNumber;}

    public int getFormerWaterNumber() {return formerWaterNumber;}

    public int getCurrentWaterNumber() {return currentWaterNumber;}

    public int getGarbage () {return garbage;}

    public int getWifi () {return wifi;}

    public int getVehicle () {return vehicle;}

    public int getTotal () {return total;}

    public String getWasPaid () {return wasPaid;}

    public void setInvoiceId (String invoiceId) {
        this.invoiceId = invoiceId;
    }

    public void setRoomId (String roomId) {
        this.roomId = roomId;
    }

    public void setDefaultRoomPrice (int defaultRoomPrice) {
        this.defaultRoomPrice = defaultRoomPrice;
    }

    public void setDateCreated (Date dateCreated) {this.dateCreated = dateCreated;}

    public void setYearPayment (String yearPayment) {this.yearPayment = yearPayment;}

    public void setMonthPayment (String monthPayment) {this.monthPayment = monthPayment;}

    public void setFormerElectricNumber(int formerElectricNumber) {this.formerElectricNumber = formerElectricNumber;}

    public void setCurrentElectricNumber(int currentElectricNumber) {this.currentElectricNumber = currentElectricNumber;}

    public void setFormerWaterNumber(int formerWaterNumber) {this.formerWaterNumber = formerWaterNumber;}

    public void setCurrentWaterNumber(int currentWaterNumber) {this.currentWaterNumber = currentWaterNumber;}

    public void setGarbage (int garbage) {
        this.garbage = garbage;
    }

    public void setWifi(int wifi) {this.wifi = wifi;}

    public void setVehicle (int vehicle) {
        this.vehicle = vehicle;
    }

    public void setTotal (int total) {
        this.total = total;
    }

    public void setWasPaid (String wasPaid) {
        this.wasPaid = wasPaid;
    }
}
