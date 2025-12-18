package models;

import models.enums.RoomType;

public class Room {
    private String roomID;
    private String roomNumber;
    private RoomType roomType;
    private String roomDescription;
    private double roomPrice;

    public Room(String roomID, String roomNumber, RoomType roomType, String roomDescription, double roomPrice) {
        this.roomID = roomID;
        this.roomNumber = roomNumber;
        this.roomType = roomType;
        this.roomDescription = roomDescription;
        this.roomPrice = roomPrice;
    }

    public String getRoomID() {
        return roomID;
    }
    public void setRoomID(String roomID) {
        this.roomID = roomID;
    }
    public String getRoomNumber() {
        return roomNumber;
    }
    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }
    public RoomType getRoomType() {
        return roomType;
    }
    public void setRoomType(RoomType roomType) {
        this.roomType = roomType;
    }
    public String getRoomDescription() {
        return roomDescription;
    }
    public void setRoomDescription(String roomDescription) {
        this.roomDescription = roomDescription;
    }
    public double getRoomPrice() {
        return roomPrice;
    }
    public void setRoomPrice(double roomPrice) {
        this.roomPrice = roomPrice;
    }
}