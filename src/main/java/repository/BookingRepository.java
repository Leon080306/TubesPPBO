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

import javax.xml.crypto.Data;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BookingRepository {
    private static final Connection con = Database.connect();

    public List<Booking> getAllBooking (String guestID) throws NoResultsFound {
        List<Booking> bookingList = new ArrayList<>();
        try{
            PreparedStatement stmt = con.prepareStatement("SELECT b.bookingid, r.roomid, r.roomtype, r.price, p.paymentid, p.method, p.amount FROM booking b JOIN room r ON b.roomid = r.roomid JOIN payment p ON b.paymentid = p.paymentid;");
            ResultSet result = stmt.executeQuery();
            if(!result.isBeforeFirst()) {
                throw new NoResultsFound();
            }



            while (result.next()){
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
                Payment payment = new Payment(result.getString("paymentid"), result.getInt("totalprice"),result.getTimestamp("paymentdate").toLocalDateTime(), paymentType, paymentStatus) {
                    @Override
                    public boolean processPayment() {
                        return false;
                    }
                };

                Booking book = new Booking(bookingID, checkInDate, checkOutDate, bookingStatus, room, payment, totalGuests);
                bookingList.add(book);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bookingList;
    }
}
