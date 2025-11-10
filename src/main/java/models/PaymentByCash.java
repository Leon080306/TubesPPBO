package models;

import models.enums.PaymentStatus;
import models.enums.PaymentType;

import java.time.LocalDateTime;

public class PaymentByCash extends Payment {
    private double amountPaid;

    public PaymentByCash(double totalPrice, LocalDateTime paymentDate, PaymentStatus paymentStatus, double amountPayed) {
        super(totalPrice, paymentDate, PaymentType.CARD, paymentStatus);
        this.amountPaid = amountPayed;
    }

    public double getChange() {
        return amountPaid - getTotalPrice();
    }

    @Override
    public boolean processPayment() {
        return true;
    }

    public double getAmountPayed() {
        return amountPaid;
    }

    public void setAmountPayed(double amountPayed) {
        this.amountPaid = amountPayed;
    }
}
