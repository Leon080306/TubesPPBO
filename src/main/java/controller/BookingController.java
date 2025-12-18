package controller;

import exceptions.InvalidInput;
import exceptions.NoResultsFound;
import com.sun.jdi.PathSearchingVirtualMachine;
import models.Booking;
import repository.BookingRepository;
import repository.PaymentRepository;

import java.util.ArrayList;
import java.time.LocalDateTime;
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
            Booking booking = BookingRepository.addBooking(guestID, roomnumber, checkIn, checkOut, guestTotal);
            PaymentRepository.addPayment(booking.getBookingID(), guestID);
            return booking;
        } catch (InvalidInput e) {
            System.out.println(e.getMessage());
            return  null;
        }
    }
    public static List<Booking> getBookingByRoomId(String roomId) {
        return BookingRepository.getBookingByRoomId(roomId);
    }

    public static List<Booking> getBookingByGuestId(String guestId) {
        return BookingRepository.getBookingByGuestId(guestId);
    }

    public static Booking getCurrentBooking(String guestId) {
        System.out.println(getBookingByGuestId(guestId).size());
        for(Booking booking : getBookingByGuestId(guestId)) {
            if(booking.getCheckInDate().isBefore(LocalDateTime.now()) && booking.getCheckOutDate().isAfter(LocalDateTime.now())) {
                return booking;
            }
        }
        return null;
    }

    public static boolean checkInBooking(String bookingID){
        return BookingRepository.checkInBooking(bookingID);
    }
}
