package views.admin;

import controller.GuestController;
import controller.StaffController;
import controller.UserController;
import models.enums.Department;
import models.enums.MembershipLevel;
import models.enums.UserType;
import moduls.GlobalVariables;
import utils.PasswordHashing;
import views.MainFrame;
import views.guest.GuestMainMenu;

import javax.swing.*;
import java.awt.*;

public class AddUserView {
    private MainFrame frame;
    boolean isAddingStaff;

    public AddUserView(boolean isAddingStaff) {
        this.isAddingStaff = isAddingStaff;
        frame = new MainFrame(true);
        renderAddUserView();
    }

    public void renderAddUserView() {
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        String textTitle = "";
        if(isAddingStaff) {
            textTitle = "Add New Staff";
        }
        else {
            textTitle = "Add New Guest";
        }
        JPanel titlePanel = new JPanel(new BorderLayout());
        JLabel title = new JLabel(textTitle, SwingConstants.CENTER);
        title.setFont(new Font("Poppins", Font.BOLD, 32));
        titlePanel.add(title, BorderLayout.CENTER);
        mainPanel.add(titlePanel);

        //Fields panel
        JPanel fieldsPanel = new JPanel();
        fieldsPanel.setLayout(new BoxLayout(fieldsPanel, BoxLayout.Y_AXIS));
        fieldsPanel.add(Box.createVerticalStrut(30));

        //user data
        //Email
        JPanel emailPanel = new JPanel();
        emailPanel.setLayout(new BoxLayout(emailPanel, BoxLayout.Y_AXIS));
        emailPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        emailPanel.setMaximumSize(new Dimension(300, 40));

        JLabel emailLabel = new JLabel("Email");
        emailLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        emailPanel.add(emailLabel);

        JTextField emailField = new JTextField();
        emailField.setAlignmentX(Component.LEFT_ALIGNMENT);
        emailPanel.add(emailField);

        fieldsPanel.add(emailPanel);

        //Password
        JPanel passwordPanel = new JPanel();
        fieldsPanel.add(Box.createVerticalStrut(10));
        passwordPanel.setLayout(new BoxLayout(passwordPanel, BoxLayout.Y_AXIS));
        passwordPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        passwordPanel.setMaximumSize(new Dimension(300, 40));

        JLabel passwordLabel = new JLabel("Password");
        passwordLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        passwordPanel.add(passwordLabel);

        JPasswordField passwordField = new JPasswordField();
        passwordField.setAlignmentX(Component.LEFT_ALIGNMENT);
        passwordPanel.add(passwordField);

        fieldsPanel.add(passwordPanel);

        //Nama
        JPanel namePanel = new JPanel();
        fieldsPanel.add(Box.createVerticalStrut(10));
        namePanel.setLayout(new BoxLayout(namePanel, BoxLayout.Y_AXIS));
        namePanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        namePanel.setMaximumSize(new Dimension(300, 40));

        JLabel nameLabel = new JLabel("Full Name");
        nameLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        namePanel.add(nameLabel);

        JTextField nameField = new JTextField();
        nameField.setAlignmentX(Component.LEFT_ALIGNMENT);
        namePanel.add(nameField);

        fieldsPanel.add(namePanel);

        //Phone Number
        JPanel phonePanel = new JPanel();
        fieldsPanel.add(Box.createVerticalStrut(10));
        phonePanel.setLayout(new BoxLayout(phonePanel, BoxLayout.Y_AXIS));
        phonePanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        phonePanel.setMaximumSize(new Dimension(300, 40));

        JLabel phoneLabel = new JLabel("Phone Number");
        phoneLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        phonePanel.add(phoneLabel);

        JTextField phoneField = new JTextField();
        phoneField.setAlignmentX(Component.LEFT_ALIGNMENT);
        phonePanel.add(phoneField);

        fieldsPanel.add(phonePanel);

        //Umur
        JPanel agePanel = new JPanel();
        fieldsPanel.add(Box.createVerticalStrut(10));
        agePanel.setLayout(new BoxLayout(agePanel, BoxLayout.Y_AXIS));
        agePanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        agePanel.setMaximumSize(new Dimension(300, 40));

        JLabel ageLabel = new JLabel("Age");
        ageLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        agePanel.add(ageLabel);

        JTextField ageField = new JTextField();
        ageField.setAlignmentX(Component.LEFT_ALIGNMENT);
        agePanel.add(ageField);

        fieldsPanel.add(agePanel);

        //Address
        JPanel addressPanel = new JPanel();
        fieldsPanel.add(Box.createVerticalStrut(10));
        addressPanel.setLayout(new BoxLayout(addressPanel, BoxLayout.Y_AXIS));
        addressPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        addressPanel.setMaximumSize(new Dimension(300, 40));

        JLabel addressLabel = new JLabel("Addrress");
        addressLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        addressPanel.add(addressLabel);

        JTextField addressField = new JTextField();
        addressField.setAlignmentX(Component.LEFT_ALIGNMENT);
        addressPanel.add(addressField);

        fieldsPanel.add(addressPanel);

        //Adding staff
        JTextField salaryField = new JTextField();
        JComboBox<Department> departmentField = new JComboBox<>(Department.values());

        //adding guest
        JTextField pointsField = new JTextField();
        JComboBox<MembershipLevel> membershipField = new JComboBox<>(MembershipLevel.values());

        if(isAddingStaff) {
            //Salary
            JPanel salaryPanel = new JPanel();
            fieldsPanel.add(Box.createVerticalStrut(10));
            salaryPanel.setLayout(new BoxLayout(salaryPanel, BoxLayout.Y_AXIS));
            salaryPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
            salaryPanel.setMaximumSize(new Dimension(300, 40));

            JLabel salaryLabel = new JLabel("Salary");
            salaryLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
            salaryPanel.add(salaryLabel);

            salaryField.setAlignmentX(Component.LEFT_ALIGNMENT);
            salaryPanel.add(salaryField);
            fieldsPanel.add(salaryPanel);

            //Department
            JPanel deparmentPanel = new JPanel();
            fieldsPanel.add(Box.createVerticalStrut(10));
            deparmentPanel.setLayout(new BoxLayout(deparmentPanel, BoxLayout.Y_AXIS));
            deparmentPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
            deparmentPanel.setMaximumSize(new Dimension(300, 40));

            JLabel departmentLabel = new JLabel("Deparment");
            departmentLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
            deparmentPanel.add(departmentLabel);

            departmentField.setAlignmentX(Component.LEFT_ALIGNMENT);
            deparmentPanel.add(departmentField);
            fieldsPanel.add(deparmentPanel);
        }
        else {
            //Membership level
            JPanel membershipPanel = new JPanel();
            fieldsPanel.add(Box.createVerticalStrut(10));
            membershipPanel.setLayout(new BoxLayout(membershipPanel, BoxLayout.Y_AXIS));
            membershipPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
            membershipPanel.setMaximumSize(new Dimension(300, 40));

            JLabel membershipLabel = new JLabel("Membership Level");
            membershipLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
            membershipPanel.add(membershipLabel);

            membershipField.setAlignmentX(Component.LEFT_ALIGNMENT);
            membershipPanel.add(membershipField);
            fieldsPanel.add(membershipPanel);

            //Points
            JPanel pointsPanel = new JPanel();
            fieldsPanel.add(Box.createVerticalStrut(10));
            pointsPanel.setLayout(new BoxLayout(pointsPanel, BoxLayout.Y_AXIS));
            pointsPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
            pointsPanel.setMaximumSize(new Dimension(300, 40));

            JLabel pointsLabel = new JLabel("Points");
            pointsLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
            pointsPanel.add(pointsLabel);

            pointsField.setAlignmentX(Component.LEFT_ALIGNMENT);
            pointsPanel.add(pointsField);
            fieldsPanel.add(pointsPanel);
        }

        mainPanel.add(fieldsPanel);

        JPanel buttonsPanel = new JPanel();

        JButton cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(e -> {
            frame.dispose();
            new UserManagementView();
        });

        JButton confirmButton = new JButton("Confirm");
        confirmButton.addActionListener(e -> {
            UserType userType = UserType.GUEST;
            if(isAddingStaff) {
                StaffController.addStaff(PasswordHashing.hashPassword(passwordField.getText()), nameField.getText(), Integer.parseInt(ageField.getText()), emailField.getText(), phoneField.getText(), addressField.getText(), Double.parseDouble(salaryField.getText()), (Department) departmentField.getSelectedItem());
            }
            else {
                GuestController.addNewGuest(PasswordHashing.hashPassword(passwordField.getText()), nameField.getText(), Integer.parseInt(ageField.getText()), emailField.getText(), phoneField.getText(), addressField.getText(), (MembershipLevel) membershipField.getSelectedItem(), Integer.parseInt(pointsField.getText()));
            }
            frame.dispose();
            new UserManagementView();
        });

        buttonsPanel.add(cancelButton);
        buttonsPanel.add(confirmButton);

        mainPanel.add(buttonsPanel);

        //====================
        frame.addComponent(mainPanel);
        frame.showFrame();
    }

    public static void main(String[] args) {
        new AddUserView(false);
    }
}
