package views;

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
    }

    public void showFrame() {
        setVisible(true);
    }

    private void createMenuBar() {
        JMenuBar menuBar = new JMenuBar();

        JMenu fileMenu = new JMenu("Menu");
        JMenuItem exitItem = new JMenuItem("Logout");

        exitItem.addActionListener(e -> {
            dispose();
            new LoginView();
        });

        fileMenu.add(exitItem);
        menuBar.add(fileMenu);
        setJMenuBar(menuBar);
    }

    private void showDialog(String title, String message) {
        JDialog dialog = new JDialog(this, title, true);
        dialog.setSize(300, 150);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        panel.add(Box.createVerticalStrut(20));

        JLabel label = new JLabel(message);
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(label);

        panel.add(Box.createVerticalStrut(20));

        JButton okButton = new JButton("OK");
        okButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        panel.add(okButton);

        dialog.add(panel);
        dialog.setLocationRelativeTo(this);
        dialog.setVisible(true);
    }
}
