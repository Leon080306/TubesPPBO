package models;

import models.enums.PaymentStatus;
import models.enums.PaymentType;

import java.time.LocalDateTime;

public class PaymentByEWallet extends Payment {
    private String provider;
    private String accountID;

    public PaymentByEWallet(double totalPrice, LocalDateTime paymentDate, PaymentStatus paymentStatus, String provider, String accountID) {
        super(totalPrice, paymentDate, PaymentType.CARD, paymentStatus);
        this.provider = provider;
        this.accountID = accountID;
    }

    @Override
    public boolean processPayment() {
        return true;
    }
}
