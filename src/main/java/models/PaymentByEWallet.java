package models;

import models.enums.PaymentStatus;
import models.enums.PaymentType;

import java.time.LocalDateTime;
import java.util.List;

public class PaymentByEWallet extends Payment implements SecurePayment{
    private String provider;
    private String accountID;

    public PaymentByEWallet(String paymentID, double totalPrice, LocalDateTime paymentDate, PaymentStatus paymentStatus, Booking booking,ExtraServices extraServices, String provider, String accountID) {
        super(paymentID, totalPrice, paymentDate, PaymentType.E_WALLET, paymentStatus, booking, extraServices);
        this.provider = provider;
        this.accountID = accountID;
    }

    @Override
    public boolean securePayment() {
       return accountID.matches("08\\d(8,11)");
    }

    public String getProvider (){return this.provider;}
    public void setProvider (String provider){this.provider = provider;}
    public String getAccountID (){return this.accountID;}
    public void setAccountID (String accountID){this.accountID = accountID;}

}
