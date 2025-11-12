package models;

import models.enums.UserType;

public abstract class Users {
    private String userID;
    private String password;
    private String nama;
    private int umur;
    private String email;
    private String phone;
    private String address;
    private UserType userType;

    public Users (String userID, String password, String nama, int umur, String email, String phone, String address, UserType userType) {
        this.userID = userID;
        this.password = password;
        this.nama = nama;
        this.umur = umur;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.userType = userType;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public int getUmur() {
        return umur;
    }

    public void setUmur(int umur) {
        this.umur = umur;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }
}
