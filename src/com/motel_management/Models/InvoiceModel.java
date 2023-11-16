package com.motel_management.Models;

import java.sql.Date;

public class InvoiceModel {
    private String invoiceId;
    private String roomId;
    private String consumptionId;
    private int defaultRoomPrice;
    private String monthPayment;
    private String yearPayment;
    private Date dateCreated;
    private int totalWaterBill;
    private int totalElectricBill;
    private int garbage;
    private int vehicle;
    private int total;
    private boolean wasPaid;

    public InvoiceModel(String invoiceId, String roomId, String consumptionId, int defaultRoomPrice, String monthPayment, String yearPayment, Date dateCreated, int totalWaterBill, int totalElectricBill, int garbage, int vehicle, int total, boolean wasPaid) {
        this.invoiceId = invoiceId;
        this.roomId = roomId;
        this.consumptionId = consumptionId;
        this.defaultRoomPrice = defaultRoomPrice;
        this.monthPayment = monthPayment;
        this.yearPayment = yearPayment;
        this.dateCreated = dateCreated;
        this.totalWaterBill = totalWaterBill;
        this.totalElectricBill = totalElectricBill;
        this.garbage = garbage;
        this.vehicle = vehicle;
        this.total = total;
        this.wasPaid = wasPaid;
    }

    public String getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(String invoiceId) {
        this.invoiceId = invoiceId;
    }

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public String getConsumptionId() {
        return consumptionId;
    }

    public void setConsumptionId(String consumptionId) {
        this.consumptionId = consumptionId;
    }

    public int getDefaultRoomPrice() {
        return defaultRoomPrice;
    }

    public void setDefaultRoomPrice(int defaultRoomPrice) {
        this.defaultRoomPrice = defaultRoomPrice;
    }

    public String getMonthPayment() {
        return monthPayment;
    }

    public void setMonthPayment(String monthPayment) {
        this.monthPayment = monthPayment;
    }

    public String getYearPayment() {
        return yearPayment;
    }

    public void setYearPayment(String yearPayment) {
        this.yearPayment = yearPayment;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public int getTotalWaterBill() {
        return totalWaterBill;
    }

    public void setTotalWaterBill(int totalWaterBill) {
        this.totalWaterBill = totalWaterBill;
    }

    public int getTotalElectricBill() {
        return totalElectricBill;
    }

    public void setTotalElectricBill(int totalElectricBill) {
        this.totalElectricBill = totalElectricBill;
    }

    public int getGarbage() {
        return garbage;
    }

    public void setGarbage(int garbage) {
        this.garbage = garbage;
    }

    public int getVehicle() {
        return vehicle;
    }

    public void setVehicle(int vehicle) {
        this.vehicle = vehicle;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public boolean isWasPaid() {
        return wasPaid;
    }

    public void setWasPaid(boolean wasPaid) {
        this.wasPaid = wasPaid;
    }
}
