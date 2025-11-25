package views;

import models.Booking;

import java.util.List;

public class BookingView {
    public void displayAllBookings(List<Booking> bookings) {
        System.out.println("\n===== ALL BOOKINGS =====");

        for (Booking b : bookings) {
            System.out.println("Booking ID: " + b.getBookingID());
            System.out.println("Check In: " + b.getCheckInDate());
            System.out.println("Check Out: " + b.getCheckOutDate());
            System.out.println("Status: " + b.getBookingStatus());
            System.out.println("Guests: " + b.getNumberOfGuests());
            System.out.println("Total Price: " + b.getPayment());

            System.out.println(" >> ROOM");
            System.out.println("Room ID: " + b.getRoom().getRoomID());
            System.out.println("Room Number: " + b.getRoom().getRoomNumber());
            System.out.println("Room Type: " + b.getRoom().getRoomType());
            System.out.println("Room Price: " + b.getRoom().getRoomPrice());

            System.out.println(" >> PAYMENT");
            System.out.println("Payment ID: " + b.getPayment().getPaymentID());
            System.out.println("Payment Date: " + b.getPayment().getPaymentDate());
            System.out.println("Payment Method: " + b.getPayment().getPaymentType());
            System.out.println("Payment Status: " + b.getPayment().getPaymentStatus());

            System.out.println("-----------------------------");
        }
    }
}
