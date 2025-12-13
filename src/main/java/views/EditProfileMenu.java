package views;

import controller.GuestController;
import controller.StaffController;
import controller.UserController;
import models.Guest;
import models.Staff;
import models.Users;
import models.enums.Department;
import models.enums.MembershipLevel;
import models.enums.UserType;
import moduls.GlobalVariables;
import utils.PasswordHashing;
import views.admin.UserManagementView;
import views.guest.GuestMainMenu;

import javax.swing.*;
import java.awt.*;

public class EditProfileMenu {
    private MainFrame frame;
    private Users userData;
    private boolean isEditingOwnProfile;

    public EditProfileMenu(Users userData) {
        this.isEditingOwnProfile = false;
        this.userData = userData;
        if(userData == null) {
            JOptionPane.showMessageDialog(null, "Error retrieving user data", "Error", JOptionPane.ERROR_MESSAGE);
            if(isEditingOwnProfile) {
                new GuestMainMenu();
            }
        }
        frame = new MainFrame(true);
        renderEditProfileMenu();
    }

    public EditProfileMenu() {
        this.isEditingOwnProfile = true;
        this.userData = GlobalVariables.getUser();
        if(userData == null) {
            JOptionPane.showMessageDialog(null, "Error retrieving user data", "Error", JOptionPane.ERROR_MESSAGE);
            if(isEditingOwnProfile) {
                new GuestMainMenu();
            }
        }
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
        emailLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        emailPanel.add(emailLabel);

        JTextField emailField = new JTextField();
        emailField.setAlignmentX(Component.LEFT_ALIGNMENT);
        emailField.setText(userData.getEmail());
        emailPanel.add(emailField);

        fieldsPanel.add(emailPanel);

        //Password
        JPanel passwordPanel = new JPanel();
        fieldsPanel.add(Box.createVerticalStrut(10));
        passwordPanel.setLayout(new BoxLayout(passwordPanel, BoxLayout.Y_AXIS));
        passwordPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        passwordPanel.setMaximumSize(new Dimension(300, 40));

        JLabel passwordLabel = new JLabel("Enter New Password (Optional)");
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
        nameField.setText(userData.getNama());
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
        phoneField.setText(userData.getPhone());
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
        ageField.setText(String.valueOf(userData.getUmur()));
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
        addressField.setText(userData.getAddress());
        addressPanel.add(addressField);

        fieldsPanel.add(addressPanel);

        //Admin updating users profile
        //for staff and admin profiles
        JTextField salaryField = new JTextField();
        JComboBox<Department> departmentField = new JComboBox<>(Department.values());

        //for guest profiles
        JTextField pointsField = new JTextField();
        JComboBox<MembershipLevel> membershipField = new JComboBox<>(MembershipLevel.values());
        if(!isEditingOwnProfile) {
            if(userData.getUserType() == UserType.STAFF || userData.getUserType() == UserType.ADMIN) {
                Staff staffData = (Staff) userData;

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
                salaryField.setText(String.valueOf(staffData.getSalary()));
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

                departmentField.addActionListener(e -> {
                    Department selectedDepartment = (Department) departmentField.getSelectedItem();
                    JDialog dialog = new JDialog(frame, "Warning", true);
                    dialog.setSize(400, 200);
                    dialog.setLocationRelativeTo(frame);
                    dialog.setLayout(new BorderLayout());

                    JPanel buttonsPanel = new JPanel();
                    JButton confirmButton = new JButton("Confirm");
                    JButton cancelButton = new JButton("Cancel");
                    cancelButton.addActionListener(action -> {
                        departmentField.setSelectedItem(staffData.getDepartment());
                        dialog.dispose();
                    });

                    if(selectedDepartment == Department.ADMIN && userData.getUserType() == UserType.STAFF) {
                        JLabel messageLabel = new JLabel("Are you sure you want to make " + userData.getNama() + " an admin?", SwingConstants.CENTER);
                        dialog.add(messageLabel, BorderLayout.CENTER);

                        confirmButton.addActionListener(action -> {
                            userData.setUserType(UserType.ADMIN);
                            dialog.dispose();
                        });

                        buttonsPanel.add(confirmButton);
                        buttonsPanel.add(cancelButton);

                        dialog.add(buttonsPanel, BorderLayout.SOUTH);

                        dialog.setVisible(true);
                    }
                    else if(selectedDepartment != Department.ADMIN && userData.getUserType() == UserType.ADMIN) {
                        JLabel messageLabel = new JLabel("Are you sure you want to remove admin access from " + userData.getNama() + "?", SwingConstants.CENTER);
                        dialog.add(messageLabel, BorderLayout.CENTER);

                        confirmButton.addActionListener(action -> {
                            userData.setUserType(UserType.STAFF);
                            dialog.dispose();
                        });

                        buttonsPanel.add(confirmButton);
                        buttonsPanel.add(cancelButton);

                        dialog.add(buttonsPanel, BorderLayout.SOUTH);

                        dialog.setVisible(true);
                    }
                });
                departmentField.setAlignmentX(Component.LEFT_ALIGNMENT);
                departmentField.setSelectedItem(staffData.getDepartment());
                deparmentPanel.add(departmentField);
                fieldsPanel.add(deparmentPanel);
            }
            else if(userData.getUserType() == UserType.GUEST) {
                Guest guestData = (Guest) userData;

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
                membershipField.setSelectedItem(guestData.getMembershipLevel());
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
                pointsField.setText(String.valueOf(guestData.getPoints()));
                pointsPanel.add(pointsField);
                fieldsPanel.add(pointsPanel);
            }
        }

        centerPanel.add(fieldsPanel);

        //Cancel & Save Button
        JPanel buttonsPanel = new JPanel();

        JButton cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(e -> {
            frame.dispose();
            if(isEditingOwnProfile) {
                new GuestMainMenu();
            }
            else {
                new UserManagementView();
            }
        });

        JButton editButton = new JButton("Edit");
        editButton.addActionListener(e -> {
            boolean isSuccessful = UserController.updateUserData(nameField.getText(), Integer.parseInt(ageField.getText()), emailField.getText(), phoneField.getText(), addressField.getText(), userData.getUserType(), userData.getUserID());
            if(isSuccessful) {
                if(isEditingOwnProfile) {
                    GlobalVariables.refreshUserData();
                }
                else {
                    if(userData.getUserType() == UserType.GUEST) {
                        System.out.println("UPDATING GUEST DATA");
                        GuestController.updateGuestData(Integer.parseInt(pointsField.getText()), (MembershipLevel) membershipField.getSelectedItem(), userData.getUserID());
                    }
                    else if(userData.getUserType() == UserType.STAFF) {
                        StaffController.updateStaffData(Double.parseDouble(salaryField.getText()), (Department) departmentField.getSelectedItem(), userData.getUserID());
                    }
                }
                JOptionPane.showMessageDialog(frame, "Data Successfully Updated", "Error", JOptionPane.PLAIN_MESSAGE);
                frame.dispose();
                if(isEditingOwnProfile) {
                    new GuestMainMenu();
                }
                else {
                    new UserManagementView();
                }
            }
            else {
                JOptionPane.showMessageDialog(frame, "Error when updating data", "Error", JOptionPane.ERROR_MESSAGE);
                frame.dispose();
                if(isEditingOwnProfile) {
                    new GuestMainMenu();
                }
                else {
                    new UserManagementView();
                }
            }

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