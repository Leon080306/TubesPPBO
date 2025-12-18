package controllers;

import exceptions.NoResultsFound;
import models.ExtraServices;
import models.Payment;
import models.enums.TaskStatus;
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

    public boolean checkService(ExtraServices service){
        return service.isStatus() == TaskStatus.COMPLETED;
    }

}
