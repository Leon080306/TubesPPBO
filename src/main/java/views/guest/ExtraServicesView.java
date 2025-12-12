package views.guest;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import controller.TaskController;
import models.enums.Department;
import models.enums.TaskStatus;
import views.MainFrame;

public class ExtraServicesView {
    MainFrame frame;
    TaskController taskController;

    public ExtraServicesView() {
        this.taskController = new TaskController();
        renderExtraServicesView();
    }

    private void renderExtraServicesView() {
        frame = new MainFrame();

        JPanel panelGuest = new JPanel();
        panelGuest.setLayout(new BoxLayout(panelGuest, BoxLayout.Y_AXIS));

        panelGuest.setBorder(new EmptyBorder(40, 40, 40, 40));
        panelGuest.setBackground(Color.WHITE);

        panelGuest.add(Box.createVerticalGlue());

        JLabel title = new JLabel("Extra Sevices Menu");
        title.setFont(new Font("Poppins", Font.BOLD, 28));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        panelGuest.add(title);

        panelGuest.add(Box.createVerticalStrut(10));

        JLabel titleAdmin = new JLabel("GUEST MENU");
        titleAdmin.setFont(new Font("SansSerif", Font.BOLD, 14));
        titleAdmin.setAlignmentX(Component.CENTER_ALIGNMENT);
        titleAdmin.setForeground(new Color(100, 100, 100));
        panelGuest.add(titleAdmin);

        panelGuest.add(Box.createVerticalStrut(40));

        JButton buttonAddTask = new JButton("Add Extra Services");
        buttonAddTask.setFont(new Font("SansSerif", Font.BOLD, 14));
        buttonAddTask.setAlignmentX(Component.CENTER_ALIGNMENT);
        buttonAddTask.setFocusPainted(false);
        buttonAddTask.setMaximumSize(new Dimension(250, 45));
        buttonAddTask.setPreferredSize(new Dimension(250, 45));
        buttonAddTask.addActionListener(e -> addExtraService());
        panelGuest.add(buttonAddTask);

        panelGuest.add(Box.createVerticalGlue());

        frame.add(panelGuest);
        frame.setVisible(true);
    }

    private void addExtraService() {
        JFrame frameExtraSevice = new MainFrame();
        JPanel panelExtraService = new JPanel();

        panelExtraService.setLayout(new BoxLayout(panelExtraService, BoxLayout.Y_AXIS));
        panelExtraService.setBorder(new EmptyBorder(20, 20, 20, 20));
        panelExtraService.add(Box.createVerticalGlue());

        JLabel labelDept = new JLabel("Department");
        labelDept.setAlignmentX(Component.CENTER_ALIGNMENT);
        labelDept.setFont(new Font("SansSerif", Font.BOLD, 17));
        panelExtraService.add(labelDept);

        Department[] filteredDepartment = {Department.FOOD,Department.CLEANING};
        JComboBox<Department> listDepartment = new JComboBox<>(filteredDepartment);
        listDepartment.setMaximumSize(new Dimension(300, 30));
        panelExtraService.add(listDepartment);

        panelExtraService.add(Box.createVerticalStrut(10));

        JLabel labelTitle = new JLabel("Service Title");
        labelTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        labelTitle.setFont(new Font("SansSerif", Font.BOLD, 17));
        panelExtraService.add(labelTitle);

        panelExtraService.add(Box.createVerticalStrut(10));
        JComboBox<String> listTitle = new JComboBox<>();
        listTitle.setMaximumSize(new Dimension(300, 30));
        panelExtraService.add(listTitle);

        panelExtraService.add(Box.createVerticalStrut(10));
        JLabel labelDescription = new JLabel("Service Description");
        labelDescription.setAlignmentX(Component.CENTER_ALIGNMENT);
        labelDescription.setFont(new Font("SansSerif", Font.BOLD, 17));
        panelExtraService.add(labelDescription);

        panelExtraService.add(Box.createVerticalStrut(10));

        JTextField fieldDescription = new JTextField(15);
        fieldDescription.setMaximumSize(new Dimension(300, 30));
        panelExtraService.add(fieldDescription);

        panelExtraService.add(Box.createVerticalStrut(10));

        JLabel labelPrice = new JLabel("Price");
        labelPrice.setAlignmentX(Component.CENTER_ALIGNMENT);
        labelPrice.setFont(new Font("SansSerif", Font.BOLD, 17));
        panelExtraService.add(labelPrice);

        panelExtraService.add(Box.createVerticalStrut(10));

        JTextField fieldPrice = new JTextField(15);
        fieldPrice.setMaximumSize(new Dimension(300, 30));
        fieldPrice.setEditable(false);
        panelExtraService.add(fieldPrice);


        Runnable updateMenu = () -> {
            Department selectedDept = (Department) listDepartment.getSelectedItem();
            listTitle.removeAllItems();
            if (selectedDept.equals(Department.FOOD)) {
                listTitle.addItem("Fried Rice Special");
                listTitle.addItem("Wagyu Steak");
                listTitle.addItem("Orange Juice");
            } else if (selectedDept.equals(Department.CLEANING)) {
                listTitle.addItem("Laundry 1kg");
                listTitle.addItem("Shoe Polish");
                listTitle.addItem("Room Deep Cleaning");
            }
        };
        updateMenu.run();
        listDepartment.addActionListener(e -> updateMenu.run());

        listTitle.addActionListener(e -> {
            String selectedMenu = (String) listTitle.getSelectedItem();
            if (selectedMenu != null) {
                double price = 0;

                switch (selectedMenu) {
                    case "Fried Rice Special":
                        price = 45000;
                        break;
                    case "Wagyu Steak":
                        price = 150000;
                        break;
                    case "Orange Juice":
                        price = 30000;
                        break;
                    case "Laundry 1kg":
                        price = 12000;
                        break;
                    case "Shoe Polish":
                        price = 100000;
                        break;
                    case "Room Deep Cleaning":
                        price = 100000;
                        break;
                
                    default:
                        break;
                }
                fieldPrice.setText(String.valueOf(price));
            }
        });

        panelExtraService.add(Box.createVerticalStrut(10));

        JLabel labelDeadline = new JLabel("Deadline");
        labelDeadline.setAlignmentX(Component.CENTER_ALIGNMENT);
        labelDeadline.setFont(new Font("SansSerif", Font.BOLD, 17));
        panelExtraService.add(labelDeadline);

        JTextField fieldDeadline = new JTextField(15);
        fieldDeadline.setText("yyyy-MM-dd HH:mm:ss");
        fieldDeadline.setMaximumSize(new Dimension(300, 30));
        panelExtraService.add(fieldDeadline);

        panelExtraService.add(Box.createVerticalStrut(10));

        JButton buttonAddExtraServ = new JButton("BOOK EXTRA SEVICE");
        buttonAddExtraServ.setFont(new Font("SansSerif", Font.BOLD, 14));
        buttonAddExtraServ.setAlignmentX(Component.CENTER_ALIGNMENT);
        buttonAddExtraServ.setFocusPainted(false);
        buttonAddExtraServ.addActionListener(e -> {
            Department selectedDepartment = (Department) listDepartment.getSelectedItem();
            String title = (String) listTitle.getSelectedItem();
            double price = Double.parseDouble(fieldPrice.getText());
            String description = fieldDescription.getText();
            DateTimeFormatter formatDateTime = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            String deadlineInput = fieldDeadline.getText();
            LocalDateTime deadline = LocalDateTime.parse(deadlineInput, formatDateTime);

            if (taskController.addExtraSevices(title, description, deadlineInput, deadline, selectedDepartment, price)) {
                showDialog(frameExtraSevice, "Success", "Order received. Please wait a moment", () -> {
                    frameExtraSevice.dispose();
                    renderExtraServicesView();
                });
            } else {
                showDialog(frameExtraSevice, "Error", "Failed Order", () -> {
                    frameExtraSevice.dispose();
                    renderExtraServicesView();
                });
            }
        });

        panelExtraService.add(Box.createVerticalStrut(15));
        panelExtraService.add(buttonAddExtraServ);
        panelExtraService.add(Box.createVerticalStrut(15));
        panelExtraService.add(buttonBack(frameExtraSevice, () -> renderExtraServicesView()));
        panelExtraService.add(Box.createVerticalGlue());
        frameExtraSevice.add(panelExtraService);
    }

    private JButton buttonBack(JFrame mainFrame, Runnable nextAction) {
        JButton buttonBack = new JButton("Back to Main Menu");
        buttonBack.setAlignmentX(Component.CENTER_ALIGNMENT);
        buttonBack.addActionListener(e -> {
            mainFrame.dispose();

            if (nextAction != null) {
                nextAction.run();
            }
        });

        return buttonBack;
    }

    private void showDialog(JFrame mainFrame, String title, String message, Runnable nextAction) {
        JDialog dialog = new JDialog(mainFrame, title, true);
        dialog.setSize(250, 150);
        dialog.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 20));
        dialog.setLocationRelativeTo(mainFrame);

        JLabel labelMessage = new JLabel(message);
        labelMessage.setAlignmentX(Component.CENTER_ALIGNMENT);
        dialog.add(labelMessage);

        JButton okButton = new JButton("OK");
        okButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        okButton.addActionListener(e -> {
            if (mainFrame != null) {
                mainFrame.dispose();
            }

            if (nextAction != null) {
                nextAction.run();
            }
        });

        dialog.add(okButton);
        dialog.setVisible(true);
    }
}
