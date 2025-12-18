package views.admin;

import controller.GuestController;
import controller.RoomController;
import controller.StaffController;
import controller.UserController;
import models.Guest;
import models.Room;
import models.Staff;
import models.Users;
import models.enums.Department;
import models.enums.MembershipLevel;
import models.enums.RoomType;
import models.enums.UserType;
import moduls.GlobalVariables;
import views.MainFrame;
import views.guest.GuestMainMenu;

import javax.swing.*;
import java.awt.*;

public class EditRoomView {
    private MainFrame frame;
    private Room roomData;

    public EditRoomView(Room roomData) {
        if(roomData == null) {
            JOptionPane.showMessageDialog(null, "Error retrieving room data", "Error", JOptionPane.ERROR_MESSAGE);
            new AdminMainMenu();
            return;
        }
        this.roomData = roomData;
        frame = new MainFrame(true);
        renderEditRoomView();
    }

    private void renderEditRoomView() {
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));

        //"EDIT PROFILE" title
        JLabel title = new JLabel("EDIT ROOM");
        title.setFont(new Font("Poppins", Font.BOLD, 32));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        centerPanel.add(title);

        //Fields panel
        JPanel fieldsPanel = new JPanel();
        fieldsPanel.setLayout(new BoxLayout(fieldsPanel, BoxLayout.Y_AXIS));
        fieldsPanel.add(Box.createVerticalStrut(50));

        //Room number
        JPanel roomNumberPanel = new JPanel();
        roomNumberPanel.setLayout(new BoxLayout(roomNumberPanel, BoxLayout.Y_AXIS));
        roomNumberPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        roomNumberPanel.setMaximumSize(new Dimension(300, 40));

        JLabel roomNumberLabel = new JLabel("Room Number");
        roomNumberLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        roomNumberPanel.add(roomNumberLabel);

        JTextField roomNumberField = new JTextField();
        roomNumberField.setText(String.valueOf(roomData.getRoomNumber()));
        roomNumberField.setAlignmentX(Component.LEFT_ALIGNMENT);
        roomNumberPanel.add(roomNumberField);

        fieldsPanel.add(roomNumberPanel);

        //Room Type
        JPanel roomTypePanel = new JPanel();
        fieldsPanel.add(Box.createVerticalStrut(10));
        roomTypePanel.setLayout(new BoxLayout(roomTypePanel, BoxLayout.Y_AXIS));
        roomTypePanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        roomTypePanel.setMaximumSize(new Dimension(300, 40));

        JLabel roomTypeLabel = new JLabel("Room Type");
        roomTypeLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        roomTypePanel.add(roomTypeLabel);

        JComboBox<RoomType> roomTypeField = new JComboBox<>(RoomType.values());
        roomTypeField.setAlignmentX(Component.LEFT_ALIGNMENT);
        roomTypeField.setSelectedItem(roomData.getRoomType());
        roomTypePanel.add(roomTypeField);

        fieldsPanel.add(roomTypePanel);

        //Room Description
        JPanel descriptionPanel = new JPanel();
        fieldsPanel.add(Box.createVerticalStrut(10));
        descriptionPanel.setLayout(new BoxLayout(descriptionPanel, BoxLayout.Y_AXIS));
        descriptionPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        descriptionPanel.setMaximumSize(new Dimension(300, 40));

        JLabel descriptionLabel = new JLabel("Room Description");
        descriptionLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        descriptionPanel.add(descriptionLabel);

        JTextField descriptionField = new JTextField();
        descriptionField.setText(String.valueOf(roomData.getRoomDescription()));
        descriptionField.setAlignmentX(Component.LEFT_ALIGNMENT);
        descriptionPanel.add(descriptionField);

        fieldsPanel.add(descriptionPanel);

        //Room price
        JPanel pricePanel = new JPanel();
        fieldsPanel.add(Box.createVerticalStrut(10));
        pricePanel.setLayout(new BoxLayout(pricePanel, BoxLayout.Y_AXIS));
        pricePanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        pricePanel.setMaximumSize(new Dimension(300, 60));

        JLabel priceLabel = new JLabel("Room Price");
        priceLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        pricePanel.add(priceLabel);

        JTextField priceField = new JTextField();
        if(RoomController.isOccupied(roomData.getRoomID())) {
            priceField.setEnabled(false);
        }
        priceField.setText(String.valueOf(roomData.getRoomPrice()));
        priceField.setAlignmentX(Component.LEFT_ALIGNMENT);
        pricePanel.add(priceField);

        if(RoomController.isOccupied(roomData.getRoomID())) {
            JLabel priceWarning = new JLabel("This room is occupied, you can't edit the price");
            priceWarning.setAlignmentX(Component.LEFT_ALIGNMENT);
            pricePanel.add(priceWarning);
        }

        fieldsPanel.add(pricePanel);

        centerPanel.add(fieldsPanel);

        //Cancel & Save Button
        JPanel buttonsPanel = new JPanel();

        JButton cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(e -> {
            frame.dispose();
            new RoomManagementView();
        });

        JButton editButton = new JButton("Edit");
        editButton.addActionListener(e -> {
            JDialog dialog = new JDialog(frame);
            dialog.setSize(400, 200);
            dialog.setLocationRelativeTo(frame);
            dialog.setLayout(new BorderLayout());

            String message = "";
            if(RoomController.updateRoom(roomNumberField.getText(), (RoomType) roomTypeField.getSelectedItem(), descriptionField.getText(), Double.parseDouble(priceField.getText()), roomData.getRoomID())) {
                message = "Room successfully updated!";
            }
            else {
                message = "Error when updating data";
            }

            JLabel messageLabel = new JLabel(message, SwingConstants.CENTER);
            dialog.add(messageLabel, BorderLayout.CENTER);

            JPanel dialogButtonsPanel = new JPanel();
            JButton closeButton = new JButton("Close");

            closeButton.addActionListener(action -> {
                dialog.dispose();
                frame.dispose();
                new RoomManagementView();
            });

            dialogButtonsPanel.add(closeButton);

            dialog.add(dialogButtonsPanel, BorderLayout.SOUTH);

            dialog.setVisible(true);
        });

        buttonsPanel.add(cancelButton);
        buttonsPanel.add(editButton);

        centerPanel.add(buttonsPanel);

        //==========================
        frame.addComponent(centerPanel);
        frame.setVisible(true);
    }
}