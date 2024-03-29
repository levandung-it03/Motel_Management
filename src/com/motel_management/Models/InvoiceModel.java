package com.motel_management.Models;

import java.sql.Date;

public class InvoiceModel {
    private String invoiceId;
    private String roomId;
    private Date priceRaisedDate;
    private Date dateCreated;
    private int paymentYear;
    private int paymentMonth;
    private int formerElectricNumber;
    private int newElectricNumber;
    private int formerWaterNumber;
    private int newWaterNumber;
    private int electricPrice;
    private int waterPrice;
    private int garbage;
    private int wifi;
    private int vehicle;
    private boolean wasPaid;

    public InvoiceModel(String invoiceId, String roomId, Date priceRaisedDate, Date dateCreated, int paymentYear,
                        int paymentMonth, int formerElectricNumber, int newElectricNumber, int formerWaterNumber,
                        int newWaterNumber, int electricPrice, int waterPrice, int garbage, int wifi, int vehicle,
                        boolean wasPaid) {
        this.invoiceId = invoiceId;
        this.roomId = roomId;
        this.priceRaisedDate = priceRaisedDate;
        this.dateCreated = dateCreated;
        this.paymentYear = paymentYear;
        this.paymentMonth = paymentMonth;
        this.formerElectricNumber = formerElectricNumber;
        this.newElectricNumber = newElectricNumber;
        this.formerWaterNumber = formerWaterNumber;
        this.newWaterNumber = newWaterNumber;
        this.electricPrice = electricPrice;
        this.waterPrice = waterPrice;
        this.garbage = garbage;
        this.wifi = wifi;
        this.vehicle = vehicle;
        this.wasPaid = wasPaid;
    }

    // Getters
    public String getInvoiceId () {return invoiceId;}
    public String getRoomId () {return roomId;}
    public Date getPriceRaisedDate() { return priceRaisedDate; }
    public Date getDateCreated () {return dateCreated;}
    public int getFormerElectricNumber() {return formerElectricNumber;}
    public int getNewElectricNumber() {return newElectricNumber;}
    public int getFormerWaterNumber() {return formerWaterNumber;}
    public int getNewWaterNumber() {return newWaterNumber;}
    public int getGarbage () {return garbage;}
    public int getWifi () {return wifi;}
    public int getVehicle () {return vehicle;}
    public int getPaymentMonth() { return paymentMonth; }
    public int getPaymentYear() { return paymentYear; }
    public int getElectricPrice() { return electricPrice; }
    public int getWaterPrice() { return waterPrice; }
    public boolean getWasPaid () {return wasPaid;}

    // Setters
    public void setInvoiceId (String invoiceId) {
        this.invoiceId = invoiceId;
    }
    public void setRoomId (String roomId) {
        this.roomId = roomId;
    }
    public void setPriceRaisedDate(Date priceRaisedDate) { this.priceRaisedDate = priceRaisedDate; }
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
    public void setElectricPrice(int electricPrice) { this.electricPrice = electricPrice; }
    public void setWaterPrice(int waterPrice) { this.waterPrice = waterPrice; }
    public void setWasPaid (boolean wasPaid) {
        this.wasPaid = wasPaid;
    }
}