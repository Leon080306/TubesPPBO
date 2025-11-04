package models;

import models.enums.MembershipLevel;
import models.enums.UserType;

public class Guest extends Users{
    private MembershipLevel membershipLevel;
    private int points;
    private String creditCardNumberPin;

    public Guest (String password, String nama, int umur, String email, String phone, String address, MembershipLevel membershipLevel) {
        super(password, nama, umur, email, phone, address, UserType.GUEST);
        this.membershipLevel = membershipLevel;
        this.points = 0;
    }

    public Guest (String password, String nama, int umur, String email, String phone, String address, MembershipLevel membershipLevel, String creditCardNumberPin) {
        super(password, nama, umur, email, phone, address, UserType.GUEST);
        this.membershipLevel = membershipLevel;
        this.points = 0;
        this.creditCardNumberPin = creditCardNumberPin;
    }
}
