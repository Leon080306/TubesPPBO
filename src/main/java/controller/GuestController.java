package controller;

import models.enums.MembershipLevel;
import repository.GuestRepository;

public class GuestController {
    public static void updateGuestData(int points, MembershipLevel membershipLevel, String userId) {
        GuestRepository.udpateGuestData(points, membershipLevel, userId);
    }

    public static void addNewGuest(String password, String name, int umur, String email, String phone, String address, MembershipLevel membershipLevel, int points) {
        GuestRepository.addGuest(password, name, umur, email, phone, address, membershipLevel, points);
    }

    public static void addNewGuest(String password, String name, int umur, String email, String phone, String address) {
        GuestRepository.addGuest(password, name, umur, email, phone, address, MembershipLevel.REGULAR, 0);
    }
}
