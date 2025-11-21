package views;

import controller.UserController;
import models.enums.UserType;
import views.admin.AdminMainMenu;
import views.guest.GuestMainMenu;
import views.staff.StaffMainMenu;

import javax.swing.*;
import java.awt.*;

public class LoginView {
    UserController userController;
    JFrame frame;
    UserType userType;

    public LoginView() {
        userController = new UserController();
        renderLoginView();
    }

    private void renderLoginView() {
        frame = new JFrame("Hotel Harapan Bangsa Management System");
        int width = 800;
        int height = 600;

        frame.setSize(width, height);
        frame.setLocationRelativeTo(null);
        frame.setAlwaysOnTop(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel mainPanel = new JPanel(new BorderLayout());

        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));

        centerPanel.add(Box.createVerticalStrut(100));

        JLabel title = new JLabel("Hotel Harapan Bangsa");
        title.setFont(new Font("Poppins", Font.BOLD, 32));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        centerPanel.add(title);

        centerPanel.add(Box.createVerticalStrut(40));

        JLabel labelEmail = new JLabel("Email");
        labelEmail.setAlignmentX(Component.CENTER_ALIGNMENT);
        centerPanel.add(labelEmail);

        centerPanel.add(Box.createVerticalStrut(5));

        JTextField textField = new JTextField(20);
        textField.setMaximumSize(new Dimension(300, 30));
        textField.setAlignmentX(Component.CENTER_ALIGNMENT);
        centerPanel.add(textField);

        centerPanel.add(Box.createVerticalStrut(20));

        JLabel labelPassword = new JLabel("Password");
        labelPassword.setAlignmentX(Component.CENTER_ALIGNMENT);
        centerPanel.add(labelPassword);

        centerPanel.add(Box.createVerticalStrut(5));

        JPasswordField passwordField = new JPasswordField(20);
        passwordField.setMaximumSize(new Dimension(300, 30));
        passwordField.setAlignmentX(Component.CENTER_ALIGNMENT);
        centerPanel.add(passwordField);

        centerPanel.add(Box.createVerticalStrut(30));

        JButton btn = new JButton("Login");
        btn.setMaximumSize(new Dimension(300, 35));
        btn.setAlignmentX(Component.CENTER_ALIGNMENT);
        centerPanel.add(btn);

        btn.addActionListener(e -> {
            String email = textField.getText();
            String password = new String(passwordField.getPassword());
            userType = userController.login(email, password);
            switch(userType) {
                case null:
                    showDialog("Error", "Invalid email or password");
                    break;
                case ADMIN:
                    showDialog("Success", "Login successful");
                    break;
                case GUEST:
                    showDialog("Success", "Login successful");
                    break;
                case STAFF:
                    showDialog("Success", "Login successful");
                    break;
            }
        });

        mainPanel.add(centerPanel, BorderLayout.CENTER);
        frame.add(mainPanel);
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

            // Only navigate if login was successful (userType is not null)
            if (userType != null) {
                frame.dispose(); // Close login window

                switch(userType) {
                    case ADMIN:
                        new AdminMainMenu();
                        break;
                    case GUEST:
                    new GuestMainMenu();
                        break;
                    case STAFF:
                    new StaffMainMenu();
                        break;
                }
            }
        });
        panel.add(okButton);

        dialog.add(panel);
        dialog.setLocationRelativeTo(frame);
        dialog.setVisible(true);
    }
}