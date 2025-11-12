package models;

import models.enums.BookingStatus;

import java.time.LocalDateTime;

public class Booking {
    private String bookingID;
    private LocalDateTime checkInDate;
    private LocalDateTime checkOutDate;
    private BookingStatus bookingStatus;
    private Room room;
    private Payment payment;
    private int numberOfGuests;

    public Booking(String bookingID, LocalDateTime checkInDate, LocalDateTime checkOutDate, BookingStatus bookingStatus, Room room, Payment payment, int numberOfGuests) {
        this.bookingID = bookingID;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.bookingStatus = bookingStatus;
        this.room = room;
        this.payment = payment;
        this.numberOfGuests = numberOfGuests;
    }

    public String getBookingID() {
        return bookingID;
    }
    public void setBookingID(String bookingID) {
        this.bookingID = bookingID;
    }
    public LocalDateTime getCheckInDate() {
        return checkInDate;
    }
    public void setCheckInDate(LocalDateTime checkInDate) {
        this.checkInDate = checkInDate;
    }
    public LocalDateTime getCheckOutDate() {
        return checkOutDate;
    }
    public void setCheckOutDate(LocalDateTime checkOutDate) {
        this.checkOutDate = checkOutDate;
    }
    public BookingStatus getBookingStatus() {
        return bookingStatus;
    }
    public void setBookingStatus(BookingStatus bookingStatus) {
        this.bookingStatus = bookingStatus;
    }
    public Room getRoom() {
        return room;
    }
    public void setRoom(Room room) {
        this.room = room;
    }
    public Payment getPayment() {
        return payment;
    }
    public void setPayment(Payment payment) {
        this.payment = payment;
    }
    public int getNumberOfGuests() {
        return numberOfGuests;
    }
    public void setNumberOfGuests(int numberOfGuests) {
        this.numberOfGuests = numberOfGuests;
    }
}
