package views.admin;

import models.Admin;
import views.MainFrame;

import javax.swing.*;

public class AdminMainMenu {
    MainFrame frame;

    public AdminMainMenu() {
        frame = new MainFrame();
        renderAdminMainMenu();
    }

    private void renderAdminMainMenu() {
        frame.setVisible(true); // Don't forget this!
    }
}