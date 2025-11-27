package views;

import javax.swing.*;
import java.awt.*;

public class GuestRegisterView {
    MainFrame frame;

    public GuestRegisterView() {
        renderSignUpView();
    }

    private void renderSignUpView() {
        frame = new MainFrame();

        JPanel mainPanel = new JPanel(new BorderLayout());

        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));

        centerPanel.add(Box.createVerticalStrut(100));

        JLabel title = new JLabel("Hotel Harapan Bangsa");
        title.setFont(new Font("Poppins", Font.BOLD, 32));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        centerPanel.add(title);

        JLabel subTitle = new JLabel("Register as New Guest");
        subTitle.setFont(new Font("Poppins", Font.BOLD, 32));
        subTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        centerPanel.add(subTitle);

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

        JButton registerBtn = new JButton("Add New Account");
        registerBtn.setMaximumSize(new Dimension(300, 35));
        registerBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        centerPanel.add(registerBtn);

        registerBtn.addActionListener(e -> {
            String email = textField.getText();
            String password = new String(passwordField.getPassword());
        });

        centerPanel.add(Box.createVerticalStrut(30));

        JButton loginBtn = new JButton("Already Have an Account?");
        loginBtn.setMaximumSize(new Dimension(300, 35));
        loginBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        centerPanel.add(loginBtn);

        loginBtn.addActionListener(e -> {
            frame.dispose();
            new LoginView();
        });

        mainPanel.add(centerPanel, BorderLayout.CENTER);
        frame.add(mainPanel);
        frame.showFrame();
    }
}
