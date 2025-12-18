package controller;

import exceptions.NoResultsFound;
import models.Guest;
import models.Users;
import models.enums.UserType;
import repository.UserRepository;
import utils.PasswordHashing;

import java.util.List;

public class UserController {
    public static boolean deleteUser(String userId) {
        return UserRepository.deleteUser(userId);
    }

    public static boolean addUser(String password, String name, int umur, String email, String phone, String address, UserType type) {
        return UserRepository.addUser(password, name, umur, email, phone, address, type) != null;
    }

    public static List<Users> getAllUsers() {
        return UserRepository.getAllUsers();
    }

    public static UserType login(String email, String password) {
        Users user = getUserDataByEmail(email);
        if(user == null || !PasswordHashing.verifyPassword(password, user.getPassword())) {
            return null;
        }
        return user.getUserType();
    }

    public static Users getUserDataByEmail(String email) {
        try {
            return UserRepository.getUserDataByEmail(email);
        } catch (NoResultsFound e) {
            return null;
        }
    }

    public static Users getUserDataByUserId(String userId) {
        try {
            return UserRepository.getUserDataByUserId(userId);
        } catch (NoResultsFound e) {
            return null;
        }
    }

    //with password
    public static boolean updateUserData(String password, String nama, int umur, String email, String phone, String address, UserType type, String userId) {
        return UserRepository.updateUserData(password, nama, umur, email, phone, address, type, userId);
    }

    //without password
    public static boolean updateUserData(String nama, int umur, String email, String phone, String address, UserType type, String userId) {
        return UserRepository.updateUserData(nama, umur, email, phone, address, type, userId);
    }
}
