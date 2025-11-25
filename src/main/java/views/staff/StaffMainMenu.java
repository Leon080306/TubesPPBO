package views.staff;

import views.MainFrame;

import javax.swing.*;

public class StaffMainMenu {
    MainFrame frame;

    public StaffMainMenu() {
        frame = new MainFrame();
        renderAdminMainMenu();
    }

    private void renderAdminMainMenu() {
        frame.setVisible(true); // Don't forget this!
    }
}
