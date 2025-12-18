package views;

import controller.PaymentControllers;
import exceptions.NoResultsFound;
import models.*;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class GuestPaymentView {

    private static final String GUEST_ID =
            "e5f8a9b0-5d8c-4d3f-9d5e-55555555eeee";

    MainFrame frame;
    PaymentControllers paymentControllers;

    public GuestPaymentView() throws NoResultsFound {
        paymentControllers = new PaymentControllers();
        List<Payment> payments = paymentControllers.showPaymentHistory(GUEST_ID);
        displayAllPayments(payments);
    }

    //to see type of payment


    public void displayAllPayments(List<Payment> payments) {
        frame = new MainFrame(true);

        JPanel mainPanel = new JPanel(new BorderLayout());

        JLabel title = new JLabel("Hotel Harapan Bangsa - Payment History");
        title.setFont(new Font("Poppins", Font.BOLD, 32));
        title.setHorizontalAlignment(SwingConstants.CENTER);
        title.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));

        JPanel centralPanel = new JPanel();
        centralPanel.setLayout(new BoxLayout(centralPanel, BoxLayout.Y_AXIS));
        centralPanel.setBackground(Color.WHITE);

        for (Payment p : payments) {
            JPanel paymentPanel = new JPanel();
            paymentPanel.setLayout(new BoxLayout(paymentPanel, BoxLayout.Y_AXIS));
            paymentPanel.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(Color.GRAY),
                    BorderFactory.createEmptyBorder(10, 10, 10, 10)
            ));

            // PAYMENT INFO
            paymentPanel.add(new JLabel("Payment ID: " + p.getPaymentID()));
            paymentPanel.add(new JLabel("Date: " + p.getPaymentDate()));
            paymentPanel.add(new JLabel("Type: " + p.getPaymentType()));
            paymentPanel.add(new JLabel("Status: " + p.getPaymentStatus()));
            paymentPanel.add(new JLabel("Total Price: Rp " + p.getTotalPrice()));
            if (p instanceof PaymentByCash cash){
                paymentPanel.add(new JLabel("Tips : " + cash.getTip()));
            } else if (p instanceof PaymentByCard card){
                paymentPanel.add(new JLabel("Credit Card Number : " + card.getCreditCardNumber()));
            } else if (p instanceof PaymentByEWallet eWallet){
                paymentPanel.add(new JLabel("E Wallet Provider : " + eWallet.getProvider()));
                paymentPanel.add(new JLabel("E Wallet Account ID : " + eWallet.getAccountID()));
            }
            paymentPanel.add(Box.createVerticalStrut(8));

            // BOOKING INFO
            paymentPanel.add(new JLabel(">> BOOKING"));
            paymentPanel.add(new JLabel("Booking ID: " + p.getBooking().getBookingID()));
            paymentPanel.add(new JLabel("Check In: " + p.getBooking().getCheckInDate()));
            paymentPanel.add(new JLabel("Check Out: " + p.getBooking().getCheckOutDate()));
            paymentPanel.add(new JLabel("Guests: " + p.getBooking().getNumberOfGuests()));
            paymentPanel.add(new JLabel("Room: " +
                    p.getBooking().getRoom().getRoomType() +
                    " (Room " + p.getBooking().getRoom().getRoomNumber() + ")"));

            // EXTRA SERVICE (optional)
            ExtraServices extra = p.getExtraService();
            if (extra != null) {
                paymentPanel.add(Box.createVerticalStrut(6));
                paymentPanel.add(new JLabel(">> EXTRA SERVICE"));
                paymentPanel.add(new JLabel("Service: " + extra.getTitle()));
                paymentPanel.add(new JLabel("Description: " + extra.getDescription()));
                paymentPanel.add(new JLabel("Price: Rp " + extra.getPrice()));
                paymentPanel.add(new JLabel("Status: " + extra.isStatus()));
            }

            paymentPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
            centralPanel.add(paymentPanel);
            centralPanel.add(Box.createVerticalStrut(15));
        }

        JScrollPane scrollPane = new JScrollPane(centralPanel);

        mainPanel.add(title, BorderLayout.NORTH);
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        frame.add(mainPanel);
        frame.setVisible(true);
    }
}
