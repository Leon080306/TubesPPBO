package views.guest;

import controller.BookingController;
import controller.GuestController;
import models.Guest;
import models.enums.BookingStatus;
import moduls.GlobalVariables;
import views.MainFrame;
import views.admin.AdminMainMenu;
import views.admin.UserManagementView;
import views.shift.ShiftView;
import views.task.TaskView;

import javax.swing.*;
import java.awt.*;

public class GuestMainMenu {
    private static MainFrame frame;

    public GuestMainMenu() {
        frame = new MainFrame(true);
        renderAdminMainMenu();
    }

    private void renderAdminMainMenu() {
        String currentBooking = "!";
        if (GlobalVariables.getBooking() != null && GlobalVariables.getBooking().getBookingStatus() != null && GlobalVariables.getBooking().getBookingStatus().name() != "CHECKED_OUT"){
            currentBooking += " You Are Currently : "+GlobalVariables.getBooking().getBookingStatus();
        }

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        JPanel titlePanel = new JPanel(new BorderLayout());
        JLabel title = new JLabel("Welcome "+ GlobalVariables.getUser().getNama() + currentBooking, SwingConstants.CENTER);
        title.setFont(new Font("Poppins", Font.BOLD, 32));
        titlePanel.add(title, BorderLayout.CENTER);
        titlePanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 60));
        mainPanel.add(titlePanel);
        mainPanel.add(Box.createVerticalStrut(50));

        //Make Reservation
        JButton reservationButton = new JButton("Make Reservation");
        reservationButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        reservationButton.addActionListener(e -> {
            frame.dispose();
            new AddBookingView();
        });
        mainPanel.add(reservationButton);
        mainPanel.add(Box.createVerticalStrut(20));

        //CheckIn
        JButton checkInButton = new JButton("Check In");
        checkInButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        checkInButton.addActionListener(e -> {
            boolean check = BookingController.checkInBooking(GlobalVariables.getBooking().getBookingID());
            if (check){
                GlobalVariables.getBooking().setBookingStatus(BookingStatus.CHECKED_IN);
                showDialog("Checked In","Check In Successful!");
                frame.dispose();
                new GuestMainMenu();
            }
        });
        //Check if user currently has booking or not
        if (GlobalVariables.getBooking() != null &&
                GlobalVariables.getBooking().getBookingStatus() == BookingStatus.BOOKED){
            mainPanel.add(checkInButton);
            mainPanel.add(Box.createVerticalStrut(20));
        }

        //CheckOut
        JButton checkOutButton = new JButton("Check Out");
        checkOutButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        checkOutButton.addActionListener(e -> {
            if (GlobalVariables.getBooking() != null) {
                frame.dispose();
                new CheckOutPaymentTypeView(GlobalVariables.getBooking());
            } else {
                showDialog("Over the Date","check out is past date .");
            }
        });
        if (GlobalVariables.getBooking() != null && GlobalVariables.getBooking().getBookingStatus() == BookingStatus.CHECKED_IN) {
            mainPanel.add(checkOutButton);
            mainPanel.add(Box.createVerticalStrut(20));
        }
        //Cancel

        //Extra Services
        JButton extraServicesButton = new JButton("Extra Services");
        extraServicesButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        extraServicesButton.addActionListener(e -> {
            frame.dispose();
            new ExtraServicesView();
        });
        //Check if user currently has booking or not
        if (GlobalVariables.getBooking() != null && GlobalVariables.getBooking().getBookingID() != null && !GlobalVariables.getBooking().getBookingID().isEmpty()){
            mainPanel.add(extraServicesButton);
            mainPanel.add(Box.createVerticalStrut(20));
        }

        //Booking History
        JButton bookingHistoryButton = new JButton("Booking History");
        bookingHistoryButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        bookingHistoryButton.addActionListener(e -> {
            frame.dispose();
            new GuestBookingView();
        });
        mainPanel.add(bookingHistoryButton);
        mainPanel.add(Box.createVerticalStrut(20));

        frame.addComponent(mainPanel);
        frame.setVisible(true);
    }

    private void showDialog(String title, String message) {
        JDialog dialog = new JDialog(frame, title, true);
        dialog.setSize(300, 150);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        panel.add(Box.createVerticalStrut(20));

        JLabel label = new JLabel(message);
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(label);

        panel.add(Box.createVerticalStrut(20));

        JButton okButton = new JButton("OK");
        okButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        okButton.addActionListener(e -> {
            dialog.dispose();
        });

        panel.add(okButton);

        dialog.add(panel);
        dialog.setLocationRelativeTo(frame);
        dialog.setVisible(true);
    }

    public static void main(String[] args) {
        new AdminMainMenu();
//        JPanel mainPanel = new JPanel();
//        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
//        JPanel titlePanel = new JPanel(new BorderLayout());
//        JLabel title = new JLabel("GUEST MENU", SwingConstants.CENTER);
//        title.setFont(new Font("Poppins", Font.BOLD, 32));
//        titlePanel.add(title, BorderLayout.CENTER);
//        titlePanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 60)); // Add this line
//        mainPanel.add(titlePanel);
//        mainPanel.add(Box.createVerticalStrut(50));
//
//        JButton taskManagementButton = new JButton("Task Management");
//        taskManagementButton.setAlignmentX(Component.CENTER_ALIGNMENT);
//        taskManagementButton.addActionListener(e -> {
//            frame.dispose();
//            new TaskView(false);
//        });
//        mainPanel.add(taskManagementButton);
//        mainPanel.add(Box.createVerticalStrut(20));
//
//        JButton shiftManagementButton = new JButton("Shift Management");
//        shiftManagementButton.setAlignmentX(Component.CENTER_ALIGNMENT);
//        shiftManagementButton.addActionListener(e -> {
//            frame.dispose();
//            new ShiftView(false);
//        });
//        mainPanel.add(shiftManagementButton);
//        mainPanel.add(Box.createVerticalStrut(20));
//
//        //user is currently in a booking
//        if(GlobalVariables.getBooking() != null) {
//            JButton extraServiceButton = new JButton("Order Extra Service");
//            extraServiceButton.setAlignmentX(Component.CENTER_ALIGNMENT);
//            extraServiceButton.addActionListener(e -> {
//                frame.dispose();
//                new ExtraServicesView();
//            });
//            mainPanel.add(extraServiceButton);
//            mainPanel.add(Box.createVerticalStrut(20));
//        }
//
//        frame.addComponent(mainPanel);
//        frame.setVisible(true);
    }
}
