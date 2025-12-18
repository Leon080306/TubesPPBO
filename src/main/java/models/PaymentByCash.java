package models;

import models.enums.PaymentStatus;
import models.enums.PaymentType;

import java.time.LocalDateTime;

public class PaymentByCash extends Payment{
    private double tip;

    public PaymentByCash(String paymentID, double totalPrice, LocalDateTime paymentDate, PaymentStatus paymentStatus,Booking booking,ExtraServices extraServices, double tip) {
        super(paymentID, totalPrice, paymentDate, PaymentType.CASH,paymentStatus, booking, extraServices);
        this.tip = tip;
    }

    public double getTip() {
        return tip;
    }

    public void setTip(double tip) {
        this.tip = tip;
    }
}
