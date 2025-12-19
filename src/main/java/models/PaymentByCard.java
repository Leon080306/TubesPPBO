package models;

import models.enums.PaymentStatus;
import models.enums.PaymentType;

import java.time.LocalDateTime;
import java.util.List;

public class PaymentByCard extends Payment implements SecurePayment{
    private String creditCardNumber;
    private String passwordInput;


    public PaymentByCard(String paymentID, double totalPrice, LocalDateTime paymentDate, PaymentStatus paymentStatus,Booking booking, ExtraServices extraServices, String creditCardNumber) {
        super(paymentID, totalPrice, paymentDate, PaymentType.CARD, paymentStatus, booking, extraServices);
        this.creditCardNumber = creditCardNumber;
    }


    //wip
    @Override
    public boolean securePayment(){
        return creditCardNumber.matches("\\d{16}");
    }
//
//    @Override
//    public boolean processPayment(String inputPin) {
//        // example: card must be validated first
//        if (!securePayment(inputPin)) {
//            setPaymentStatus(PaymentStatus.FAILED);
//            return false;
//        }
//
//        setPaymentStatus(PaymentStatus.SUCCESSFUL);
//        return true;
//    }


    public String getCreditCardNumber() {
        return creditCardNumber;
    }

    public void setCreditCardNumber(String creditCardNumber) {
        this.creditCardNumber = creditCardNumber;
    }


}
