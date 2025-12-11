package controller;

import repository.GuestRepository;

public class GuestController {
    public static void addNewGuest(String password, String name, int umur, String email, String phone, String address) {
        GuestRepository.addGuest(password, name, umur, email, phone, address);
    }
}
