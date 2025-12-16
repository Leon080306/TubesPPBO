package repository;

import exceptions.NoResultsFound;
import exceptions.UnderPaymentHandling;
import models.Payment;
import models.PaymentByCard;
import models.PaymentByCash;
import models.PaymentByEWallet;
import models.enums.PaymentStatus;
import models.enums.PaymentType;
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
            PreparedStatement stmt = con.prepareStatement("SELECT p.*, b. FROM payment WHERE guestid= ? ;");
            stmt.setString(1, guestId);
            ResultSet result = stmt.executeQuery();
            if (!result.isBeforeFirst()) {
                throw new NoResultsFound();
            }

            while (result.next()) {
                PaymentType paymentType = PaymentType.valueOf(result.getString("paymenttype"));
                PaymentStatus paymentStatus = PaymentStatus.valueOf(result.getString("paymentstatus"));
                String paymentID = result.getString("paymentid");
                double totalPrice = result.getDouble("amountpaid");
                LocalDateTime paymentDate = result.getTimestamp("paymentdate").toLocalDateTime();

                if (paymentType.name().equals("CARD")){
                    String creditCardNumber = result.getString("creditcardnumberpin");
                    paymentList.add(new PaymentByCard(paymentID,totalPrice,paymentDate,paymentStatus,creditCardNumber));
                } else if (paymentType.name().equals("CASH")) {
                    double tip = result.getDouble("tips");
                    paymentList.add(new PaymentByCash(paymentID,totalPrice,paymentDate,paymentStatus,tip));
                } else{
                    String eWalletProvider = result.getString("ewalletprovider");
                    String eWalletAccountID = result.getString("ewalletaccountid");
                    paymentList.add(new PaymentByEWallet(paymentID,totalPrice,paymentDate,paymentStatus,eWalletProvider,eWalletAccountID));
                }

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return paymentList;
    }

    public String addPayment(String bookingID, String guestID){
        String paymentID = new GeneratedUUID().toString();
        try{
            PreparedStatement stmt = con.prepareStatement("INSERT INTO payment (paymentid,paymentdate,paymenttype,paymentstatus,bookingid,guestid,amountpaid) " +
            "SELECT ?,?,'CASH','PENDING',b.bookingid, b.guestid, r.roomprice + SUM(t.price) FROM booking b JOIN room r ON b.roomid = r.roomid JOIN task t ON t.bookingid = b.bookingid WHERE b.bookingid = ? " +
            "GROUP BY b.bookingid, b.guestid, r.roomprice;");
            stmt.setString(1,paymentID);
            stmt.setTimestamp(2, Timestamp.valueOf(LocalDateTime.now()));
            stmt.setString(3,bookingID);
            stmt.executeUpdate();

            return paymentID;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void cashPayment(String paymentID, double nominal, double tips, String guestID, String bookingID) throws UnderPaymentHandling {

        try {
            PreparedStatement stmt = con.prepareStatement("INSERT INTO payment (paymentid,paymentdate,paymenttype,paymentstatus,creditcardnumberpin,amountpaid,ewalletprovider,ewalletaccountid,bookingid,tips,guestid) VALUES(?,?,?,?,?,?,?,?,?,?,?);");
            stmt.setString(1,paymentID);
        }catch (SQLException e) {
            throw new UnderPaymentHandling();
        }
    }
}
