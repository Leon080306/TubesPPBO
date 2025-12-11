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
        return paymentRepository.getPaymentHistory(guestID);
    }
}
