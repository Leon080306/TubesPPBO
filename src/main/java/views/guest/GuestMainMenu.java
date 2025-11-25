package views.guest;

import views.MainFrame;

import javax.swing.*;

public class GuestMainMenu {
    MainFrame frame;

    public GuestMainMenu() {
        frame = new MainFrame();
        renderAdminMainMenu();
    }

    private void renderAdminMainMenu() {
        frame.setVisible(true); // Don't forget this!
    }
}
