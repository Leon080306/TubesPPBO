package views.admin;

import controllers.BookingControllers;
import exceptions.NoResultsFound;
import models.Booking;
import views.MainFrame;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class AdminBookingView {
    MainFrame frame;
    public AdminBookingView(){// <-- FETCH BOOKINGS
        displayAllBookings();
    }
    public void displayAllBookings() {
        frame = new MainFrame();
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        JPanel titlePanel = new JPanel(new BorderLayout());
        JLabel title = new JLabel("BOOKING MANAGEMENT", SwingConstants.CENTER);
        title.setFont(new Font("Poppins", Font.BOLD, 32));
        titlePanel.add(title, BorderLayout.CENTER);
        mainPanel.add(titlePanel);
        mainPanel.add(Box.createVerticalStrut(10));

        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new BoxLayout(buttonsPanel, BoxLayout.Y_AXIS));
        buttonsPanel.setAlignmentX(Component.CENTER_ALIGNMENT);


        for (Booking b : BookingControllers.showAllBooking()) {
            JPanel bookingPanel = new JPanel();
            bookingPanel.setLayout(new BoxLayout(bookingPanel, BoxLayout.Y_AXIS));
            bookingPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
            bookingPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));

            //Room Number
            JLabel roomNumber = new JLabel(b.getRoom().getRoomNumber());
            roomNumber.setPreferredSize(new Dimension(50,25));
            bookingPanel.add(roomNumber);

            //Check In
            JLabel checkInDate = new JLabel(String.valueOf(b.getCheckInDate()));
            checkInDate.setPreferredSize(new Dimension(50,25));
            bookingPanel.add(checkInDate);
            //Check Out
            JLabel checkOutDate = new JLabel(String.valueOf(b.getCheckOutDate()));
            checkOutDate.setPreferredSize(new Dimension(50,25));
            bookingPanel.add(checkOutDate);

            //Booking Status
            String status = "";
            if() {
                status = "YES";
            }
            else {
                status = "NO";
            }
            JLabel roomStatus = new JLabel(status);
            roomStatus.setPreferredSize(new Dimension(25, 25));
            roomPanel.add(roomStatus);


            bookingPanel.add(new JLabel("Booking ID: " + b.getBookingID()));
            bookingPanel.add(new JLabel("Check In: " + b.getCheckInDate()));
            bookingPanel.add(new JLabel("Check Out: " + b.getCheckOutDate()));
            bookingPanel.add(new JLabel("Status: " + b.getBookingStatus()));
            bookingPanel.add(new JLabel("Guests: " + b.getNumberOfGuests()));

            bookingPanel.add(new JLabel(" >> ROOM"));
            bookingPanel.add(new JLabel("Room Number: " + b.getRoom().getRoomNumber()));
            bookingPanel.add(new JLabel("Room Type: " + b.getRoom().getRoomType()));
            bookingPanel.add(new JLabel("Room Price: " + b.getRoom().getRoomPrice()));
            bookingPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
            centralPanel.add(bookingPanel);



            centralPanel.add(Box.createVerticalStrut(15)); // space between bookings
        }
        JScrollPane scroll = new JScrollPane(centralPanel);
        mainPanel.add(title, BorderLayout.NORTH);
        mainPanel.add(scroll, BorderLayout.CENTER);
        frame.add(mainPanel);   // assuming MainFrame extends JFrame
        frame.setVisible(true);


    }


}
