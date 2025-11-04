package models;

import models.enums.PaymentStatus;
import models.enums.PaymentType;

import java.time.LocalDateTime;

public class PaymentByCard extends Payment implements SecurePayment {
    private String creditCardNumber;

    public PaymentByCard(double totalPrice, LocalDateTime paymentDate, PaymentStatus paymentStatus, String creditCardNumber) {
        super(totalPrice, paymentDate, PaymentType.CARD, paymentStatus);
        this.creditCardNumber = creditCardNumber;
    }

    @Override
    public boolean processPayment() {
        return false;
    }

    @Override
    public boolean securePayment() {
        return true;
    }

    public String getCreditCardNumber() {
        return creditCardNumber;
    }

    public void setCreditCardNumber(String creditCardNumber) {
        this.creditCardNumber = creditCardNumber;
    }
}
