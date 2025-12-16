package views;

import controllers.BookingControllers;
import exceptions.NoResultsFound;
import models.Booking;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class BookingView {
    MainFrame frame;
    BookingControllers bookingControllers;

    public BookingView() throws NoResultsFound {
        bookingControllers = new BookingControllers();
        List<Booking> bookings = bookingControllers.showAllBooking(); // <-- FETCH BOOKINGS
        displayAllBookings(bookings);
    }
    public void displayAllBookings(List<Booking> bookings) {
        frame = new MainFrame();
        JPanel mainPanel = new JPanel(new BorderLayout());


        JLabel title = new JLabel("Hotel Harapan Bangsa Booking");
        title.setFont(new Font("Poppins", Font.BOLD, 32));
        title.setHorizontalAlignment(SwingConstants.CENTER);
        title.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
        mainPanel.add(title, BorderLayout.NORTH);

        JPanel centralPanel = new JPanel();
        centralPanel.setLayout(new BoxLayout(centralPanel, BoxLayout.Y_AXIS));
        centralPanel.setBackground(Color.WHITE);
        centralPanel.setBackground(Color.WHITE);

//        JButton addBookingBtn = new JButton("Add Booking");
//        addBookingBtn.setFont(new Font("Poppins", Font.BOLD, 20));
//        addBookingBtn.addActionListener(e -> openAddBookingForm());
//        mainPanel.add(addBookingBtn, BorderLayout.SOUTH);


        for (Booking b : bookings) {
            JPanel bookingPanel = new JPanel();
            bookingPanel.setLayout(new BoxLayout(bookingPanel, BoxLayout.Y_AXIS));
            bookingPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

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

//    //AI
//    private void openAddBookingForm() {
//        JDialog dialog = new JDialog(frame, "Add Booking", true);
//        dialog.setSize(400, 400);
//        dialog.setLayout(new GridLayout(0, 2, 10, 10));
//
//        JTextField checkInField = new JTextField();
//        JTextField checkOutField = new JTextField();
//        JTextField guestField = new JTextField();
//        JTextField roomIdField = new JTextField();
//
//        dialog.add(new JLabel("Check-In (YYYY-MM-DD):"));
//        dialog.add(checkInField);
//
//        dialog.add(new JLabel("Check-Out (YYYY-MM-DD):"));
//        dialog.add(checkOutField);
//
//        dialog.add(new JLabel("Guests:"));
//        dialog.add(guestField);
//
//        dialog.add(new JLabel("Room ID:"));
//        dialog.add(roomIdField);
//
//        JButton saveBtn = new JButton("Save Booking");
//        saveBtn.addActionListener(e -> {
//            try {
//                bookingControllers.addBooking(
//                    "c3d7e5f6-3b6a-4e1f-8b3c-33333333cccc",
//                    Integer.parseInt(roomIdField.getText()),
//                    checkInField.getText(),
//                    checkOutField.getText(),
//                    Integer.parseInt(guestField.getText())
//                );
//
//                dialog.dispose();// RELOAD UI
//                refreshBookings();
//            } catch (Exception ex) {
//                JOptionPane.showMessageDialog(dialog, "Error: " + ex.getMessage());
//            }
//        });
//
//        dialog.add(new JLabel());  // empty spacing
//        dialog.add(saveBtn);
//
//        dialog.setLocationRelativeTo(frame);
//        dialog.setVisible(true);
//    }
//
//    private void refreshBookings() {
//        try {
//            List<Booking> updated = bookingControllers.showAllBooking();
//            frame.dispose();
//            displayAllBookings(updated);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }


}
