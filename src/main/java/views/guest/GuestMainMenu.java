package views.guest;

import models.Guest;
import moduls.GlobalVariables;
import views.MainFrame;
import views.shift.ShiftView;
import views.task.TaskView;

import javax.swing.*;
import java.awt.*;

public class GuestMainMenu {
    MainFrame frame;

    public GuestMainMenu() {
        frame = new MainFrame(true);
        renderAdminMainMenu();
    }

    private void renderAdminMainMenu() {
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        JPanel titlePanel = new JPanel(new BorderLayout());
        JLabel title = new JLabel("GUEST MENU", SwingConstants.CENTER);
        title.setFont(new Font("Poppins", Font.BOLD, 32));
        titlePanel.add(title, BorderLayout.CENTER);
        titlePanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 60)); // Add this line
        mainPanel.add(titlePanel);
        mainPanel.add(Box.createVerticalStrut(50));

        //user is currently in a booking
        if(GlobalVariables.getBooking() != null) {
            JButton extraServiceButton = new JButton("Order Extra Service");
            extraServiceButton.setAlignmentX(Component.CENTER_ALIGNMENT);
            extraServiceButton.addActionListener(e -> {
                frame.dispose();
                new ExtraServicesView();
            });
            mainPanel.add(extraServiceButton);
            mainPanel.add(Box.createVerticalStrut(20));
        }

        frame.addComponent(mainPanel);
        frame.setVisible(true);
    }
}
