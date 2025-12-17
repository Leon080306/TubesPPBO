package models;

import models.enums.PaymentStatus;
import models.enums.PaymentType;

import java.time.LocalDateTime;
import java.util.List;

public class PaymentByEWallet extends Payment implements SecurePayment{
    private String provider;
    private String accountID;

    public PaymentByEWallet(String paymentID, double totalPrice, LocalDateTime paymentDate, PaymentStatus paymentStatus, Booking booking,ExtraServices extraServices, String provider, String accountID) {
        super(paymentID, totalPrice, paymentDate, PaymentType.CARD, paymentStatus, booking, extraServices);
        this.provider = provider;
        this.accountID = accountID;
    }

    //wip
    @Override
    public boolean securePayment(String input) {
        return input.equals("test");
    }

    @Override
    public boolean processPayment(String inputPin) {
        // example: card must be validated first
        if (!securePayment(inputPin)) {
            setPaymentStatus(PaymentStatus.FAILED);
            return false;
        }

        setPaymentStatus(PaymentStatus.SUCCESSFUL);
        return true;
    }

    //wip
    @Override
    public List<String> getDisplayDetails() {
        return List.of();
    }
}
