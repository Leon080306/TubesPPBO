package views.staff;

import views.MainFrame;
import views.admin.RoomManagementView;
import views.admin.UserManagementView;
import views.shift.ShiftView;
import views.task.TaskView;

import javax.swing.*;
import java.awt.*;

public class StaffMainMenu {
    MainFrame frame;

    public StaffMainMenu() {
        frame = new MainFrame(true);
        renderAdminMainMenu();
    }

    private void renderAdminMainMenu() {
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        JPanel titlePanel = new JPanel(new BorderLayout());
        JLabel title = new JLabel("STAFF MENU", SwingConstants.CENTER);
        title.setFont(new Font("Poppins", Font.BOLD, 32));
        titlePanel.add(title, BorderLayout.CENTER);
        titlePanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 60)); // Add this line
        mainPanel.add(titlePanel);
        mainPanel.add(Box.createVerticalStrut(50));

        JButton taskManagementButton = new JButton("Task Management");
        taskManagementButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        taskManagementButton.addActionListener(e -> {
            frame.dispose();
            new TaskView(false);
        });
        mainPanel.add(taskManagementButton);
        mainPanel.add(Box.createVerticalStrut(20));

        JButton shiftManagementButton = new JButton("Shift Management");
        shiftManagementButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        shiftManagementButton.addActionListener(e -> {
            frame.dispose();
            new ShiftView(false);
        });
        mainPanel.add(shiftManagementButton);
        mainPanel.add(Box.createVerticalStrut(20));

        frame.addComponent(mainPanel);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        new StaffMainMenu();
    }
}
