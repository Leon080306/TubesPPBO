package moduls;

import models.Booking;
import models.Users;

public class GlobalVariables {
    private static Users user;
    private static Booking booking;

    public Users getUser(){
        return this.user;
    }

    public void setUser(Users user){
        this.user = user;
    }

    public Booking getBooking(){
        return this.booking;
    }

    public void setBooking(Booking booking){
        
    }
}
