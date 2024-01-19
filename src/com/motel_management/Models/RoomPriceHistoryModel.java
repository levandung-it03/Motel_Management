package com.motel_management.Models;

import java.sql.Date;

public class RoomPriceHistoryModel {
    private String roomId;
    private Date priceRaisedDate;
    private int roomPrice;

    public RoomPriceHistoryModel(String roomId, Date priceRaisedDate, int roomPrice) {
        this.roomId = roomId;
        this.roomPrice = roomPrice;
        this.priceRaisedDate = priceRaisedDate;
    }

    public String getRoomId() { return roomId; }
    public Date getPriceRaisedDate() { return priceRaisedDate; }
    public int getRoomPrice() { return roomPrice; }

    public void setRoomId(String roomId) { this.roomId = roomId; }
    public void setPriceRaisedDate(Date priceRaisedDate) { this.priceRaisedDate = priceRaisedDate; }
    public void setRoomPrice(int roomPrice) { this.roomPrice = roomPrice; }
}
