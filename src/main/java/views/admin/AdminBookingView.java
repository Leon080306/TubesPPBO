package views.admin;

import controller.BookingController;
import models.Booking;
import views.MainFrame;

import javax.swing.*;
import java.awt.*;

public class AdminBookingView {
    private MainFrame frame;
    public AdminBookingView(){// <-- FETCH BOOKINGS
        frame = new MainFrame(true);
        displayAllBookings();
    }
    // This is your custom "Label Machine"
    private JLabel createHeaderLabel(String text, int width) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Poppins", Font.BOLD, 14)); // Makes it look punchy!
        label.setPreferredSize(new Dimension(width, 25)); // Keeps everything aligned!
        return label;
    }

    public void displayAllBookings() {
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

        //Back button
        JButton backButton = new JButton("Back");
        backButton.addActionListener(e -> {
            frame.dispose();
            new AdminMainMenu();
        });
        backButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        buttonsPanel.add(backButton);
        buttonsPanel.add(Box.createRigidArea(new Dimension(20, 0)));

        mainPanel.add(buttonsPanel);

        mainPanel.add(Box.createVerticalStrut(20));

        JPanel bookingListPanel = new JPanel();
        bookingListPanel.setLayout(new BoxLayout(bookingListPanel, BoxLayout.Y_AXIS));
        bookingListPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // 1. Create the container for the list
        JPanel containerPanel = new JPanel();
        containerPanel.setLayout(new BoxLayout(containerPanel, BoxLayout.Y_AXIS));

// 2. CREATE THE HEADER (OUTSIDE THE LOOP!)
        JPanel headerPanel = new JPanel();
        headerPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 20, 0));
        headerPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
        headerPanel.setBackground(new Color(230, 230, 230)); // Light grey so it looks like a header

// Add your column names here
        headerPanel.add(createHeaderLabel("Room", 50));
        headerPanel.add(createHeaderLabel("Description", 150));
        headerPanel.add(createHeaderLabel("Check In", 100));
        headerPanel.add(createHeaderLabel("Check Out", 100));
        headerPanel.add(createHeaderLabel("Status", 100));

// Add the header to the list panel FIRST
        containerPanel.add(headerPanel);
        containerPanel.add(Box.createVerticalStrut(10)); // Gap between header and first row

        for (Booking b : BookingController.showAllBooking()) {
            JPanel bookingPanel = new JPanel();
            bookingPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 20,0));
            bookingPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
            bookingPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));

            //Room Number
            JLabel roomNumber = new JLabel(b.getRoom().getRoomNumber());
            roomNumber.setPreferredSize(new Dimension(50,25));
            bookingPanel.add(roomNumber);

            //Room Description
            JLabel roomDesc = new JLabel(b.getRoom().getRoomDescription());
            roomDesc.setPreferredSize(new Dimension(150,25));
            bookingPanel.add(roomDesc);

            //Check In
            JLabel checkInDate = new JLabel(String.valueOf(b.getCheckInDate()));
            checkInDate.setPreferredSize(new Dimension(100,25));
            bookingPanel.add(checkInDate);
            //Check Out
            JLabel checkOutDate = new JLabel(String.valueOf(b.getCheckOutDate()));
            checkOutDate.setPreferredSize(new Dimension(100,25));
            bookingPanel.add(checkOutDate);

            //Booking Status
            String status = b.getBookingStatus().name();
            String displayStatus = "";
            switch (status){
                case "BOOKED":
                    displayStatus = "Booked";
                    break;
                case "CHECKED_IN":
                    displayStatus = "Check In";
                    break;
                case "CHECKED_OUT":
                    displayStatus = "Check Out";
                    break;
                default:
                    displayStatus = "Canceled";
            }

            JLabel roomStatus = new JLabel(displayStatus);
            roomStatus.setPreferredSize(new Dimension(100, 25));
            if(status.equals("CANCELED")) roomStatus.setForeground(Color.RED);
            if(status.equals("CHECKED_IN")) roomStatus.setForeground(Color.GREEN);

            bookingPanel.add(roomStatus);

            // ADD TO THE CONTAINER, NOT BUTTONS!
            containerPanel.add(bookingPanel);
            containerPanel.add(Box.createVerticalStrut(5));
        }
        JScrollPane scrollPane = new JScrollPane(containerPanel);
        scrollPane.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(scrollPane);

        frame.addComponent(mainPanel);
        frame.showFrame();
    }

    public static void main(String[] args) {
        new RoomManagementView();
    }


}
