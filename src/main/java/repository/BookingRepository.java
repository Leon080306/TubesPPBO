package repository;

import exceptions.NoResultsFound;
import models.Booking;
import models.Payment;
import models.Room;
import models.enums.BookingStatus;
import models.enums.PaymentStatus;
import models.enums.PaymentType;
import models.enums.RoomType;
import utils.Database;
import utils.GeneratedUUID;

import javax.xml.crypto.Data;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BookingRepository {
    private static final Connection con = Database.connect();
    PaymentRepository paymentRepository;

    public BookingRepository (){
        this.paymentRepository = new PaymentRepository();
    }

    public List<Booking> getAllBooking () throws NoResultsFound {
        List<Booking> bookingList = new ArrayList<>();
        try{
            PreparedStatement stmt = con.prepareStatement("SELECT b.*, r.*, p.* FROM booking b JOIN room r ON b.roomid = r.roomid JOIN payment p ON b.guestid = p.guestid;");
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
                Booking book = new Booking(bookingID, checkInDate, checkOutDate, bookingStatus, room, totalGuests);
                bookingList.add(book);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return bookingList;
    }

    public Booking getBooking(String guestID) throws NoResultsFound{
        try{
            PreparedStatement stmt = con.prepareStatement("SELECT b.*, r.*, p.* FROM booking b JOIN room r ON b.roomid = r.roomid JOIN payment p ON b.paymentid = p.paymentid WHERE b.guestid = ?;");
            stmt.setString(1,guestID);
            ResultSet result = stmt.executeQuery();
            if(!result.isBeforeFirst()) {
                throw new NoResultsFound();
            }
            result.next();
            String bookingID = result.getString("bookingid");
            String roomID = result.getString("roomid");
            String paymentID = result.getString("paymentid");
            LocalDateTime checkInDate = result.getTimestamp("checkindate").toLocalDateTime();
            LocalDateTime checkOutDate = result.getTimestamp("checkoutdate").toLocalDateTime();
            BookingStatus bookingStatus = BookingStatus.valueOf(result.getString("bookingstatus"));
            int totalGuests = result.getInt("numberofguest");
            double totalPrice = result.getDouble("totalprice");

            RoomType roomType = RoomType.valueOf(result.getString("roomtype"));
            Room room = new Room(roomID, result.getString("roomnumber"), roomType, result.getString("roomdescription"), result.getDouble("roomprice"));

            PaymentType paymentType = PaymentType.valueOf(result.getString("paymenttype"));
            PaymentStatus paymentStatus = PaymentStatus.valueOf(result.getString("paymentstatus"));

            return new Booking(bookingID, checkInDate, checkOutDate, bookingStatus, room, totalGuests);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void addBooking(String guestID, String roomID, String checkIn, String checkOut, int guestTotal){
        try{
            String bookID = GeneratedUUID.generateUUID();
            LocalDate checkOutDate = LocalDate.parse(checkOut);
            LocalDateTime checkOutTime = checkOutDate.atStartOfDay();
            LocalDate checkInDate = LocalDate.parse(checkIn);
            LocalDateTime checkInTime = checkInDate.atStartOfDay();
            PreparedStatement stmt = con.prepareStatement("INSERT INTO booking (bookingid,roomid,checkindate,checkoutdate,bookingstatus,numberofguest,totalprice,guestid) " +
                    "VALUES (?,?,?,?,?::bookingstatus,?,?,?);  ");
            stmt.setString(1,bookID);
            stmt.setString(2,roomID);
            stmt.setTimestamp(3, Timestamp.valueOf(checkInTime));
            stmt.setTimestamp(4,Timestamp.valueOf(checkOutTime));
            stmt.setString(5,BookingStatus.BOOKED.name());
            stmt.setInt(6,guestTotal);
            stmt.setDouble(7, 0);
            stmt.setString(8,guestID);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

}
