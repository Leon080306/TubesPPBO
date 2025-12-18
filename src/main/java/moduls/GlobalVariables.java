package moduls;

import controller.UserController;
import models.Booking;
import models.Users;

public class GlobalVariables {
    private static Users user;
    private static Booking booking;

    public static Users getUser(){
        return user;
    }

    public static void setUser(Users newUser){
        user = newUser;
    }

    public static Booking getBooking(){
        return booking;
    }

    public static void setBooking(Booking newBooking){
        booking = newBooking;
    }

    public static void refreshUserData() {
        UserController.getUserDataByEmail(user.getEmail());
    }
}
