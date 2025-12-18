package views.guest;

import controller.GuestController;
import models.Guest;
import moduls.GlobalVariables;
import views.MainFrame;
import views.admin.AdminMainMenu;
import views.admin.UserManagementView;

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
        JLabel title = new JLabel("Welcome "+ GlobalVariables.getUser().getNama(), SwingConstants.CENTER);
        title.setFont(new Font("Poppins", Font.BOLD, 32));
        titlePanel.add(title, BorderLayout.CENTER);
        titlePanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 60)); // Add this line
        mainPanel.add(titlePanel);
        mainPanel.add(Box.createVerticalStrut(50));

        //Extra Services
        if (GlobalVariables.getUser().){

        }
        JButton extraServicesButton = new JButton("Extra Services");
        extraServicesButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        extraServicesButton.addActionListener(e -> {
            frame.dispose();
            new ExtraServicesView();
        });
        mainPanel.add(extraServicesButton);
        mainPanel.add(Box.createVerticalStrut(20));


        frame.addComponent(mainPanel);
        frame.setVisible(true);
    }
    public static void main(String[] args) {
        new AdminMainMenu();
    }
}
