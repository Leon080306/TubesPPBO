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
    MainFrame frame;
    UserType userType;

    public LoginView() {
        userController = new UserController();
        renderLoginView();
    }

    private void renderLoginView() {
        frame = new MainFrame();

        JPanel mainPanel = new JPanel(new BorderLayout());

        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));

        centerPanel.add(Box.createVerticalStrut(100));

        JLabel title = new JLabel("Hotel Harapan Bangsa");
        title.setFont(new Font("Poppins", Font.BOLD, 32));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        centerPanel.add(title);

        JLabel subTitle = new JLabel("Login");
        subTitle.setFont(new Font("Poppins", Font.BOLD, 32));
        subTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        centerPanel.add(subTitle);

        centerPanel.add(Box.createVerticalStrut(40));

        JLabel labelEmail = new JLabel("Email");
        labelEmail.setAlignmentX(Component.CENTER_ALIGNMENT);
        centerPanel.add(labelEmail);

        centerPanel.add(Box.createVerticalStrut(5));

        JTextField emailField = new JTextField(20);
        emailField.setMaximumSize(new Dimension(300, 30));
        emailField.setAlignmentX(Component.CENTER_ALIGNMENT);
        centerPanel.add(emailField);

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

        JButton loginBtn = new JButton("Login");
        loginBtn.setMaximumSize(new Dimension(300, 35));
        loginBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        centerPanel.add(loginBtn);

        loginBtn.addActionListener(e -> {
            String email = emailField.getText();
            String password = new String(passwordField.getPassword());
            userType = UserController.login(email, password);
            if(userType == null) {
                showDialog("Login Failed", "Invalid email or password");
            }
            else {
                showDialog("Login Successful", "Login Successful!");
            }
        });

        centerPanel.add(Box.createVerticalStrut(30));

        JButton registerBtn = new JButton("Register as New Guest");
        registerBtn.setMaximumSize(new Dimension(300, 35));
        registerBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        centerPanel.add(registerBtn);

        registerBtn.addActionListener(e -> {
            frame.dispose();
            new GuestRegisterView();
        });

        mainPanel.add(centerPanel, BorderLayout.CENTER);
        frame.addComponent(mainPanel);
        frame.showFrame();
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
            if (userType != null) {
                frame.dispose();
                navigateMenu(userType);
            }
        });

        panel.add(okButton);

        dialog.add(panel);
        dialog.setLocationRelativeTo(frame);
        dialog.setVisible(true);
    }

    private void navigateMenu(UserType userType) {
        switch (userType) {
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
}