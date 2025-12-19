package views.guest;

import controller.PaymentControllers;
import models.Booking;
import models.Payment;
import repository.PaymentRepository;
import views.MainFrame;
import javax.swing.*;
import java.awt.*;

public class PaymentView {
    private MainFrame frame;
    private Booking booking;

    public PaymentView(Booking booking) {
        this.booking = booking;
        this.frame = new MainFrame(true);
        renderPaymentDetails();
    }

    private void renderPaymentDetails() {
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Title
        JLabel title = new JLabel("Payment History", SwingConstants.CENTER);
        title.setFont(new Font("Poppins", Font.BOLD, 24));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(title);
        mainPanel.add(Box.createVerticalStrut(20));

        // Fetch Payment Data
        // Note: Ensure PaymentRepository has a method to get payment by Booking ID
        Payment payment = PaymentControllers.getPaymentByBookingID(booking.getBookingID());

        if (payment != null) {
            mainPanel.add(createDetailLabel("Booking ID: " + booking.getBookingID()));
            mainPanel.add(createDetailLabel("Room: " + booking.getRoom().getRoomNumber()));
            mainPanel.add(createDetailLabel("Total Paid: Rp " + payment.getTotalPrice()));
            mainPanel.add(createDetailLabel("Payment Method: " + payment.getPaymentType()));
            mainPanel.add(createDetailLabel("Status: " + payment.getPaymentStatus()));
        } else {
            mainPanel.add(new JLabel("No payment record found for this booking."));
        }

        mainPanel.add(Box.createVerticalStrut(30));

        // Back Button
        JButton backButton = new JButton("Back to History");
        backButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        backButton.addActionListener(e -> {
            frame.dispose();
            new GuestBookingView();
        });
        mainPanel.add(backButton);

        frame.addComponent(mainPanel);
        frame.showFrame();
    }

    private JLabel createDetailLabel(String text) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Poppins", Font.PLAIN, 16));
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        label.setBorder(BorderFactory.createEmptyBorder(5, 0, 5, 0));
        return label;
    }
}