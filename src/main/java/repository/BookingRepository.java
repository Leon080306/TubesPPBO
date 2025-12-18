package repository;

import controller.GuestController;
import controller.RoomController;
import models.Booking;
import models.Payment;
import models.Room;
import models.enums.BookingStatus;
import utils.Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class BookingRepository {
    private static final Connection con = Database.connect();

    public static List<Booking> getBookingByRoomId(String roomId) {
        List<Booking> bookingList = new ArrayList<>();
        try {
            PreparedStatement psmt = con.prepareStatement("SELECT * FROM booking WHERE roomid = ?");
            psmt.setString(1, roomId);
            ResultSet rs = psmt.executeQuery();
            while(rs.next()) {
                bookingList.add(new Booking(rs.getString("bookingid"), rs.getTimestamp("checkindate").toLocalDateTime(), rs.getTimestamp("checkoutdate").toLocalDateTime(), BookingStatus.valueOf(rs.getString("bookingstatus").toUpperCase()), RoomController.getRoomByRoomId(rs.getString("roomid")), rs.getInt("numberofguest"), GuestController.getGuestByGuestId(rs.getString("guestid"))));
            }
            return bookingList;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static List<Booking> getBookingByGuestId(String guestId) {
        List<Booking> bookingList = new ArrayList<>();
        try {
            PreparedStatement psmt = con.prepareStatement("SELECT * FROM booking WHERE guestid = ?");
            psmt.setString(1, guestId);
            ResultSet rs = psmt.executeQuery();
            while(rs.next()) {
                bookingList.add(new Booking(rs.getString("bookingid"), rs.getTimestamp("checkindate").toLocalDateTime(), rs.getTimestamp("checkoutdate").toLocalDateTime(), BookingStatus.valueOf(rs.getString("bookingstatus").toUpperCase()), RoomController.getRoomByRoomId(rs.getString("roomid")), rs.getInt("numberofguest"), GuestController.getGuestByGuestId(rs.getString("guestid"))));
            }
            return bookingList;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
