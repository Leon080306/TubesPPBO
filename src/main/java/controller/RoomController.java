package controller;

import models.Room;
import models.enums.RoomType;
import repository.RoomRepository;

import java.util.List;


public class RoomController {

    public static boolean addRoom(int roomNumber, RoomType roomType, String roomDescription, double roomPrice) {
        return RoomRepository.addRoom(roomNumber, roomType, roomDescription, roomPrice);
    }

    public static boolean isOccupied(String roomId) {
        return !BookingController.getBookingByRoomId(roomId).isEmpty();
    }

    public static Room getRoomByRoomId(String roomId) {
        return RoomRepository.getRoomByRoomId(roomId);
    }

    public static boolean deleteRoom(String roomId) {
        return RoomRepository.deleteRoom(roomId);
    }

    public static boolean updateRoom(int roomNumber, RoomType roomType, String roomDescription, double roomPrice, String roomId) {
        return RoomRepository.updateRoom(roomNumber, roomType, roomDescription, roomPrice, roomId);
    }

    public static List<Room> getAllRooms() {
        return RoomRepository.getAllRooms();
    }
}
