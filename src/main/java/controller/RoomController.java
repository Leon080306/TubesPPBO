package controller;

import models.Booking;
import models.Room;
import models.enums.BookingStatus;
import models.enums.RoomType;
import repository.RoomRepository;

import java.time.LocalDateTime;
import java.util.List;


public class RoomController {

    public static boolean addRoom(String roomNumber, RoomType roomType, String roomDescription, double roomPrice) {
        return RoomRepository.addRoom(roomNumber, roomType, roomDescription, roomPrice);
    }

    public static boolean isOccupied(String roomId) {
        List<Booking> bookingList = BookingController.getBookingByRoomId(roomId);
        if(bookingList.isEmpty()) {
            return false;
        }
        for(Booking booking : bookingList) {
            if (booking.getBookingStatus() == BookingStatus.CHECKED_IN && booking.getCheckInDate().isBefore(LocalDateTime.now()) && booking.getCheckOutDate().isAfter(LocalDateTime.now())) {
                return true;
            }
        }
        return false;
    }

    public static Room getRoomByRoomId(String roomId) {
        return RoomRepository.getRoomByRoomId(roomId);
    }

    public static boolean deleteRoom(String roomId) {
        return RoomRepository.deleteRoom(roomId);
    }

    public static boolean updateRoom(String roomNumber, RoomType roomType, String roomDescription, double roomPrice, String roomId) {
        return RoomRepository.updateRoom(roomNumber, roomType, roomDescription, roomPrice, roomId);
    }

    public static List<Room> getAllRooms() {
        return RoomRepository.getAllRooms();
    }
}
