package controller;

import exceptions.InvalidInput;
import exceptions.NoResultsFound;
import models.Booking;
import repository.BookingRepository;
import repository.PaymentRepository;

import java.util.ArrayList;
import java.util.List;

public class BookingController {

    public static List<Booking> showAllBooking(){
        try{
            return BookingRepository.getAllBooking();
        } catch (NoResultsFound e) {
            System.out.println(e.getMessage());
            return new ArrayList<>();
        }
    }

    public static List<Booking> showAllBooking(String guestID){
        try{
            return BookingRepository.getAllBooking(guestID);
        } catch (NoResultsFound e) {
            System.out.println(e.getMessage());
            return new ArrayList<>();
        }
    }

//    public static Booking showBooking(String guestID) throws NoResultsFound{
//            return BookingRepository.getBooking(guestID);
//    }

    public static Booking addBooking (String guestID, String roomnumber, String checkIn, String checkOut,int guestTotal){
        try{
            String bookID = BookingRepository.addBooking(guestID, roomnumber, checkIn, checkOut, guestTotal);
            PaymentRepository.addPayment(bookID, guestID);
            return bookID;
        } catch (InvalidInput e) {
            System.out.println(e.getMessage());
            return  null;
        }
    }

    public static boolean isOccupied (String roomNumber,String checkIn, String checkOut){
        return BookingRepository.isOccupied(roomNumber,checkIn,checkOut);
    }
    public static List<Booking> getBookingByRoomId(String roomId) {
        return BookingRepository.getBookingByRoomId(roomId);
    }

    public static boolean checkInBooking(String bookingID){
        return BookingRepository.checkInBooking(bookingID);
    }
}
