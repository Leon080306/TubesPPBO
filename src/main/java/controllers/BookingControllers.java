package controllers;

import exceptions.InvalidInput;
import exceptions.NoResultsFound;
import models.Booking;
import repository.BookingRepository;
import repository.PaymentRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class BookingControllers {

    public static List<Booking> showAllBooking(){
        try{
            return BookingRepository.getAllBooking();
        } catch (NoResultsFound e) {
            System.out.println(e.getMessage());
            return new ArrayList<>();
        }
    }

    public static Booking showBooking(String guestID) throws NoResultsFound{
            return BookingRepository.getBooking(guestID);
    }

    public static void addBooking (String guestID, int roomnumber, String checkIn, String checkOut,int guestTotal){
        try{
            String bookID = BookingRepository.addBooking(guestID, roomnumber, checkIn, checkOut, guestTotal);
            PaymentRepository.addPayment(bookID, guestID);
        } catch (InvalidInput e) {
            System.out.println(e.getMessage());
        }

    }

    public static boolean isCheckIn (String ){

    }
}
