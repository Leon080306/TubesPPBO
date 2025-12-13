package views.admin;

import models.Admin;
import views.MainFrame;

import javax.swing.*;
import java.awt.*;

public class AdminMainMenu {
    MainFrame frame;

    public AdminMainMenu() {
        frame = new MainFrame(true);
        renderAdminMainMenu();
    }

    private void renderAdminMainMenu() {
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        JLabel title = new JLabel("ADMIN MAIN MENU");
        title.setFont(new Font("Poppins", Font.BOLD, 32));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(title);
        mainPanel.add(Box.createVerticalStrut(50));

        JButton userManagementButton = new JButton("User Management");
        userManagementButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        userManagementButton.addActionListener(e -> {
            frame.dispose();
            new UserManagementView();
        });

        mainPanel.add(userManagementButton);

        frame.addComponent(mainPanel);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        new AdminMainMenu();
    }
}