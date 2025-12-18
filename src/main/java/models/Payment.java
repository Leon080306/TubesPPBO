package models;

import controller.BookingController;
import models.enums.PaymentStatus;
import models.enums.PaymentType;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.List;

public abstract class Payment {
    private String paymentID;
    private double totalPrice;
    private LocalDateTime paymentDate;
    private PaymentType paymentType;
    private PaymentStatus paymentStatus;
    private Booking booking;
    private ExtraServices extraService;

    public Payment(String paymentID, double totalPrice, LocalDateTime paymentDate, PaymentType paymentType, PaymentStatus paymentStatus, Booking booking, ExtraServices extraService) {
        this.paymentID = paymentID;
        this.totalPrice = totalPrice;
        this.paymentDate = paymentDate;
        this.paymentType = paymentType;
        this.paymentStatus = paymentStatus;
        this.booking = booking;
        this.extraService = extraService;
    }


    public String getPaymentID() {
        return paymentID;
    }
    public void setPaymentID(String paymentID) {
        this.paymentID = paymentID;
    }
    public double getTotalPrice() {
        return totalPrice;
    }
    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }
    public LocalDateTime getPaymentDate() {
        return paymentDate;
    }
    public void setPaymentDate(LocalDateTime paymentDate) {
        this.paymentDate = paymentDate;
    }
    public PaymentType getPaymentType() {
        return paymentType;
    }
    public void setPaymentType(PaymentType paymentType) {
        this.paymentType = paymentType;
    }
    public PaymentStatus getPaymentStatus() {
        return paymentStatus;
    }
    public void setPaymentStatus(PaymentStatus paymentStatus) {
        this.paymentStatus = paymentStatus;
    }
    public ExtraServices getExtraService() {
        return extraService;
    }
    public void setExtraService(ExtraServices extraService) {
        this.extraService = extraService;
    }
    public Booking getBooking(){return booking; }
    public void setBooking(Booking booking){this.booking = booking; }
}
