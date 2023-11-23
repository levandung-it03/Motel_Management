package com.motel_management.Models;

import java.sql.Date;

public class InvoiceModel {
    private String invoiceId;
    private String roomId;
    private int defaultRoomPrice;
    private Date dateCreated;
    private String paymentYear;
    private String paymentMonth;
    private int formerElectricNumber;
    private int newElectricNumber;
    private int formerWaterNumber;
    private int newWaterNumber;
    private int garbage;
    private int wifi;
    private int vehicle;
    private int total;
    private String wasPaid;

    public InvoiceModel(String invoiceId, String roomId, int defaultRoomPrice, Date dateCreated, int paymentYear,
                        int paymentMonth, int formerElectricNumber, int newElectricNumber, int formerWaterNumber,
                        int newWaterNumber, int garbage, int wifi, int vehicle, int total, String wasPaid) {
        this.invoiceId = invoiceId;
        this.roomId = roomId;
        this.defaultRoomPrice = defaultRoomPrice;
        this.dateCreated = dateCreated;
        this.paymentYear = paymentYear;
        this.paymentMonth = paymentMonth;
        this.formerElectricNumber = formerElectricNumber;
        this.newElectricNumber = newElectricNumber;
        this.formerWaterNumber = formerWaterNumber;
        this.newWaterNumber = newWaterNumber;
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

    public int getFormerElectricNumber() {return formerElectricNumber;}

    public int getNewElectricNumber() {return newElectricNumber;}

    public int getFormerWaterNumber() {return formerWaterNumber;}

    public int getNewWaterNumber() {return newWaterNumber;}

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

    public int getPaymentMonth() { return paymentMonth; }

    public int getPaymentYear() { return paymentYear; }

    public void setDefaultRoomPrice (int defaultRoomPrice) {
        this.defaultRoomPrice = defaultRoomPrice;
    }

    public void setDateCreated (Date dateCreated) {this.dateCreated = dateCreated;}

    public void setPaymentYear (int paymentYear) {this.paymentYear = paymentYear;}

    public void setPaymentMonth (int paymentMonth) {this.paymentMonth = paymentMonth;}

    public void setFormerElectricNumber(int formerElectricNumber) {this.formerElectricNumber = formerElectricNumber;}

    public void setNewElectricNumber(int newElectricNumber) {this.newElectricNumber = newElectricNumber;}

    public void setFormerWaterNumber(int formerWaterNumber) {this.formerWaterNumber = formerWaterNumber;}

    public void setNewWaterNumber(int newWaterNumber) {this.newWaterNumber = newWaterNumber;}

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