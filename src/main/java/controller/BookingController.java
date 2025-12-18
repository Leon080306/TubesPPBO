package controller;

import com.sun.jdi.PathSearchingVirtualMachine;
import models.Booking;
import repository.BookingRepository;

import java.time.LocalDateTime;
import java.util.List;

public class BookingController {
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
}
