package views.staff;

import javax.swing.*;

public class StaffMainMenu {
    JFrame frame;

    public StaffMainMenu() {
        frame = new JFrame("Staff Main Menu");
        renderStaffMainMenu();
    }

    private void renderStaffMainMenu() {
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
