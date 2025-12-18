package controller;

import models.Booking;
import repository.BookingRepository;

import java.util.List;

public class BookingController {
    public static List<Booking> getBookingByRoomId(String roomId) {
        return BookingRepository.getBookingByRoomId(roomId);
    }
}
