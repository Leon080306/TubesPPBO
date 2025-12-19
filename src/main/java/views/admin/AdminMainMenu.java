package views.admin;

import models.Admin;
import views.MainFrame;
import views.shift.ShiftView;
import views.task.TaskView;

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
        JPanel titlePanel = new JPanel(new BorderLayout());
        JLabel title = new JLabel("ADMIN MENU", SwingConstants.CENTER);
        title.setFont(new Font("Poppins", Font.BOLD, 32));
        titlePanel.add(title, BorderLayout.CENTER);
        titlePanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 60)); // Add this line
        mainPanel.add(titlePanel);
        mainPanel.add(Box.createVerticalStrut(50));

        JButton userManagementButton = new JButton("User Management");
        userManagementButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        userManagementButton.addActionListener(e -> {
            frame.dispose();
            new UserManagementView();
        });
        mainPanel.add(userManagementButton);
        mainPanel.add(Box.createVerticalStrut(20));

        JButton roomManagementButton = new JButton("Room Management");
        roomManagementButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        roomManagementButton.addActionListener(e -> {
            frame.dispose();
            new RoomManagementView();
        });
        mainPanel.add(roomManagementButton);
        mainPanel.add(Box.createVerticalStrut(20));

        JButton taskManagementButton = new JButton("Task Management");
        taskManagementButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        taskManagementButton.addActionListener(e -> {
            frame.dispose();
            new TaskView(true);
        });
        mainPanel.add(taskManagementButton);
        mainPanel.add(Box.createVerticalStrut(20));

        JButton shiftManagementButton = new JButton("Shift Management");
        shiftManagementButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        shiftManagementButton.addActionListener(e -> {
            frame.dispose();
            new ShiftView(true);
        });
        mainPanel.add(shiftManagementButton);
        mainPanel.add(Box.createVerticalStrut(20));

        JButton bookingManagementButton = new JButton("Hotel Booking History");
        bookingManagementButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        bookingManagementButton.addActionListener(e -> {
            frame.dispose();
            new AdminBookingView();
        });
        mainPanel.add(bookingManagementButton);
        mainPanel.add(Box.createVerticalStrut(20));

        frame.addComponent(mainPanel);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        new AdminMainMenu();
    }
}