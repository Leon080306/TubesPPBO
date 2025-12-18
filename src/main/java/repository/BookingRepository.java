package repository;

import controller.BookingController;
import controller.GuestController;
import controller.RoomController;
import exceptions.InvalidInput;
import exceptions.NoResultsFound;
import models.Booking;
import models.Room;
import models.enums.BookingStatus;
import models.enums.PaymentStatus;
import models.enums.PaymentType;
import models.enums.RoomType;
import moduls.GlobalVariables;
import utils.Database;
import utils.GeneratedUUID;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class BookingRepository {
    private static final Connection con = Database.connect();
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public static List<Booking> getAllBooking () throws NoResultsFound {
        List<Booking> bookingList = new ArrayList<>();
        try{
            PreparedStatement stmt = con.prepareStatement("SELECT b.*, r.*, p.* FROM booking b JOIN room r ON b.roomid = r.roomid JOIN payment p ON b.guestid = p.guestid;");
            ResultSet result = stmt.executeQuery();
            while (result.next()){
                String bookingID = result.getString("bookingid");
                String roomID = result.getString("roomid");
                LocalDateTime checkInDate = result.getTimestamp("checkindate").toLocalDateTime();
                LocalDateTime checkOutDate = result.getTimestamp("checkoutdate").toLocalDateTime();
                BookingStatus bookingStatus = BookingStatus.valueOf(result.getString("bookingstatus"));
                int totalGuests = result.getInt("numberofguest");
                double totalPrice = result.getDouble("totalprice");
                RoomType roomType = RoomType.valueOf(result.getString("roomtype"));
                Room room = new Room(roomID, result.getString("roomnumber"), roomType, result.getString("roomdescription"), result.getDouble("roomprice"));
                PaymentType paymentType = PaymentType.valueOf(result.getString("paymenttype"));
                PaymentStatus paymentStatus = PaymentStatus.valueOf(result.getString("paymentstatus"));
                String guestId = result.getString("guestid");
                Booking book = new Booking(bookingID, checkInDate, checkOutDate, bookingStatus, room, totalGuests, GuestRepository.getGuestByGuestId(guestId));
                bookingList.add(book);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return bookingList;
    }

    public static List<Booking> getAllBooking (String guestID) throws NoResultsFound {
        List<Booking> bookingList = new ArrayList<>();
        try{
            PreparedStatement stmt = con.prepareStatement("SELECT b.*, r.*, p.* FROM booking b JOIN room r ON b.roomid = r.roomid JOIN payment p ON b.guestid = p.guestid WHERE b.guestid=?;");
            stmt.setString(1, guestID);
            ResultSet result = stmt.executeQuery();
            if(!result.isBeforeFirst()) {
                throw new NoResultsFound();
            }

            while (result.next()){
                String bookingID = result.getString("bookingid");
                String roomID = result.getString("roomid");
                LocalDateTime checkInDate = result.getTimestamp("checkindate").toLocalDateTime();
                LocalDateTime checkOutDate = result.getTimestamp("checkoutdate").toLocalDateTime();
                BookingStatus bookingStatus = BookingStatus.valueOf(result.getString("bookingstatus"));
                int totalGuests = result.getInt("numberofguest");
                double totalPrice = result.getDouble("totalprice");

                RoomType roomType = RoomType.valueOf(result.getString("roomtype"));
                Room room = new Room(roomID, result.getString("roomnumber"), roomType, result.getString("roomdescription"), result.getDouble("roomprice"));

                PaymentType paymentType = PaymentType.valueOf(result.getString("paymenttype"));
                PaymentStatus paymentStatus = PaymentStatus.valueOf(result.getString("paymentstatus"));
                Booking book = new Booking(bookingID, checkInDate, checkOutDate, bookingStatus, room, totalGuests, GuestRepository.getGuestByGuestId(guestID));
                bookingList.add(book);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return bookingList;
    }

    public static List<Booking> getBookingByRoomId(String roomId) {
        List<Booking> bookingList = new ArrayList<>();
        try {
            PreparedStatement psmt = con.prepareStatement("SELECT * FROM booking WHERE roomid = ?");
            psmt.setString(1, roomId);
            ResultSet rs = psmt.executeQuery();
            while(rs.next()) {
                bookingList.add(new Booking(rs.getString("bookingid"), rs.getTimestamp("checkindate").toLocalDateTime(), rs.getTimestamp("checkoutdate").toLocalDateTime(), BookingStatus.valueOf(rs.getString("bookingstatus").toUpperCase()), RoomController.getRoomByRoomId(rs.getString("roomid")), rs.getInt("numberofguest"), GuestRepository.getGuestByGuestId(rs.getString("guestid"))));
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

//    public static Booking getBooking(String guestID) throws NoResultsFound{
//        try{
//            PreparedStatement stmt = con.prepareStatement("SELECT b.*, r.*, p.* FROM booking b JOIN room r ON b.roomid = r.roomid JOIN payment p ON b.paymentid = p.paymentid WHERE b.guestid = ?;");
//            stmt.setString(1,guestID);
//            ResultSet result = stmt.executeQuery();
//            if(!result.isBeforeFirst()) {
//                throw new NoResultsFound();
//            }
//            result.next();
//            String bookingID = result.getString("bookingid");
//            String roomID = result.getString("roomid");
//            String paymentID = result.getString("paymentid");
//            LocalDateTime checkInDate = result.getTimestamp("checkindate").toLocalDateTime();
//            LocalDateTime checkOutDate = result.getTimestamp("checkoutdate").toLocalDateTime();
//            BookingStatus bookingStatus = BookingStatus.valueOf(result.getString("bookingstatus"));
//            int totalGuests = result.getInt("numberofguest");
//            double totalPrice = result.getDouble("totalprice");
//
//            RoomType roomType = RoomType.valueOf(result.getString("roomtype"));
//            Room room = new Room(roomID, result.getString("roomnumber"), roomType, result.getString("roomdescription"), result.getDouble("roomprice"));
//
//            PaymentType paymentType = PaymentType.valueOf(result.getString("paymenttype"));
//            PaymentStatus paymentStatus = PaymentStatus.valueOf(result.getString("paymentstatus"));
//
//            return new Booking(bookingID, checkInDate, checkOutDate, bookingStatus, room, totalGuests);
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//    }
    public static Booking addBooking(String guestID, String room, String checkIn, String checkOut, int guestTotal) throws InvalidInput {
        try{
            Room selectedRoom = RoomController.getRoomByRoomNumber(room);
            if (selectedRoom == null) {
                throw new InvalidInput("Room " + room + " does not exist.");
            }

            String bookID = GeneratedUUID.generateUUID();
            LocalDateTime checkInDate = LocalDateTime.parse(checkIn, formatter);
            LocalDateTime checkOutDate = LocalDateTime.parse(checkOut,formatter);
            PreparedStatement stmt = con.prepareStatement("INSERT INTO booking (bookingid,roomid,checkindate,checkoutdate,bookingstatus,numberofguest,totalprice,guestid) " +
                    "SELECT ?,r.roomid,?,?,?::bookingstatus,?,?,? FROM room r WHERE r.roomnumber = ?;  ");
            stmt.setString(1,bookID);
            stmt.setTimestamp(2, Timestamp.valueOf(checkInDate));
            stmt.setTimestamp(3,Timestamp.valueOf(checkOutDate));
            stmt.setString(4,BookingStatus.BOOKED.name());
            stmt.setInt(5,guestTotal);
            stmt.setDouble(6,0);
            stmt.setString(7,guestID);
            stmt.setString(8,room);
            int check = stmt.executeUpdate();
            if (check == 0 ){
                throw new InvalidInput("Room Not Found");
            }
            RoomController.getRoomByRoomId(room);
            return new Booking(bookID,
                    checkInDate,
                    checkOutDate,
                    BookingStatus.BOOKED,
                    selectedRoom,
                    guestTotal,
                    GuestRepository.getGuestByGuestId(guestID)
            );
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public static boolean checkInBooking(String bookingID){
        try{
            String roomID = GlobalVariables.getBooking().getRoom().getRoomID();
            PreparedStatement stmt = con.prepareStatement("UPDATE booking SET bookingstatus = 'CHECKED_IN' WHERE bookingid = ? AND checkindate <= CURRENT_TIMESTAMP" +
            " AND bookingstatus = 'BOOKED' AND roomid = ?");
            stmt.setString(1,bookingID);
            stmt.setString(2,roomID);
            int rowAffected = stmt.executeUpdate();
            return rowAffected > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


}
