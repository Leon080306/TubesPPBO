package views.guest;

import views.MainFrame;

import javax.swing.*;
import java.awt.*;

public class GuestBookingView {
    private MainFrame frame;

    public GuestBookingView(){
        frame = new MainFrame(true);
        renderHistoryMenu();
    }

    private void renderHistoryMenu(){
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        JPanel titlePanel = new JPanel(new BorderLayout());
        JLabel title = new JLabel("Your Booking History", SwingConstants.CENTER);
        title.setFont(new Font("Poppins", Font.BOLD, 32));
        titlePanel.add(title, BorderLayout.CENTER);
        titlePanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 60)); // Add this line
        mainPanel.add(titlePanel);
        mainPanel.add(Box.createVerticalStrut(50));
    }
}
