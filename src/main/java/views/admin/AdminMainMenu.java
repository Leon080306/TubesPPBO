package views.admin;

import models.Admin;

import javax.swing.*;

public class AdminMainMenu {
    JFrame frame;

    public AdminMainMenu() {
        frame = new JFrame("Admin Main Menu");
        renderAdminMainMenu();
    }

    private void renderAdminMainMenu() {
        int width = 800;
        int height = 600;

        frame.setSize(width, height);
        frame.setLocationRelativeTo(null);
        frame.setAlwaysOnTop(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Add your components here (panels, buttons, etc.)

        frame.setVisible(true); // Don't forget this!
    }
}