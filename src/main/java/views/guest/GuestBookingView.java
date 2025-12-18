package views.guest;

import controller.BookingController;
import models.Booking;
import models.Guest;
import moduls.GlobalVariables;
import views.MainFrame;
import views.admin.AdminMainMenu;
import views.admin.RoomManagementView;

import javax.swing.*;
import java.awt.*;

import static moduls.GlobalVariables.getUser;

public class GuestBookingView {
    private MainFrame frame;
    public GuestBookingView(){
        frame = new MainFrame(true);
        renderHistoryMenu();
    }

    private JLabel createHeaderLabel(String text, int width) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Poppins", Font.BOLD, 14)); // Makes it look punchy!
        label.setPreferredSize(new Dimension(width, 25)); // Keeps everything aligned!
        return label;
    }

    private void renderHistoryMenu(){
        Guest guest = (Guest) GlobalVariables.getUser();
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        JPanel titlePanel = new JPanel(new BorderLayout());
        JLabel title = new JLabel("Your Booking History", SwingConstants.CENTER);
        title.setFont(new Font("Poppins", Font.BOLD, 32));
        titlePanel.add(title, BorderLayout.CENTER);
        mainPanel.add(titlePanel);
        mainPanel.add(Box.createVerticalStrut(10));

        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new BoxLayout(buttonsPanel, BoxLayout.X_AXIS));
        buttonsPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        //Back Button
        JButton backButton = new JButton("Back");
        backButton.addActionListener(e -> {
            frame.dispose();
            new GuestMainMenu();
        });
        backButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        buttonsPanel.add(backButton);
        buttonsPanel.add(Box.createRigidArea(new Dimension(20, 0)));

        mainPanel.add(buttonsPanel);

        mainPanel.add(Box.createVerticalStrut(20));

        JPanel bookingHistoryListPanel = new JPanel();
        bookingHistoryListPanel.setLayout(new BoxLayout(bookingHistoryListPanel, BoxLayout.Y_AXIS));
        bookingHistoryListPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        //List Container
        JPanel containerPanel = new JPanel();
        containerPanel.setLayout(new BoxLayout(containerPanel, BoxLayout.Y_AXIS));

        //Headers
        JPanel headerPanel = new JPanel();
        headerPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 20, 0));
        headerPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
        headerPanel.setBackground(new Color(230, 230, 230));

        headerPanel.add(createHeaderLabel("Room", 50));
        headerPanel.add(createHeaderLabel("Description", 150));
        headerPanel.add(createHeaderLabel("Check In", 100));
        headerPanel.add(createHeaderLabel("Check Out", 100));

        containerPanel.add(headerPanel);
        containerPanel.add(Box.createVerticalStrut(10));

        //loop booking
        for(Booking b : BookingController.showAllBooking(guest.getGuestID())){
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

            //add to container
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
        new GuestBookingView();
    }
}
