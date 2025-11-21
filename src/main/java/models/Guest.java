package models;

import models.enums.MembershipLevel;
import models.enums.UserType;

public class Guest extends Users{
    private String guestID;
    private MembershipLevel membershipLevel;
    private int points;
    private String creditCardNumberPin;

    public Guest (String userID, String guestID, String password, String nama, int umur, String email, String phone, String address, MembershipLevel membershipLevel, int points) {
        super(userID, password, nama, umur, email, phone, address, UserType.GUEST);
        this.guestID = guestID;
        this.membershipLevel = membershipLevel;
        this.points = 0;
    }

    public Guest (String userID, String guestID, String password, String nama, int umur, String email, String phone, String address, MembershipLevel membershipLevel, String creditCardNumberPin) {
        super(userID, password, nama, umur, email, phone, address, UserType.GUEST);
        this.membershipLevel = membershipLevel;
        this.points = points;
        this.creditCardNumberPin = creditCardNumberPin;
        this.guestID = guestID;
    }

    public String getGuestID() {
        return guestID;
    }

    public void setGuestID(String guestID) {
        this.guestID = guestID;
    }

    public MembershipLevel getMembershipLevel() {
        return membershipLevel;
    }

    public void setMembershipLevel(MembershipLevel membershipLevel) {
        this.membershipLevel = membershipLevel;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public String getCreditCardNumberPin() {
        return creditCardNumberPin;
    }

    public void setCreditCardNumberPin(String creditCardNumberPin) {
        this.creditCardNumberPin = creditCardNumberPin;
    }
}
