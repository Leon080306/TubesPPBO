package views.admin;

import controller.UserController;
import models.Users;
import models.enums.UserType;
import views.EditProfileMenu;
import views.MainFrame;

import javax.swing.*;
import java.awt.*;

public class UserManagementView {
    MainFrame frame;

    public UserManagementView() {
        renderUserManagementMenu();
    }

    private void renderUserManagementMenu() {
        frame = new MainFrame(true);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        JPanel titlePanel = new JPanel(new BorderLayout());
        JLabel title = new JLabel("USER MANAGEMENT", SwingConstants.CENTER);
        title.setFont(new Font("Poppins", Font.BOLD, 32));
        titlePanel.add(title, BorderLayout.CENTER);
        mainPanel.add(titlePanel);
        mainPanel.add(Box.createVerticalStrut(10));

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

        //add new guest button
        JButton addGuestButton = new JButton("Add New Guest");
        addGuestButton.addActionListener(e -> {
            frame.dispose();
            new AddUserView(false);
        });
        addGuestButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        buttonsPanel.add(addGuestButton);
        buttonsPanel.add(Box.createRigidArea(new Dimension(20, 0)));

        //add new staff button
        JButton addStaffButton = new JButton("Add New Staff");
        addStaffButton.addActionListener(e -> {
            frame.dispose();
            new AddUserView(true);
        });
        addStaffButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        buttonsPanel.add(addStaffButton);

        mainPanel.add(buttonsPanel);

        mainPanel.add(Box.createVerticalStrut(20));

        JPanel usersListPanel = new JPanel();
        usersListPanel.setLayout(new BoxLayout(usersListPanel, BoxLayout.Y_AXIS));
        usersListPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        for (Users user : UserController.getAllUsers()) {
            JPanel userPanel = new JPanel();
            userPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 20, 0));
            userPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
            userPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));

            JLabel userName = new JLabel(user.getNama());
            userName.setPreferredSize(new Dimension(150, 25));
            userPanel.add(userName);

            JLabel userEmail = new JLabel(user.getEmail());
            userEmail.setPreferredSize(new Dimension(200, 25));
            userPanel.add(userEmail);

            JLabel userType = new JLabel(user.getUserType().toString());
            userType.setPreferredSize(new Dimension(100, 25));
            userPanel.add(userType);

            JButton editButton = new JButton("Edit");
            editButton.setPreferredSize(new Dimension(100, 25));
            editButton.addActionListener(e -> {
                frame.dispose();
                new EditProfileMenu(user);
            });
            userPanel.add(editButton);

            JButton deleteButton = new JButton("Delete");
            deleteButton.addActionListener(e -> {
                JDialog dialog = new JDialog(frame, "Warning", true);
                dialog.setSize(400, 200);
                dialog.setLocationRelativeTo(frame);
                dialog.setLayout(new BorderLayout());
                JLabel messageLabel = new JLabel("Are you sure you want to delete " + user.getNama() + "?", SwingConstants.CENTER);
                dialog.add(messageLabel, BorderLayout.CENTER);

                JPanel dialogButtonsPanel = new JPanel();
                JButton confirmButton = new JButton("Confirm");
                JButton cancelButton = new JButton("Cancel");

                confirmButton.addActionListener(action -> {
                    UserController.deleteUser(user.getUserID());
                    dialog.dispose();
                    frame.dispose();
                    new UserManagementView();
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
            userPanel.add(deleteButton);

            usersListPanel.add(userPanel);
            usersListPanel.add(Box.createVerticalStrut(10));
        }

        mainPanel.add(usersListPanel);

        frame.addComponent(mainPanel);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        new UserManagementView();
    }
}