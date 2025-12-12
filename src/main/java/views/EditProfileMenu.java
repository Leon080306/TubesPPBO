package views;

import models.Users;
import views.guest.GuestMainMenu;

import javax.swing.*;
import java.awt.*;

public class EditProfileMenu {
    private MainFrame frame;
    private Users userData;

    public EditProfileMenu() {
        frame = new MainFrame(true);
        renderEditProfileMenu();
    }

    private void renderEditProfileMenu() {
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));

        //"EDIT PROFILE" title
        JLabel title = new JLabel("EDIT PROFILE");
        title.setFont(new Font("Poppins", Font.BOLD, 32));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        centerPanel.add(title);

        //Fields panel
        JPanel fieldsPanel = new JPanel();
        fieldsPanel.setLayout(new BoxLayout(fieldsPanel, BoxLayout.Y_AXIS));
        fieldsPanel.add(Box.createVerticalStrut(50));

        //Email
        JPanel emailPanel = new JPanel();
        emailPanel.setLayout(new BoxLayout(emailPanel, BoxLayout.Y_AXIS));
        emailPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        emailPanel.setMaximumSize(new Dimension(300, 40));

        JLabel emailLabel = new JLabel("Email");
        emailPanel.add(emailLabel);

        JTextField emailField = new JTextField();
        emailPanel.add(emailField);

        fieldsPanel.add(emailPanel);

        //Password
        JPanel passwordPanel = new JPanel();
        fieldsPanel.add(Box.createVerticalStrut(10));
        passwordPanel.setLayout(new BoxLayout(passwordPanel, BoxLayout.Y_AXIS));
        passwordPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        passwordPanel.setMaximumSize(new Dimension(300, 40));

        JLabel passwordLabel = new JLabel("Password");
        passwordPanel.add(passwordLabel);

        JPasswordField passwordField = new JPasswordField();
        passwordPanel.add(passwordField);

        fieldsPanel.add(passwordPanel);

        //Nama
        JPanel namePanel = new JPanel();
        fieldsPanel.add(Box.createVerticalStrut(10));
        namePanel.setLayout(new BoxLayout(namePanel, BoxLayout.Y_AXIS));
        namePanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        namePanel.setMaximumSize(new Dimension(300, 40));

        JLabel nameLabel = new JLabel("Full Name");
        namePanel.add(nameLabel);

        JTextField nameField = new JTextField();
        namePanel.add(nameField);

        fieldsPanel.add(namePanel);

        //Phone Number
        JPanel phonePanel = new JPanel();
        fieldsPanel.add(Box.createVerticalStrut(10));
        phonePanel.setLayout(new BoxLayout(phonePanel, BoxLayout.Y_AXIS));
        phonePanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        phonePanel.setMaximumSize(new Dimension(300, 40));

        JLabel phoneLabel = new JLabel("Phone Number");
        phonePanel.add(phoneLabel);

        JTextField phoneField = new JTextField();
        phonePanel.add(phoneField);

        fieldsPanel.add(phonePanel);

        //Umur
        JPanel agePanel = new JPanel();
        fieldsPanel.add(Box.createVerticalStrut(10));
        agePanel.setLayout(new BoxLayout(agePanel, BoxLayout.Y_AXIS));
        agePanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        agePanel.setMaximumSize(new Dimension(300, 40));

        JLabel ageLabel = new JLabel("Age");
        agePanel.add(ageLabel);

        JTextField ageField = new JTextField();
        agePanel.add(ageField);

        fieldsPanel.add(agePanel);

        //Address
        JPanel addressPanel = new JPanel();
        fieldsPanel.add(Box.createVerticalStrut(10));
        addressPanel.setLayout(new BoxLayout(addressPanel, BoxLayout.Y_AXIS));
        addressPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        addressPanel.setMaximumSize(new Dimension(300, 40));

        JLabel addressLabel = new JLabel("Addrress");
        addressPanel.add(addressLabel);

        JTextField addressField = new JTextField();
        addressPanel.add(addressField);

        fieldsPanel.add(addressPanel);

        centerPanel.add(fieldsPanel);


        //Cancel & Save Button
        JPanel buttonsPanel = new JPanel();

        JButton cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(e -> {
            frame.dispose();
            new GuestMainMenu();
        });

        JButton editButton = new JButton("Edit");
        editButton.addActionListener(e -> {

            frame.dispose();
        });

        buttonsPanel.add(cancelButton);
        buttonsPanel.add(editButton);

        centerPanel.add(buttonsPanel);

        //==========================
        frame.addComponent(centerPanel);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        EditProfileMenu editProfileMenu = new EditProfileMenu();
    }
}
