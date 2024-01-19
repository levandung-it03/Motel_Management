package com.motel_management.Models;

public class RoomModel {
    private String roomId;
    private int quantity;
    private int maxQuantity;

    public RoomModel(String roomId, int quantity, int maxQuantity) {
        this.roomId = roomId;
        this.quantity = quantity;
        this.maxQuantity = maxQuantity;
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


    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    public void setMaxQuantity(int maxQuantity) {
        this.maxQuantity = maxQuantity;
    }

}
