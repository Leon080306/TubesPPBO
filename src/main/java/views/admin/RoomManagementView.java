package views.admin;

import controller.RoomController;
import controller.UserController;
import models.Room;
import models.Users;
import models.enums.RoomType;
import views.EditProfileMenu;
import views.MainFrame;

import javax.swing.*;
import java.awt.*;

public class RoomManagementView {
    private MainFrame frame;

    public RoomManagementView() {
        frame = new MainFrame(true);
        renderRoomManagementMenu();
    }

    private void renderRoomManagementMenu() {
        frame = new MainFrame(true);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        JPanel titlePanel = new JPanel(new BorderLayout());
        JLabel title = new JLabel("ROOM MANAGEMENT", SwingConstants.CENTER);
        title.setFont(new Font("Poppins", Font.BOLD, 32));
        titlePanel.add(title, BorderLayout.CENTER);
        mainPanel.add(titlePanel);

        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new BoxLayout(buttonsPanel, BoxLayout.X_AXIS));
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

        //add new room button
        JButton addRoomButton = new JButton("Add New Room");
        addRoomButton.addActionListener(e -> {
            frame.dispose();
            new AddRoomView();
        });
        addRoomButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        buttonsPanel.add(addRoomButton);
        buttonsPanel.add(Box.createRigidArea(new Dimension(20, 0)));

        mainPanel.add(buttonsPanel);

        mainPanel.add(Box.createVerticalStrut(20));

        JPanel roomListPanel = new JPanel();
        roomListPanel.setLayout(new BoxLayout(roomListPanel, BoxLayout.Y_AXIS));
        roomListPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        for (Room room : RoomController.getAllRooms()) {
            JPanel roomPanel = new JPanel();
            roomPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 20, 0));
            roomPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
            roomPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));

            JLabel roomNumber = new JLabel(room.getRoomNumber());
            roomNumber.setPreferredSize(new Dimension(50, 25));
            roomPanel.add(roomNumber);

            JLabel roomType = new JLabel(room.getRoomType().name());
            roomType.setPreferredSize(new Dimension(100, 25));
            roomPanel.add(roomType);

            JLabel roomDescription = new JLabel(room.getRoomDescription());
            roomDescription.setPreferredSize(new Dimension(150, 25));
            roomPanel.add(roomDescription);

            JLabel roomPrice = new JLabel(String.valueOf(room.getRoomPrice()));
            roomPrice.setPreferredSize(new Dimension(75, 25));
            roomPanel.add(roomPrice);

            String status = "";
            if(RoomController.isOccupied(room.getRoomID())) {
                status = "YES";
            }
            else {
                status = "NO";
            }
            JLabel roomStatus = new JLabel(status);
            roomStatus.setPreferredSize(new Dimension(25, 25));
            roomPanel.add(roomStatus);

            JButton editButton = new JButton("Edit");
            editButton.setPreferredSize(new Dimension(100, 25));
            editButton.addActionListener(e -> {
                frame.dispose();
                new EditRoomView(room);
            });
            roomPanel.add(editButton);

            JButton deleteButton = new JButton("Delete");
            deleteButton.addActionListener(e -> {
                if(RoomController.isOccupied(room.getRoomID())) {
                    JDialog dialog = new JDialog(frame, "Warning", true);
                    dialog.setSize(400, 200);
                    dialog.setLocationRelativeTo(frame);
                    dialog.setLayout(new BorderLayout());
                    JLabel messageLabel = new JLabel("Room number " + room.getRoomNumber() + " is occupied", SwingConstants.CENTER);
                    dialog.add(messageLabel, BorderLayout.CENTER);

                    JPanel dialogButtonsPanel = new JPanel();
                    JButton closeButton = new JButton("Close");

                    closeButton.addActionListener(action -> {
                        dialog.dispose();
                    });

                    dialogButtonsPanel.add(closeButton);

                    dialog.add(dialogButtonsPanel, BorderLayout.SOUTH);

                    dialog.setVisible(true);
                    return;
                }

                JDialog dialog = new JDialog(frame, "Warning", true);
                dialog.setSize(400, 200);
                dialog.setLocationRelativeTo(frame);
                dialog.setLayout(new BorderLayout());
                JLabel messageLabel = new JLabel("Are you sure you want to delete room number " + room.getRoomNumber() + "?", SwingConstants.CENTER);
                dialog.add(messageLabel, BorderLayout.CENTER);

                JPanel dialogButtonsPanel = new JPanel();
                JButton confirmButton = new JButton("Confirm");
                JButton cancelButton = new JButton("Cancel");

                confirmButton.addActionListener(action -> {
                    RoomController.deleteRoom(room.getRoomID());
                    dialog.dispose();
                    frame.dispose();
                    new RoomManagementView();
                });

                cancelButton.addActionListener(action -> {
                    dialog.dispose();
                });

                dialogButtonsPanel.add(confirmButton);
                dialogButtonsPanel.add(cancelButton);

                dialog.add(dialogButtonsPanel, BorderLayout.SOUTH);

                dialog.setVisible(true);
            });
            deleteButton.setPreferredSize(new Dimension(100, 25));
            roomPanel.add(deleteButton);

            roomListPanel.add(roomPanel);
            roomListPanel.add(Box.createVerticalStrut(10));
        }

        mainPanel.add(roomListPanel);

        frame.addComponent(mainPanel);
        frame.showFrame();
    }

    public static void main(String[] args) {
        new RoomManagementView();
    }
}
