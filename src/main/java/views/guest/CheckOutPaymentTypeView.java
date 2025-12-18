package views.guest;

import models.Booking;
import moduls.GlobalVariables;
import views.MainFrame;
import javax.swing.*;
import java.awt.*;

public class CheckOutPaymentTypeView {
    private MainFrame frame;
    private Booking booking;
    // We grab the price from the room associated with the booking!
    private double amountNeeded;

    public CheckOutPaymentTypeView(Booking booking) {
        this.booking = booking;
        this.amountNeeded = (booking != null && booking.getRoom() != null) ? booking.getRoom().getRoomPrice() : 0.0;
        this.frame = new MainFrame(true);
        renderView();
    }

    private void renderView() {
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));

        JLabel title = new JLabel("CHECKOUT - Total: Rp " + amountNeeded);
        title.setFont(new Font("Poppins", Font.BOLD, 22));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(title);
        mainPanel.add(Box.createVerticalStrut(20));

        String[] types = {"Select Payment", "Cash", "Card", "E-Wallet"};
        JComboBox<String> paymentTypeCombo = new JComboBox<>(types);
        mainPanel.add(paymentTypeCombo);
        mainPanel.add(Box.createVerticalStrut(20));

        JPanel cards = new JPanel(new CardLayout());

        // --- CASH PANEL (Tips + Nominal) ---
        JPanel cashPanel = new JPanel(new GridLayout(2, 2, 5, 5));
        cashPanel.add(new JLabel("Tips (Optional):"));
        JTextField tipsField = new JTextField();
        cashPanel.add(tipsField);
        cashPanel.add(new JLabel("Nominal:"));
        JTextField cashNominal = new JTextField();
        cashPanel.add(cashNominal);

        // --- CARD PANEL (CC Number + PIN + Nominal) ---
        JPanel cardPanel = new JPanel(new GridLayout(3, 2, 5, 5));
        cardPanel.add(new JLabel("Card Number:"));
        JTextField cardNum = new JTextField();
        cardPanel.add(cardNum);
        cardPanel.add(new JLabel("PIN:"));
        JPasswordField pinField = new JPasswordField();
        cardPanel.add(pinField);
        cardPanel.add(new JLabel("Nominal:"));
        JTextField cardNominal = new JTextField();
        cardPanel.add(cardNominal);

        // --- E-WALLET PANEL (Provider + Account ID + Nominal) ---
        JPanel walletPanel = new JPanel(new GridLayout(3, 2, 5, 5));
        walletPanel.add(new JLabel("Provider (OVO/Gopay):"));
        JTextField providerField = new JTextField();
        walletPanel.add(providerField);
        walletPanel.add(new JLabel("Account ID:"));
        JTextField accountIdField = new JTextField();
        walletPanel.add(accountIdField);
        walletPanel.add(new JLabel("Nominal:"));
        JTextField walletNominal = new JTextField();
        walletPanel.add(walletNominal);

        cards.add(new JPanel(), "Select Payment");
        cards.add(cashPanel, "Cash");
        cards.add(cardPanel, "Card");
        cards.add(walletPanel, "E-Wallet");

        mainPanel.add(cards);
        mainPanel.add(Box.createVerticalStrut(30));

        paymentTypeCombo.addActionListener(e -> {
            CardLayout cl = (CardLayout)(cards.getLayout());
            cl.show(cards, (String)paymentTypeCombo.getSelectedItem());
        });

        JButton payButton = new JButton("FINALIZE CHECKOUT");
        payButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        payButton.addActionListener(e -> {
            JOptionPane.showMessageDialog(null, "Payment Processed! Thanks for staying, you legend!");
            GlobalVariables.setBooking(null); // Clear the active booking
            frame.dispose();
            new GuestMainMenu();
        });

        mainPanel.add(payButton);
        frame.addComponent(mainPanel);
        frame.setVisible(true);
    }
}