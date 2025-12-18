package views;

import exceptions.NoResultsFound;
import views.admin.AdminBookingView;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    public MainFrame() {
        setTitle("Hotel Harapan Bangsa Management System");

        int width = 800;
        int height = 600;

        setSize(width, height);
        setLocationRelativeTo(null);
        setAlwaysOnTop(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        createMenuBar();

        setLayout(new BorderLayout());
        setVisible(true);
    }

    private void createMenuBar() {
        JMenuBar menuBar = new JMenuBar();

        JMenu fileMenu = new JMenu("Menu");
        JMenuItem exitItem = new JMenuItem("Logout");

        exitItem.addActionListener(e -> {
            dispose();
            try {
                new AdminBookingView();
            } catch (NoResultsFound ex) {
                throw new RuntimeException(ex);
            }
        });

        fileMenu.add(exitItem);
        menuBar.add(fileMenu);
        setJMenuBar(menuBar);
    }
}