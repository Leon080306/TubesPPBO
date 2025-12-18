package repository;

import exceptions.NoResultsFound;
import exceptions.UnderPaymentHandling;
import models.*;
import models.enums.*;
import utils.Database;
import utils.GeneratedUUID;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class PaymentRepository {
    private static final Connection con = Database.connect();

    public List<Payment> getPaymentHistory(String guestId) throws NoResultsFound {
        List<Payment> paymentList = new ArrayList<>();
        try {
            PreparedStatement stmt = con.prepareStatement("SELECT p.*, t.*, r.*, b.* FROM payment p JOIN task t ON p.bookingid = t.bookingid JOIN booking b ON p.bookingid = b.bookingid JOIN room r ON b.roomid = r.roomid WHERE p.guestid= ? ;");
            stmt.setString(1, guestId);
            ResultSet result = stmt.executeQuery();
            if (!result.isBeforeFirst()) {
                throw new NoResultsFound();
            }

            while (result.next()) {
                PaymentType paymentType = PaymentType.valueOf(result.getString("paymenttype"));
                PaymentStatus paymentStatus = PaymentStatus.valueOf(result.getString("paymentstatus"));
                String paymentID = result.getString("paymentid");
                double totalPrice = result.getDouble("amountpaid") + result.getDouble("price");
                String roomID = result.getString("roomid");
                RoomType roomType = RoomType.valueOf(result.getString("roomtype"));
                Room room = new Room(roomID, result.getString("roomnumber"), roomType, result.getString("roomdescription"), result.getDouble("roomprice"));
                LocalDateTime paymentDate = result.getTimestamp("paymentdate").toLocalDateTime();
                Timestamp completedAt = result.getTimestamp("completedat");
                LocalDateTime completedAtDate =
                        completedAt != null ? completedAt.toLocalDateTime() : null;
                Booking booking = new Booking(result.getString("bookingid"),
                        result.getTimestamp("checkindate").toLocalDateTime(),
                        result.getTimestamp("checkoutdate").toLocalDateTime(),
                        BookingStatus.valueOf(result.getString("bookingstatus")),
                        room,result.getInt("numberofguest")
                );
                ExtraServices extraServices = new ExtraServices(result.getString("taskid"),
                        result.getString("title"),
                        result.getString("description"),
                        TaskStatus.valueOf(result.getString("status")),
                        result.getTimestamp("deadline").toLocalDateTime(),
                        completedAtDate,
                        result.getDouble("price")
                );
                if (paymentType.name().equals("CARD")){
                    String creditCardNumber = result.getString("creditcardnumber");
                    paymentList.add(new PaymentByCard(paymentID,totalPrice,paymentDate,paymentStatus, booking, extraServices, creditCardNumber));
                } else if (paymentType.name().equals("CASH")) {
                    double tip = result.getDouble("tips");
                    paymentList.add(new PaymentByCash(paymentID,totalPrice,paymentDate,paymentStatus, booking, extraServices, tip));
                } else{
                    String eWalletProvider = result.getString("ewalletprovider");
                    String eWalletAccountID = result.getString("ewalletaccountid");
                    paymentList.add(new PaymentByEWallet(paymentID,totalPrice,paymentDate,paymentStatus,booking, extraServices, eWalletProvider,eWalletAccountID));
                }

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return paymentList;
    }

    public void addPayment(String bookingID, String guestID){
        String paymentID = new GeneratedUUID().toString();
        try{
            PreparedStatement stmt = con.prepareStatement("INSERT INTO payment (paymentid,paymentdate,paymenttype,paymentstatus,bookingid,guestid,amountpaid) " +
            "SELECT ?,?,'CASH','PENDING',b.bookingid, b.guestid, r.roomprice + SUM(t.price) FROM booking b JOIN room r ON b.roomid = r.roomid JOIN task t ON t.bookingid = b.bookingid WHERE b.bookingid = ? " +
            "GROUP BY b.bookingid, b.guestid, r.roomprice;");
            stmt.setString(1,paymentID);
            stmt.setTimestamp(2, Timestamp.valueOf(LocalDateTime.now()));
            stmt.setString(3,bookingID);
            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public double getAmountNeeed(String paymentid, String guestID, String bookingid) throws NoResultsFound{
        try{
            PreparedStatement stmt = con.prepareStatement("SELECT amountpaid FROM payment WHERE bookingid=? AND guestid=? AND bookingid=?");
            stmt.setString(1,paymentid);
            stmt.setString(2,guestID);
            ResultSet result = stmt.executeQuery();
            if (result.next()){
                return result.getDouble("amountpaid");
            }else{
                throw new NoResultsFound();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void cashPayment(String paymentID, double nominal, double tips, String guestID, String bookingID) throws UnderPaymentHandling, NoResultsFound {

        try {
            double amountNeeded = getAmountNeeed(paymentID,guestID,bookingID);
            if (nominal<amountNeeded){
                throw new UnderPaymentHandling();
            }
            PreparedStatement stmt = con.prepareStatement("UPDATE payment SET tips = ?, paymentstatus = 'SUCCESSFUL' WHERE paymentid=? AND guestid=? AND bookingid=? ;");
            stmt.setDouble(1,tips);
            stmt.setString(2,paymentID);
            stmt.setString(3,guestID);
            stmt.setString(4,bookingID);
            stmt.executeUpdate();
        }catch (SQLException e) {
            throw new RuntimeException();
        }
    }

    public void cardPayment(String paymentID, double nominal, String creditCardNumber , String guestID, String bookingID) throws UnderPaymentHandling, NoResultsFound {

        try {
            double amountNeeded = getAmountNeeed(paymentID,guestID,bookingID);
            if (nominal<amountNeeded){
                throw new UnderPaymentHandling();
            }
            PreparedStatement stmt = con.prepareStatement("UPDATE payment SET creditcardnumber = ?, paymenttype = 'CARD', paymentstatus = 'SUCCESSFUL' WHERE paymentid=? AND guestid=? AND bookingid=? ;");
            stmt.setString(1,creditCardNumber);
            stmt.setString(2,paymentID);
            stmt.setString(3,guestID);
            stmt.setString(4,bookingID);
            stmt.executeUpdate();
        }catch (SQLException e) {
            throw new RuntimeException();
        }
    }

    public void eWalletPayment(String paymentID, double nominal, String eWalletProvider, String eWalletAccountID , String guestID, String bookingID) throws UnderPaymentHandling, NoResultsFound {

        try {
            double amountNeeded = getAmountNeeed(paymentID,guestID,bookingID);
            if (nominal<amountNeeded){
                throw new UnderPaymentHandling();
            }
            PreparedStatement stmt = con.prepareStatement("UPDATE payment SET ewalletprovider = ?, ewalletaccountid = ?, paymenttype = 'E_WALLET', paymentstatus = 'SUCCESSFUL' WHERE paymentid=? AND guestid=? AND bookingid=? ;");
            stmt.setString(1,eWalletProvider);
            stmt.setString(2,eWalletAccountID);
            stmt.setString(3,paymentID);
            stmt.setString(4,guestID);
            stmt.setString(5,bookingID);
            stmt.executeUpdate();
        }catch (SQLException e) {
            throw new RuntimeException();
        }
    }


}
