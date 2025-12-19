package views.guest;

import controller.BookingController;
import controller.PaymentControllers;
import controller.TaskController;
import models.*;
import models.enums.BookingStatus;
import moduls.GlobalVariables;
import repository.BookingRepository;
import repository.PaymentRepository;
import views.MainFrame;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class CheckOutPaymentTypeView {
    private MainFrame frame;
    private Booking booking;
    private double totalPrice;

    public CheckOutPaymentTypeView(Booking booking) {
        this.booking = booking;
        calculateTotal();
        this.frame = new MainFrame(true);

        renderView();
    }

    private void calculateTotal() {
        this.totalPrice = (booking != null && booking.getRoom() != null) ? booking.getRoom().getRoomPrice() : 0.0;

        if (booking != null) {
            List<ExtraServices> tasks = TaskController.getExtraServicesByBookingId(booking.getBookingID());
            if (tasks != null) {
                for (ExtraServices eS : tasks) {
                    totalPrice += eS.getPrice();
                }
            }
        }
    }

    private void renderView() {
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));

        JLabel title = new JLabel("CHECKOUT - Total: Rp " + totalPrice);
        title.setFont(new Font("Poppins", Font.BOLD, 22));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(title);
        mainPanel.add(Box.createVerticalStrut(20));

        String[] types = {"Select Payment", "Cash", "Card", "E-Wallet"};
        JComboBox<String> paymentTypeCombo = new JComboBox<>(types);
        mainPanel.add(paymentTypeCombo);
        mainPanel.add(Box.createVerticalStrut(20));

        JPanel cards = new JPanel(new CardLayout());

        JTextField tipsField = new JTextField();
        JTextField cashNominal = new JTextField();
        JTextField cardNum = new JTextField();
        JTextField cardNominal = new JTextField();
        JTextField providerField = new JTextField();
        JTextField accountIdField = new JTextField();
        JTextField walletNominal = new JTextField();

        // --- CASH PANEL ---
        JPanel cashPanel = new JPanel(new GridLayout(2, 2, 5, 5));
        cashPanel.add(new JLabel("Tips (Optional):"));
        cashPanel.add(tipsField);
        cashPanel.add(new JLabel("Nominal:"));
        cashPanel.add(cashNominal);

        // --- CARD PANEL ---
        JPanel cardPanel = new JPanel(new GridLayout(2, 2, 5, 5));
        cardPanel.add(new JLabel("Card Number:"));
        cardPanel.add(cardNum);
        cardPanel.add(new JLabel("Nominal:"));
        cardPanel.add(cardNominal);

        // --- E-WALLET PANEL ---
        JPanel walletPanel = new JPanel(new GridLayout(3, 2, 5, 5));
        walletPanel.add(new JLabel("Provider:"));
        walletPanel.add(providerField);
        walletPanel.add(new JLabel("Account ID:"));
        walletPanel.add(accountIdField);
        walletPanel.add(new JLabel("Nominal:"));
        walletPanel.add(walletNominal);

        cards.add(new JPanel(), "Select Payment");
        cards.add(cashPanel, "Cash");
        cards.add(cardPanel, "Card");
        cards.add(walletPanel, "E-Wallet");

        mainPanel.add(cards);
        mainPanel.add(Box.createVerticalStrut(30));

        // --- MESSAGE PANEL ---
        JLabel statusLabel = new JLabel(" ");
        statusLabel.setFont(new Font("Poppins", Font.PLAIN, 14));
        statusLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(statusLabel);
        mainPanel.add(Box.createVerticalStrut(20));

        paymentTypeCombo.addActionListener(e -> {
            ((CardLayout) cards.getLayout()).show(cards, (String) paymentTypeCombo.getSelectedItem());
        });

        JButton payButton = new JButton("FINALIZE CHECKOUT");
        payButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        payButton.addActionListener(e -> {
            String selected = (String) paymentTypeCombo.getSelectedItem();
            double nominalInput = 0;
            boolean paymentSuccess = false;

            try {
                // 1. Get the existing payment record to retrieve its ID
                Payment currentPayment = PaymentControllers.getPaymentByBookingID(booking.getBookingID());

                System.out.println("PAYMENT ID: " + currentPayment.getPaymentID());
                Guest test = (Guest) GlobalVariables.getUser();
                System.out.println("GUEST ID: " + test.getGuestID());
                System.out.println("BOOKING ID: " + booking.getBookingID());
                if (currentPayment == null) {
                    statusLabel.setText("<html><font color='red'>No payment record found! Check your DB, shit.</font></html>");
                    return;
                }

                String pID = currentPayment.getPaymentID();
                Guest gID = (Guest) GlobalVariables.getUser();
                String bID = booking.getBookingID();

                // 2. Process based on selected type
                if ("Cash".equals(selected)) {
                    nominalInput = Double.parseDouble(cashNominal.getText());
                    if (nominalInput >= totalPrice) {
                        double tips = tipsField.getText().isEmpty() ? 0 : Double.parseDouble(tipsField.getText());
                        // Calling with: (paymentID, nominal, tips, guestID, bookingID)
                        paymentSuccess = PaymentControllers.cashPayment(pID, nominalInput, tips, gID.getGuestID(), bID);
                    }
                } else if ("Card".equals(selected)) {
                    nominalInput = Double.parseDouble(cardNominal.getText());
                    PaymentByCard cardPay = new PaymentByCard(null, totalPrice, null, null, booking, null, cardNum.getText());

                    if (cardPay.securePayment() && nominalInput >= totalPrice) {
                        // Calling with: (paymentID, nominal, creditCardNumber, guestID, bookingID)
                        paymentSuccess = PaymentControllers.cardPayment(pID, nominalInput, cardNum.getText(), gID.getGuestID(), bID);
                    } else if (!cardPay.securePayment()) {
                        statusLabel.setText("<html><font color='red'>Invalid Card! Must be 16 digits.</font></html>");
                        return;
                    }
                } else if ("E-Wallet".equals(selected)) {
                    nominalInput = Double.parseDouble(walletNominal.getText());
                    PaymentByEWallet walletPay = new PaymentByEWallet(null, totalPrice, null, null, booking, null, providerField.getText(), accountIdField.getText());

                    if (walletPay.securePayment() && nominalInput >= totalPrice) {
                        // Calling with: (paymentID, nominal, provider, accountID, guestID, bookingID)
                        paymentSuccess = PaymentControllers.eWalletPayment(pID, nominalInput, providerField.getText(), accountIdField.getText(), gID.getGuestID(), bID);
                    } else if (!walletPay.securePayment()) {
                        statusLabel.setText("<html><font color='red'>Invalid Account ID format!</font></html>");
                        return;
                    }
                }

                // 3. Finalize if successful
                if (paymentSuccess) {
                    booking.setBookingStatus(BookingStatus.CHECKED_OUT);
                    BookingController.checkOutBooking(booking.getBookingID());

                    statusLabel.setText("<html><font color='green'>Payment Success! You're good to go.</font></html>");

                    Timer timer = new Timer(1500, event -> {
                        GlobalVariables.setBooking(null);
                        frame.dispose();
                        new GuestMainMenu();
                    });
                    timer.setRepeats(false);
                    timer.start();
                } else {
                    statusLabel.setText("<html><font color='red'>Nominal is not enough! Needed: Rp " + totalPrice + "</font></html>");
                }
            } catch (NumberFormatException ex) {
                statusLabel.setText("<html><font color='red'>Enter valid numbers, for fuck's sake!</font></html>");
            } catch (Exception ex) {
                statusLabel.setText("<html><font color='red'>Something went wrong: " + ex.getMessage() + "</font></html>");
            }

        });

        mainPanel.add(payButton);
        frame.addComponent(mainPanel);
        frame.setVisible(true);
    }
}