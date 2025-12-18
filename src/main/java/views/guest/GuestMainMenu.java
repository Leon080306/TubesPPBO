package views.guest;

import controller.BookingController;
import controller.GuestController;
import models.Guest;
import moduls.GlobalVariables;
import views.MainFrame;
import views.admin.AdminMainMenu;
import views.admin.UserManagementView;

import javax.swing.*;
import java.awt.*;

public class GuestMainMenu {
    MainFrame frame;

    public GuestMainMenu() {
        frame = new MainFrame(true);
        renderAdminMainMenu();
    }

    private void renderAdminMainMenu() {

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        JPanel titlePanel = new JPanel(new BorderLayout());
        JLabel title = new JLabel("Welcome "+ GlobalVariables.getUser().getNama(), SwingConstants.CENTER);
        title.setFont(new Font("Poppins", Font.BOLD, 32));
        titlePanel.add(title, BorderLayout.CENTER);
        titlePanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 60)); // Add this line
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
            BookingController.checkInBooking(GlobalVariables.getBooking().getBookingID());
        });
        //Check if user currently has booking or not
        if (GlobalVariables.getBooking() != null && GlobalVariables.getBooking().getBookingID() != null && !GlobalVariables.getBooking().getBookingID().isEmpty()){
            mainPanel.add(checkInButton);
            mainPanel.add(Box.createVerticalStrut(20));
        }
        //CheckOut

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
    public static void main(String[] args) {
        new AdminMainMenu();
    }
}
