package views.guest;

import javax.swing.*;

public class GuestMainMenu {
    JFrame frame;

    public GuestMainMenu() {
        frame = new JFrame("Guest Main Menu");
        renderGuestMainMenu();
    }

    private void renderGuestMainMenu() {
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
