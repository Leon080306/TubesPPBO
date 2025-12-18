package views.shift;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import controller.ShiftController;
import controller.StaffController;
import models.Shift;
import models.Staff;
import views.MainFrame;

public class ShiftView {
    MainFrame frame;
    ShiftController shiftController;
    StaffController staffController;

    public ShiftView() {
        this.shiftController = new ShiftController();
        this.staffController = new StaffController();
        renderShiftViewAdmin();
        // renderShiftViewStaff();
    }

    private void renderShiftViewAdmin() {
        frame = new MainFrame();
        JPanel panelAdmin = new JPanel();

        panelAdmin.setLayout(new BoxLayout(panelAdmin, BoxLayout.Y_AXIS));

        panelAdmin.setBorder(new EmptyBorder(40, 40, 40, 40));
        panelAdmin.setBackground(Color.WHITE);

        panelAdmin.add(Box.createVerticalGlue());

        JLabel title = new JLabel("Shift Management Menu");
        title.setFont(new Font("Poppins", Font.BOLD, 28));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        panelAdmin.add(title);

        panelAdmin.add(Box.createVerticalStrut(10));

        JLabel titleAdmin = new JLabel("ADMIN MENU");
        titleAdmin.setFont(new Font("SansSerif", Font.BOLD, 14));
        titleAdmin.setAlignmentX(Component.CENTER_ALIGNMENT);
        titleAdmin.setForeground(new Color(100, 100, 100));
        panelAdmin.add(titleAdmin);

        panelAdmin.add(Box.createVerticalStrut(40));

        JButton buttonAddTask = new JButton("Add Task");
        buttonAddTask.setFont(new Font("SansSerif", Font.BOLD, 14));
        buttonAddTask.setAlignmentX(Component.CENTER_ALIGNMENT);
        buttonAddTask.setFocusPainted(false);
        buttonAddTask.setMaximumSize(new Dimension(250, 45));
        buttonAddTask.setPreferredSize(new Dimension(250, 45));
        buttonAddTask.addActionListener(e -> addShift());
        panelAdmin.add(buttonAddTask);

        JButton buttonShowShift = new JButton("View Daily Shift");
        buttonShowShift.setFont(new Font("SansSerif", Font.BOLD, 14));
        buttonShowShift.setAlignmentX(Component.CENTER_ALIGNMENT);
        buttonShowShift.setFocusPainted(false);
        buttonShowShift.setMaximumSize(new Dimension(250, 45));
        buttonShowShift.setPreferredSize(new Dimension(250, 45));
        buttonShowShift.addActionListener(e -> viewDailyShift());
        panelAdmin.add(buttonShowShift);

        panelAdmin.add(Box.createVerticalGlue());

        frame.add(panelAdmin);
        frame.setVisible(true);
    }

    private void addShift() {
        JFrame frameAddShiftAdmin = new MainFrame();
        JPanel panelAdminAddShift = new JPanel();

        panelAdminAddShift.setLayout(new BoxLayout(panelAdminAddShift, BoxLayout.Y_AXIS));
        panelAdminAddShift.setBorder(new EmptyBorder(20, 20, 20, 20));

        JLabel labelEmployeeId = new JLabel("Employee ID");
        labelEmployeeId.setAlignmentX(Component.CENTER_ALIGNMENT);
        labelEmployeeId.setFont(new Font("SansSerif", Font.BOLD, 17));
        panelAdminAddShift.add(labelEmployeeId);

        JTextField fieldEmployeeId = new JTextField(15);
        fieldEmployeeId.setMaximumSize(new Dimension(300, 30));
        panelAdminAddShift.add(fieldEmployeeId);
        panelAdminAddShift.add(Box.createVerticalStrut(15));

        JLabel labelStartTime = new JLabel("Start Time");
        labelStartTime.setAlignmentX(Component.CENTER_ALIGNMENT);
        labelStartTime.setFont(new Font("SansSerif", Font.BOLD, 17));
        panelAdminAddShift.add(labelStartTime);

        JTextField fieldStartTime = new JTextField(15);
        fieldStartTime.setText("HH:mm:ss");
        fieldStartTime.setMaximumSize(new Dimension(300, 30));
        panelAdminAddShift.add(fieldStartTime);
        panelAdminAddShift.add(Box.createVerticalStrut(15));

        JLabel labelEndTime = new JLabel("End Time");
        labelEndTime.setAlignmentX(Component.CENTER_ALIGNMENT);
        labelEndTime.setFont(new Font("SansSerif", Font.BOLD, 17));
        panelAdminAddShift.add(labelEndTime);

        JTextField fieldEndTime = new JTextField(15);
        fieldEndTime.setText("HH:mm:ss");
        fieldEndTime.setMaximumSize(new Dimension(300, 30));
        panelAdminAddShift.add(fieldEndTime);
        panelAdminAddShift.add(Box.createVerticalStrut(15));

        JLabel labelDate = new JLabel("Date");
        labelDate.setAlignmentX(Component.CENTER_ALIGNMENT);
        labelDate.setFont(new Font("SansSerif", Font.BOLD, 17));
        panelAdminAddShift.add(labelDate);

        JTextField fieldDate = new JTextField(15);
        fieldDate.setText("yyyy-MM-dd");
        fieldDate.setMaximumSize(new Dimension(300, 30));
        panelAdminAddShift.add(fieldDate);
        panelAdminAddShift.add(Box.createVerticalStrut(15));

        JButton buttonAddTask = new JButton("Add Shift");
        buttonAddTask.setFont(new Font("SansSerif", Font.BOLD, 14));
        buttonAddTask.setAlignmentX(Component.CENTER_ALIGNMENT);
        buttonAddTask.setFocusPainted(false);
        buttonAddTask.addActionListener(e -> {
            DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("HH:mm:ss");
            DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            String employeeId = fieldEmployeeId.getText();
            String startTimeInput = fieldStartTime.getText();
            LocalTime startTime = LocalTime.parse(startTimeInput, timeFormat);
            String endTimeInput = fieldEndTime.getText();
            LocalTime endTime = LocalTime.parse(endTimeInput, timeFormat);
            String dateInput = fieldDate.getText();
            LocalDate date = LocalDate.parse(dateInput, dateFormat);

            if (shiftController.addShift(employeeId, startTime, endTime, date)) {
                showDialog(frameAddShiftAdmin, "Success", "Add Shift Successful", () -> {
                    frameAddShiftAdmin.dispose();
                    renderShiftViewAdmin();
                });
            } else {
                showDialog(frameAddShiftAdmin, "Error", "Add Shift Error", null);
            }
        });

        panelAdminAddShift.add(buttonAddTask);
        panelAdminAddShift.add(Box.createVerticalStrut(15));
        panelAdminAddShift.add(buttonBack(frameAddShiftAdmin, () -> renderShiftViewAdmin()));
        frameAddShiftAdmin.add(panelAdminAddShift);
    }

    private void viewDailyShift() {
        JFrame frameViewDailyShift = new MainFrame();
        JPanel panelViewDailyShift = new JPanel();

        panelViewDailyShift.setLayout(new BoxLayout(panelViewDailyShift, BoxLayout.Y_AXIS));
        panelViewDailyShift.setBorder(new EmptyBorder(20, 20, 20, 20));

        panelViewDailyShift.add(Box.createVerticalGlue());

        JLabel labelDate = new JLabel("Date");
        labelDate.setAlignmentX(Component.CENTER_ALIGNMENT);
        labelDate.setFont(new Font("SansSerif", Font.BOLD, 17));
        panelViewDailyShift.add(labelDate);

        panelViewDailyShift.add(Box.createVerticalStrut(10));

        JTextField fieldDate = new JTextField(15);
        fieldDate.setMaximumSize(new Dimension(300, 30));
        panelViewDailyShift.add(fieldDate);

        panelViewDailyShift.add(Box.createVerticalStrut(15));
        JButton buttonSearch = new JButton("Search Shift");
        buttonSearch.setAlignmentX(Component.CENTER_ALIGNMENT);
        buttonSearch.addActionListener(e -> {
            DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            String dateInput = fieldDate.getText();
            LocalDate date = LocalDate.parse(dateInput, dateFormat);

            List<Shift> shiftList = shiftController.getDailyAttendanceReports(date);

            if (shiftList.isEmpty()) {
                showDialog(frameViewDailyShift, "Error", "Data Not Found", () -> {
                    frameViewDailyShift.dispose();
                    renderShiftViewAdmin();
                });
            } else {
                String[] columnNames = { "Nama", "Shift ID", "Empployee ID", "Date", "Start Time", "End Time" };
                DefaultTableModel model = new DefaultTableModel(columnNames, 0);

                for (Shift shift : shiftList) {
                    Staff staff = staffController.getStaffByEmployeeId(shift.getStaffId());
                    Object[] rowData = {
                            staff.getNama(),
                            shift.getShiftId(),
                            shift.getStaffId(),
                            shift.getDate(),
                            shift.getStartTime(),
                            shift.getEndTime()
                    };

                    model.addRow(rowData);
                }

                JDialog dialog = new JDialog(frameViewDailyShift, "Data List", true);
                dialog.setSize(700, 400);
                dialog.setLocationRelativeTo(frameViewDailyShift);

                JTable tableData = new JTable(model);
                JScrollPane scrollPane = new JScrollPane(tableData);

                dialog.add(scrollPane);
                dialog.setVisible(true);
                frameViewDailyShift.dispose();
                renderShiftViewAdmin();
            }
        });

        panelViewDailyShift.add(buttonSearch);
        panelViewDailyShift.add(Box.createVerticalStrut(15));
        panelViewDailyShift.add(buttonBack(frameViewDailyShift, () -> renderShiftViewAdmin()));
        panelViewDailyShift.add(Box.createVerticalGlue());
        frameViewDailyShift.add(panelViewDailyShift);
        frameViewDailyShift.setVisible(true);
    }

    private void renderShiftViewStaff() {
        frame = new MainFrame();
        JPanel panelStaff = new JPanel();

        panelStaff.setLayout(new BoxLayout(panelStaff, BoxLayout.Y_AXIS));

        panelStaff.setBorder(new EmptyBorder(40, 40, 40, 40));
        panelStaff.setBackground(Color.WHITE);

        panelStaff.add(Box.createVerticalGlue());

        JLabel title = new JLabel("Shift Management Menu");
        title.setFont(new Font("Poppins", Font.BOLD, 28));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        panelStaff.add(title);

        panelStaff.add(Box.createVerticalStrut(10));

        JLabel titleAdmin = new JLabel("STAFF MENU");
        titleAdmin.setFont(new Font("SansSerif", Font.BOLD, 14));
        titleAdmin.setAlignmentX(Component.CENTER_ALIGNMENT);
        titleAdmin.setForeground(new Color(100, 100, 100));
        panelStaff.add(titleAdmin);

        panelStaff.add(Box.createVerticalStrut(40));

        JButton buttonGetAllShift = new JButton("Get All Shift");
        buttonGetAllShift.setFont(new Font("SansSerif", Font.BOLD, 14));
        buttonGetAllShift.setAlignmentX(Component.CENTER_ALIGNMENT);
        buttonGetAllShift.setFocusPainted(false);
        buttonGetAllShift.setMaximumSize(new Dimension(250, 45));
        buttonGetAllShift.setPreferredSize(new Dimension(250, 45));
        buttonGetAllShift.addActionListener(e -> viewAllShifts());
        panelStaff.add(buttonGetAllShift);

        JButton buttonShiftById = new JButton("Get Shift By Employee ID");
        buttonShiftById.setFont(new Font("SansSerif", Font.BOLD, 14));
        buttonShiftById.setAlignmentX(Component.CENTER_ALIGNMENT);
        buttonShiftById.setFocusPainted(false);
        buttonShiftById.setMaximumSize(new Dimension(250, 45));
        buttonShiftById.setPreferredSize(new Dimension(250, 45));
        buttonShiftById.addActionListener(e -> viewShiftsByEmployeeId());
        panelStaff.add(buttonShiftById);

        JButton buttonAttendance = new JButton("Submit Attendance");
        buttonAttendance.setFont(new Font("SansSerif", Font.BOLD, 14));
        buttonAttendance.setAlignmentX(Component.CENTER_ALIGNMENT);
        buttonAttendance.setFocusPainted(false);
        buttonAttendance.setMaximumSize(new Dimension(250, 45));
        buttonAttendance.setPreferredSize(new Dimension(250, 45));
        buttonAttendance.addActionListener(e -> submitAttendance());
        panelStaff.add(buttonAttendance);

        panelStaff.add(Box.createVerticalGlue());

        frame.add(panelStaff);
        frame.setVisible(true);
    }

    private void viewAllShifts() {
        JFrame frameViewShift = new MainFrame();
        JPanel panelViewShift = new JPanel();

        panelViewShift.setLayout(new BoxLayout(panelViewShift, BoxLayout.Y_AXIS));
        panelViewShift.setBorder(new EmptyBorder(20, 20, 20, 20));

        panelViewShift.add(Box.createVerticalGlue());

        List<Shift> shiftList = shiftController.getAllShifts();

        if (shiftList.isEmpty()) {
            showDialog(frameViewShift, "Error", "Data Not Found", () -> {
                frameViewShift.dispose();
                renderShiftViewAdmin();
            });
        } else {
            String[] columnNames = { "Nama", "Shift ID", "Empployee ID", "Date", "Start Time", "End Time" };
            DefaultTableModel model = new DefaultTableModel(columnNames, 0);

            for (Shift shift : shiftList) {
                Staff staff = staffController.getStaffByEmployeeId(shift.getStaffId());
                Object[] rowData = {
                        staff.getNama(),
                        shift.getShiftId(),
                        shift.getStaffId(),
                        shift.getDate(),
                        shift.getStartTime(),
                        shift.getEndTime()
                };

                model.addRow(rowData);
            }

            JTable tableData = new JTable(model);
            JScrollPane scrollPane = new JScrollPane(tableData);

            panelViewShift.add(scrollPane);
            panelViewShift.add(Box.createVerticalStrut(20));

            panelViewShift.add( buttonBack(frameViewShift, () -> renderShiftViewStaff()));
        }
        panelViewShift.add(Box.createVerticalGlue());
        frameViewShift.add(panelViewShift);
        frameViewShift.setVisible(true);
    }

    private void viewShiftsByEmployeeId() {
        JFrame frameViewShiftById = new MainFrame();
        JPanel panelViewShiftById = new JPanel();

        panelViewShiftById.setLayout(new BoxLayout(panelViewShiftById, BoxLayout.Y_AXIS));
        panelViewShiftById.setBorder(new EmptyBorder(20, 20, 20, 20));

        panelViewShiftById.add(Box.createVerticalGlue());

        JLabel labelEmployeeId = new JLabel("Employee ID");
        labelEmployeeId.setAlignmentX(Component.CENTER_ALIGNMENT);
        labelEmployeeId.setFont(new Font("SansSerif", Font.BOLD, 17));
        panelViewShiftById.add(labelEmployeeId);

        JTextField fieldEmployeeId = new JTextField(15);
        fieldEmployeeId.setMaximumSize(new Dimension(300, 30));
        panelViewShiftById.add(fieldEmployeeId);
        panelViewShiftById.add(Box.createVerticalStrut(15));

        panelViewShiftById.add(Box.createVerticalStrut(15));
        JButton buttonSearch = new JButton("Search Shift");
        buttonSearch.setAlignmentX(Component.CENTER_ALIGNMENT);
        buttonSearch.addActionListener(e -> {
            String employeeId = fieldEmployeeId.getText();

            List<Shift> shiftList = shiftController.getShiftsByEmployeeId(employeeId);
            if (shiftList.isEmpty()) {
                showDialog(frameViewShiftById, "Error", "Data Not Found", () -> {
                    frameViewShiftById.dispose();
                    renderShiftViewAdmin();
                });
            } else {
                String[] columnNames = { "Nama", "Shift ID", "Empployee ID", "Date", "Start Time", "End Time" };
                DefaultTableModel model = new DefaultTableModel(columnNames, 0);
    
                for (Shift shift : shiftList) {
                    Staff staff = staffController.getStaffByEmployeeId(shift.getStaffId());
                    Object[] rowData = {
                            staff.getNama(),
                            shift.getShiftId(),
                            shift.getStaffId(),
                            shift.getDate(),
                            shift.getStartTime(),
                            shift.getEndTime()
                    };
    
                    model.addRow(rowData);
                }
                panelViewShiftById.add(Box.createVerticalStrut(20));
                
                JDialog dialog = new JDialog(frameViewShiftById, "Data List", true);
                dialog.setSize(700, 400);
                dialog.setLocationRelativeTo(frameViewShiftById);

                JTable tableData = new JTable(model);
                JScrollPane scrollPane = new JScrollPane(tableData);

                dialog.add(scrollPane);
                dialog.setVisible(true);
                frameViewShiftById.dispose();
                renderShiftViewStaff(); 
            }

        });

        panelViewShiftById.add(buttonSearch);
        panelViewShiftById.add(Box.createVerticalStrut(15));
        panelViewShiftById.add(buttonBack(frameViewShiftById, () -> renderShiftViewStaff()));
        panelViewShiftById.add(Box.createVerticalGlue());
        frameViewShiftById.add(panelViewShiftById);
        frameViewShiftById.setVisible(true);
    }

    private void submitAttendance() {
        JFrame frameAttendance = new MainFrame();
        JPanel panelAttendance = new JPanel();

        panelAttendance.setLayout(new BoxLayout(panelAttendance, BoxLayout.Y_AXIS));
        panelAttendance.setBorder(new EmptyBorder(20, 20, 20, 20));

        panelAttendance.add(Box.createVerticalGlue());

        JLabel labelEmployeeId = new JLabel("Employee ID");
        labelEmployeeId.setAlignmentX(Component.CENTER_ALIGNMENT);
        labelEmployeeId.setFont(new Font("SansSerif", Font.BOLD, 17));
        panelAttendance.add(labelEmployeeId);

        JTextField fieldEmployeeId = new JTextField(15);
        fieldEmployeeId.setMaximumSize(new Dimension(300, 30));
        panelAttendance.add(fieldEmployeeId);

        panelAttendance.add(Box.createVerticalStrut(15));

        JLabel labelShiftId = new JLabel("Shift ID");
        labelShiftId.setAlignmentX(Component.CENTER_ALIGNMENT);
        labelShiftId.setFont(new Font("SansSerif", Font.BOLD, 17));
        panelAttendance.add(labelShiftId);

        JTextField fieldShiftId = new JTextField(15);
        fieldShiftId.setMaximumSize(new Dimension(300, 30));
        panelAttendance.add(fieldShiftId);
        panelAttendance.add(Box.createVerticalStrut(15));

        JLabel labelIsPresent= new JLabel("Is Present");
        labelIsPresent.setAlignmentX(Component.CENTER_ALIGNMENT);
        labelIsPresent.setFont(new Font("SansSerif", Font.BOLD, 17));
        panelAttendance.add(labelIsPresent);

        Boolean[] options = {true, false};
        JComboBox<Boolean> comboxIsPresent = new JComboBox<>(options);
        comboxIsPresent.setMaximumSize(new Dimension(300, 30));
        panelAttendance.add(comboxIsPresent);

        panelAttendance.add(Box.createVerticalStrut(15));
        JButton buttonSubmit = new JButton("Submit");
        buttonSubmit.setAlignmentX(Component.CENTER_ALIGNMENT);
        buttonSubmit.addActionListener(e -> {
            String employeeId = fieldEmployeeId.getText();
            String shiftId = fieldShiftId.getText();
            Boolean isPresentValue = (Boolean) comboxIsPresent.getSelectedItem();

            if (shiftController.submitAttendance(employeeId, shiftId, isPresentValue)) {
                showDialog(frameAttendance, "Success", "Update Submit Attendance Success", () -> {
                    frameAttendance.dispose();
                    renderShiftViewStaff();
                });
            } else {
                showDialog(frameAttendance, "Error", "Data Not Found", () -> {
                    frameAttendance.dispose();
                    renderShiftViewStaff();
                });
            }

        });

        panelAttendance.add(buttonSubmit);
        panelAttendance.add(Box.createVerticalStrut(20));
        panelAttendance.add(buttonBack(frameAttendance, () -> renderShiftViewStaff()));
        panelAttendance.add(Box.createVerticalGlue());
        frameAttendance.add(panelAttendance);
        frameAttendance.setVisible(true);
    }

    private JButton buttonBack(JFrame mainFrame, Runnable nextAction){
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
