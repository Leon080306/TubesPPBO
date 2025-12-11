//package views;
//
//import controllers.BookingControllers;
//import exceptions.NoResultsFound;
//import models.Payment;
//import models.PaymentByCash;
//
//import javax.swing.*;
//import java.awt.*;
//import java.util.List;
//
//public class PaymentView {
//    MainFrame frame;
//    BookingControllers bookingControllers;
//
//    public BookingView() throws NoResultsFound {
//        bookingControllers = new BookingControllers();
//        List<Payment> paymentList = bookingControllers.(); // <-- FETCH BOOKINGS
//        displayAllPayment(paymentList);
//    }
//    public void displayAllPayment(List<Booking> bookings) {
//        frame = new MainFrame();
//        JPanel mainPanel = new JPanel(new BorderLayout());
//
//
//        JLabel title = new JLabel("Hotel Harapan Bangsa Booking");
//        title.setFont(new Font("Poppins", Font.BOLD, 32));
//        title.setHorizontalAlignment(SwingConstants.CENTER);
//        title.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
//        mainPanel.add(title, BorderLayout.NORTH);
//
//        JPanel centralPanel = new JPanel();
//        centralPanel.setLayout(new BoxLayout(centralPanel, BoxLayout.Y_AXIS));
//        centralPanel.setBackground(Color.WHITE);
//        centralPanel.setBackground(Color.WHITE);
//
//
//        for (Booking b : bookings) {
//            JPanel bookingPanel = new JPanel();
//            bookingPanel.setLayout(new BoxLayout(bookingPanel, BoxLayout.Y_AXIS));
//            bookingPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
//
//            bookingPanel.add(new JLabel("Booking ID: " + b.getBookingID()));
//            bookingPanel.add(new JLabel("Check In: " + b.getCheckInDate()));
//            bookingPanel.add(new JLabel("Check Out: " + b.getCheckOutDate()));
//            bookingPanel.add(new JLabel("Status: " + b.getBookingStatus()));
//            bookingPanel.add(new JLabel("Guests: " + b.getNumberOfGuests()));
//            bookingPanel.add(new JLabel("Total Price: " + b.getPayment().getTotalPrice()));
//
//            bookingPanel.add(new JLabel(" >> ROOM"));
//            bookingPanel.add(new JLabel("Room Number: " + b.getRoom().getRoomNumber()));
//            bookingPanel.add(new JLabel("Room Type: " + b.getRoom().getRoomType()));
//            bookingPanel.add(new JLabel("Room Price: " + b.getRoom().getRoomPrice()));
//
//            bookingPanel.add(new JLabel(" >> PAYMENT"));
//            bookingPanel.add(new JLabel("Payment Method: " + b.getPayment().getPaymentType()));
//            bookingPanel.add(new JLabel("Payment Status: " + b.getPayment().getPaymentStatus()));
//            bookingPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
//            centralPanel.add(bookingPanel);
//
//
//
//            centralPanel.add(Box.createVerticalStrut(15)); // space between bookings
//        }
//        JScrollPane scroll = new JScrollPane(centralPanel);
//        mainPanel.add(title, BorderLayout.NORTH);
//        mainPanel.add(scroll, BorderLayout.CENTER);
//        frame.add(mainPanel);   // assuming MainFrame extends JFrame
//        frame.setVisible(true);
//
//    }
