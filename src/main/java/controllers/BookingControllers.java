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

    public List<Booking> showAllBooking(){
        try{
            return bookingRepository.getAllBooking();
        } catch (NoResultsFound e) {
            throw new RuntimeException(e);
        }

    }

    public Booking showBooking(String guestID){
        try{
            return bookingRepository.getBooking(guestID);
        } catch (NoResultsFound e) {
            throw new RuntimeException(e);
        }

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
