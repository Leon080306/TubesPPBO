package controllers;

import exceptions.NoResultsFound;
import models.Payment;
import repository.PaymentRepository;

import java.util.List;

public class PaymentControllers {
    PaymentRepository paymentRepository;

    public PaymentControllers(){
        paymentRepository = new PaymentRepository();
    }

    public List<Payment> showPaymentHistory(String guestID) throws NoResultsFound {
        try{
            return paymentRepository.getPaymentHistory(guestID);
        } catch (NoResultsFound e) {
            throw new RuntimeException(e);
        }

    }
}
