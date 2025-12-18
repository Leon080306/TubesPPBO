package views.admin;

import controller.GuestController;
import controller.RoomController;
import controller.StaffController;
import models.enums.Department;
import models.enums.MembershipLevel;
import models.enums.RoomType;
import models.enums.UserType;
import utils.PasswordHashing;
import views.MainFrame;

import javax.swing.*;
import java.awt.*;

public class AddRoomView {
    private MainFrame frame;

    public AddRoomView() {
        frame = new MainFrame(true);
        renderAddRoomView();
    }

    private void renderAddRoomView() {
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        JLabel title = new JLabel("ADD ROOM");
        title.setFont(new Font("Poppins", Font.BOLD, 32));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(title);

        //Fields panel
        JPanel fieldsPanel = new JPanel();
        fieldsPanel.setLayout(new BoxLayout(fieldsPanel, BoxLayout.Y_AXIS));
        fieldsPanel.add(Box.createVerticalStrut(30));

        //Room number
        JPanel roomNumberPanel = new JPanel();
        roomNumberPanel.setLayout(new BoxLayout(roomNumberPanel, BoxLayout.Y_AXIS));
        roomNumberPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        roomNumberPanel.setMaximumSize(new Dimension(300, 40));

        JLabel roomNumberLabel = new JLabel("Room Number");
        roomNumberLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        roomNumberPanel.add(roomNumberLabel);

        JTextField roomNumberField = new JTextField();
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
        descriptionField.setAlignmentX(Component.LEFT_ALIGNMENT);
        descriptionPanel.add(descriptionField);

        fieldsPanel.add(descriptionPanel);

        //Room price
        JPanel pricePanel = new JPanel();
        fieldsPanel.add(Box.createVerticalStrut(10));
        pricePanel.setLayout(new BoxLayout(pricePanel, BoxLayout.Y_AXIS));
        pricePanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        pricePanel.setMaximumSize(new Dimension(300, 40));

        JLabel priceLabel = new JLabel("Room Price");
        priceLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        pricePanel.add(priceLabel);

        JTextField priceField = new JTextField();
        priceField.setAlignmentX(Component.LEFT_ALIGNMENT);
        pricePanel.add(priceField);

        fieldsPanel.add(pricePanel);

        mainPanel.add(fieldsPanel);

        //Cancel & Save Button
        JPanel buttonsPanel = new JPanel();

        JButton cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(e -> {
            frame.dispose();
            new RoomManagementView();
        });

        JButton addButton = new JButton("Add");
        addButton.addActionListener(e -> {
            JDialog dialog = new JDialog(frame);
            dialog.setSize(400, 200);
            dialog.setLocationRelativeTo(frame);
            dialog.setLayout(new BorderLayout());

            String message = "";
            if(RoomController.addRoom(roomNumberField.getText(), (RoomType) roomTypeField.getSelectedItem(), descriptionField.getText(), Double.parseDouble(priceField.getText()))) {
                message = "Room successfully added!";
            }
            else {
                message = "Error when creating room";
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
        buttonsPanel.add(addButton);

        mainPanel.add(buttonsPanel);

        //==========================
        frame.addComponent(mainPanel);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        new AddRoomView();
    }
}
