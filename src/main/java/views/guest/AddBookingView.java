package views.guest;

import com.sun.tools.javac.Main;
import controller.BookingController;
import controller.RoomController;
import models.Booking;
import models.Guest;
import models.Room;
import moduls.GlobalVariables;
import views.LoginView;
import views.MainFrame;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class AddBookingView {
    private MainFrame frame;


    public AddBookingView(){
        frame = new MainFrame(true);
        renderBookingReservation();
    }

    private void renderBookingReservation(){
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel,BoxLayout.Y_AXIS));

        JPanel titlePanel = new JPanel(new BorderLayout());
        JLabel title = new JLabel("Make a Reservation", SwingConstants.CENTER);
        title.setFont(new Font("Poppins", Font.BOLD, 32));
        titlePanel.add(title, BorderLayout.CENTER);
        titlePanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 80));
        mainPanel.add(titlePanel);

        //form
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new BoxLayout(formPanel,BoxLayout.Y_AXIS));
        formPanel.add(Box.createVerticalStrut(30));

            //Room List
        List<Room> roomList = RoomController.getAllRooms();
        JComboBox<String> rooms = new JComboBox<>();
        for(Room r : roomList){
            rooms.addItem(r.getRoomNumber());
        }
        JPanel roomsPanel = new JPanel();
        formPanel.add(Box.createVerticalStrut(10));
        roomsPanel.setLayout(new BoxLayout(roomsPanel,BoxLayout.Y_AXIS));
        roomsPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        roomsPanel.setMaximumSize(new Dimension(300,40));

        JLabel roomsLabel = new JLabel("Rooms");
        roomsLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        roomsPanel.add(roomsLabel);

        rooms.setAlignmentX(Component.LEFT_ALIGNMENT);
        roomsPanel.add(rooms);
        formPanel.add(roomsPanel);

            //Check In Date
        JPanel checkInPanel = new JPanel();
        formPanel.add(Box.createVerticalStrut(10));
        checkInPanel.setLayout(new BoxLayout(checkInPanel,BoxLayout.Y_AXIS));
        checkInPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        checkInPanel.setMaximumSize(new Dimension(300, 40));

        JLabel checkInLabel = new JLabel("Check In Date");
        checkInLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        checkInPanel.add(checkInLabel);

        JTextField checkInField = new JTextField();
        checkInField.setAlignmentX(Component.LEFT_ALIGNMENT);
        checkInField.setText("YYYY-MM-DD HH-MM-SS");
        checkInPanel.add(checkInField);

        formPanel.add(checkInPanel);

            //Check Out Date
        JPanel checkOutPanel = new JPanel();
        formPanel.add(Box.createVerticalStrut(10));
        checkOutPanel.setLayout(new BoxLayout(checkOutPanel,BoxLayout.Y_AXIS));
        checkOutPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        checkOutPanel.setMaximumSize(new Dimension(300, 40));

        JLabel checkOutLabel = new JLabel("Check Out Date");
        checkOutLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        checkOutPanel.add(checkOutLabel);

        JTextField checkOutField = new JTextField();
        checkOutField.setAlignmentX(Component.LEFT_ALIGNMENT);
        checkOutField.setText("YYYY-MM-DD HH-MM-SS");
        checkOutPanel.add(checkOutField);

        formPanel.add(checkOutPanel);

            //Total Guests
        JPanel guestTotalPanel = new JPanel();
        formPanel.add(Box.createVerticalStrut(10));
        guestTotalPanel.setLayout(new BoxLayout(guestTotalPanel,BoxLayout.Y_AXIS));
        guestTotalPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        guestTotalPanel.setMaximumSize(new Dimension(300, 40));

        JLabel guestTotalLabel = new JLabel("Total Guests");
        guestTotalLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        guestTotalPanel.add(guestTotalLabel);

        JTextField guestTotalField = new JTextField();
        guestTotalField.setAlignmentX(Component.LEFT_ALIGNMENT);
        guestTotalPanel.add(guestTotalField);

        formPanel.add(guestTotalPanel);

        JPanel buttonsPanel = new JPanel();

        mainPanel.add(formPanel);

        //Cancel
        JButton cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(e -> {
            frame.dispose();
            new GuestMainMenu();
        });

        //Make Reservation
        JButton reserveButton = new JButton("Reserve");
        reserveButton.addActionListener(e -> {
            Guest guest = (Guest) GlobalVariables.getUser();
            Object item = rooms.getSelectedItem();
            if (item == null) {
                JOptionPane.showMessageDialog(frame, "No room selected! Is the room list empty?");
                return;
            }
            String selectedRoom = item.toString();
            if (!RoomController.isOccupied(selectedRoom)){
                Booking booking = BookingController.addBooking(guest.getGuestID(),(String) selectedRoom,checkInField.getText(),checkOutField.getText(),Integer.parseInt(guestTotalField.getText()) );
                GlobalVariables.setBooking(booking);
            } else{
                JOptionPane.showMessageDialog(frame, "Room is Occupied",
                        "Occupied", JOptionPane.INFORMATION_MESSAGE);
            }
            frame.dispose();
            new GuestMainMenu();
        });
        buttonsPanel.add(cancelButton);
        buttonsPanel.add(reserveButton);

        mainPanel.add(buttonsPanel);



        //show
        frame.addComponent(mainPanel);
        frame.showFrame();
    }

    public static void main(String[] args) {
        new AddBookingView();
    }

}
