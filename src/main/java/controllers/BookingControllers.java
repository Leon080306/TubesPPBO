package controllers;

import exceptions.InvalidInput;
import exceptions.NoResultsFound;
import models.Booking;
import repository.BookingRepository;
import repository.PaymentRepository;

import java.time.LocalDateTime;
import java.util.List;

public class BookingControllers {
    private BookingRepository bookingRepository;
    private PaymentRepository paymentRepository;

    public BookingControllers (){
        bookingRepository = new BookingRepository();
        paymentRepository = new PaymentRepository();
    }

    public List<Booking> showAllBooking() throws NoResultsFound{
            return bookingRepository.getAllBooking();
    }

    public Booking showBooking(String guestID) throws NoResultsFound{
            return bookingRepository.getBooking(guestID);
    }

    public void addBooking (String guestID, int roomnumber, String checkIn, String checkOut,int guestTotal){
        try{
            String bookID = bookingRepository.addBooking(guestID, roomnumber, checkIn, checkOut, guestTotal);
            paymentRepository.addPayment(bookID, guestID);
        } catch (InvalidInput e) {
            System.out.println(e.getMessage());
        }

    }

}
