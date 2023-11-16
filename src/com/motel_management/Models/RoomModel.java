package com.motel_management.Models;

public class RoomModel {
    private String roomId;
    private int quantity;
    private int maxQuantity;
    private int defaultRoomPrice;

    public RoomModel(String roomId, int quantity, int maxQuantity, int defaultRoomPrice) {
        this.roomId = roomId;
        this.quantity = quantity;
        this.maxQuantity = maxQuantity;
        this.defaultRoomPrice = defaultRoomPrice;
    }

    public String getRoomId() {
        return roomId;
    }

    public int getQuantity() {
        return quantity;
    }

    public int getMaxQuantity() {
        return maxQuantity;
    }

    public int getDefaultRoomPrice() {
        return defaultRoomPrice;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setMaxQuantity(int maxQuantity) {
        this.maxQuantity = maxQuantity;
    }

    public void setDefaultRoomPrice(int defaultRoomPrice) {
        this.defaultRoomPrice = defaultRoomPrice;
    }
}
