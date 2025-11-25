package controllers;

import exceptions.NoResultsFound;
import models.Booking;
import repository.BookingRepository;

import java.util.List;

public class BookingControllers {
    BookingRepository bookingRepository;

    public BookingControllers (){
        bookingRepository = new BookingRepository();
    }

    public List<Booking> showAllBooking() throws NoResultsFound {
        return bookingRepository.getAllBooking();
    }

    public Booking showBooking(String guestID) throws  NoResultsFound{
        return bookingRepository.getBooking(guestID);
    }


}
