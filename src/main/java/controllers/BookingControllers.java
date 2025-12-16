package controllers;

import exceptions.NoResultsFound;
import models.Booking;
import repository.BookingRepository;

import java.time.LocalDateTime;
import java.util.List;

public class BookingControllers {
    BookingRepository bookingRepository;

    public BookingControllers (){
        bookingRepository = new BookingRepository();
    }

    public List<Booking> showAllBooking() throws NoResultsFound {
        try{
            return bookingRepository.getAllBooking();
        } catch (NoResultsFound e) {
            throw new RuntimeException(e);
        }

    }

    public Booking showBooking(String guestID) throws  NoResultsFound{
        return bookingRepository.getBooking(guestID);
    }

    public void addBooking (String guestID, String roomID, String checkIn, String checkOut,int guestTotal){
        bookingRepository.addBooking(guestID, roomID, checkIn, checkOut, guestTotal);
    }

//    public Booking addBooking (String roomID, String checkIn, String checkOut){
//    }

}
