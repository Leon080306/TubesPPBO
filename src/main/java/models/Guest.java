package models;

import models.enums.MembershipLevel;
import models.enums.UserType;

public class Guest extends Users{
    private String guestID;
    private MembershipLevel membershipLevel;
    private int points;
    private String creditCardNumberPin;

    public Guest (String guestID, String password, String nama, int umur, String email, String phone, String address, MembershipLevel membershipLevel) {
        super(guestID, password, nama, umur, email, phone, address, UserType.GUEST);
        this.membershipLevel = membershipLevel;
        this.points = 0;
    }

    public Guest (String guestID, String password, String nama, int umur, String email, String phone, String address, MembershipLevel membershipLevel, String creditCardNumberPin) {
        super(guestID, password, nama, umur, email, phone, address, UserType.GUEST);
        this.membershipLevel = membershipLevel;
        this.points = 0;
        this.creditCardNumberPin = creditCardNumberPin;
    }
}
