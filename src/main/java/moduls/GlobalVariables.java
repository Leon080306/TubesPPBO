package moduls;

import controller.UserController;
import models.Booking;
import models.Guest;
import models.Users;

public class GlobalVariables {
    private static Users user;
    private static Booking booking;

    public static Users getUser(){
        return user;
    }

    public static void setUser(Users userData){
        user = userData;
    }

    public static void refreshUserData() {
        user = UserController.getUserDataByUserId(user.getUserID());
    }

    public static Booking getBooking(){return booking;}
    public static void setBooking(Booking bookingData){booking = bookingData;}
}
