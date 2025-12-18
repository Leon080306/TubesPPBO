package controller;

import exceptions.NoResultsFound;
import exceptions.UnderPaymentHandling;
import models.ExtraServices;
import models.Payment;
import models.enums.TaskStatus;
import repository.PaymentRepository;

import java.util.List;

public class PaymentControllers {
    public static Payment getPaymentByBookingID(String guestID) {
        try {
            return PaymentRepository.getPaymentByBookingID(guestID);
        } catch (NoResultsFound e) {
            throw new RuntimeException(e);
        }

    }

    public static boolean cardPayment(String paymentID, double nominal, String creditCardNumber, String guestID, String bookingID) {
        try {
            return PaymentRepository.cardPayment(paymentID, nominal, creditCardNumber, guestID, bookingID);
        } catch (UnderPaymentHandling | NoResultsFound e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean eWalletPayment(String paymentID, double nominal, String eWalletProvider,String eWalletAccountID, String guestID, String bookingID) {
        try {
            return PaymentRepository.eWalletPayment(paymentID, nominal, eWalletProvider, eWalletAccountID, guestID, bookingID);
        } catch (UnderPaymentHandling | NoResultsFound e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean cashPayment(String paymentID, double nominal, double tips, String guestID, String bookingID){
        try{
            return PaymentRepository.cashPayment(paymentID,nominal,tips,guestID,bookingID);
        } catch (UnderPaymentHandling | NoResultsFound e) {
            throw new RuntimeException(e);
        }
    }

    public boolean checkService(ExtraServices service){
        return service.isStatus() == TaskStatus.COMPLETED;
    }

}
