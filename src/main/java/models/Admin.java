package models;

import models.enums.UserType;

public class Admin extends Users {
    public Admin (String password, String nama, int umur, String email, String phone, String address) {
        super(password, nama, umur, email, phone, address, UserType.ADMIN);
    }
}