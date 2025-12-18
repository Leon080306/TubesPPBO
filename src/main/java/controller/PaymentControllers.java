package controller;

import exceptions.NoResultsFound;
import models.ExtraServices;
import models.Payment;
import models.enums.TaskStatus;
import repository.PaymentRepository;

import java.util.List;

public class PaymentControllers {
    public static Payment getPaymentByBookingID(String guestID){
        try{
            return PaymentRepository.getPaymentByBookingID(guestID);
        } catch (NoResultsFound e) {
            throw new RuntimeException(e);
        }

    }

    public boolean checkService(ExtraServices service){
        return service.isStatus() == TaskStatus.COMPLETED;
    }

}
