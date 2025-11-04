package models;

import models.enums.PaymentStatus;
import models.enums.PaymentType;

import java.time.LocalDateTime;

public class PaymentByCash extends Payment {
    private double amountPayed;

    public PaymentByCash(double totalPrice, LocalDateTime paymentDate, PaymentStatus paymentStatus, double amountPayed) {
        super(totalPrice, paymentDate, PaymentType.CARD, paymentStatus);
        this.amountPayed = amountPayed;
    }

    public double getChange() {
        return amountPayed - getTotalPrice();
    }

    @Override
    public boolean processPayment() {
        return true;
    }

    public double getAmountPayed() {
        return amountPayed;
    }

    public void setAmountPayed(double amountPayed) {
        this.amountPayed = amountPayed;
    }
}
