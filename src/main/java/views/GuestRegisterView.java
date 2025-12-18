package views;

import controller.GuestController;
import controller.UserController;
import models.enums.UserType;
import moduls.GlobalVariables;
import utils.PasswordHashing;
import views.admin.AdminMainMenu;
import views.guest.GuestMainMenu;
import views.staff.StaffMainMenu;

import javax.swing.*;
import java.awt.*;

public class GuestRegisterView {
    MainFrame frame;

    public GuestRegisterView() {
        renderSignUpView();
    }

    private void renderSignUpView() {
        frame = new MainFrame(false);

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

        JTextField fieldEmail = new JTextField(20);
        fieldEmail.setMaximumSize(new Dimension(300, 30));
        fieldEmail.setAlignmentX(Component.CENTER_ALIGNMENT);
        centerPanel.add(fieldEmail);

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

        JLabel labelNama = new JLabel("Nama Lengkap");
        labelNama.setAlignmentX(Component.CENTER_ALIGNMENT);
        centerPanel.add(labelNama);

        centerPanel.add(Box.createVerticalStrut(5));

        JTextField fieldNama = new JTextField(20);
        fieldNama.setMaximumSize(new Dimension(300, 30));
        fieldNama.setAlignmentX(Component.CENTER_ALIGNMENT);
        centerPanel.add(fieldNama);

        centerPanel.add(Box.createVerticalStrut(20));

        JLabel labelUmur = new JLabel("Umur");
        labelUmur.setAlignmentX(Component.CENTER_ALIGNMENT);
        centerPanel.add(labelUmur);

        centerPanel.add(Box.createVerticalStrut(5));

        JTextField fieldUmur = new JTextField(20);
        fieldUmur.setMaximumSize(new Dimension(300, 30));
        fieldUmur.setAlignmentX(Component.CENTER_ALIGNMENT);
        centerPanel.add(fieldUmur);

        centerPanel.add(Box.createVerticalStrut(20));

        JLabel labelPhone = new JLabel("No HP");
        labelPhone.setAlignmentX(Component.CENTER_ALIGNMENT);
        centerPanel.add(labelPhone);

        centerPanel.add(Box.createVerticalStrut(5));

        JTextField fieldPhone = new JTextField(20);
        fieldPhone.setMaximumSize(new Dimension(300, 30));
        fieldPhone.setAlignmentX(Component.CENTER_ALIGNMENT);
        centerPanel.add(fieldPhone);

        centerPanel.add(Box.createVerticalStrut(20));

        JLabel labelAddress = new JLabel("Alamaat");
        labelAddress.setAlignmentX(Component.CENTER_ALIGNMENT);
        centerPanel.add(labelAddress);

        centerPanel.add(Box.createVerticalStrut(5));

        JTextField fieldAddress = new JTextField(20);
        fieldAddress.setMaximumSize(new Dimension(300, 30));
        fieldAddress.setAlignmentX(Component.CENTER_ALIGNMENT);
        centerPanel.add(fieldAddress);

        centerPanel.add(Box.createVerticalStrut(20));

        JButton registerBtn = new JButton("Add New Account");
        registerBtn.setMaximumSize(new Dimension(300, 35));
        registerBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        centerPanel.add(registerBtn);

        registerBtn.addActionListener(e -> {
            String email = fieldEmail.getText();
            String rawPassword = new String(passwordField.getPassword());
            String password = PasswordHashing.hashPassword(rawPassword);
            String nama = fieldNama.getText();
            int umur = Integer.parseInt(fieldUmur.getText());
            String noHp = fieldPhone.getText();
            String alamat = fieldAddress.getText();

            GuestController.addNewGuest(password, nama, umur, email, noHp, alamat);
            //set global variable
            GlobalVariables.setUser(UserController.getUserDataByEmail(email));
            UserType userType = UserController.login(email, rawPassword);
            frame.dispose();
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
        frame.addComponent(mainPanel);
        frame.showFrame();
    }
}