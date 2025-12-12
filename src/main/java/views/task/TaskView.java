package views.task;

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

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import controller.TaskController;
import models.Task;
import models.enums.TaskStatus;
import views.MainFrame;

public class TaskView {
    MainFrame frame;
    TaskController taskController;

    public TaskView() {
        this.taskController = new TaskController();
        //renderTaskViewAdmin();
        renderTaskViewStaff();
    }

    private void renderTaskViewAdmin() {
        frame = new MainFrame();

        JPanel panelAdmin = new JPanel();
        panelAdmin.setLayout(new BoxLayout(panelAdmin, BoxLayout.Y_AXIS));

        panelAdmin.setBorder(new EmptyBorder(40, 40, 40, 40));
        panelAdmin.setBackground(Color.WHITE);

        panelAdmin.add(Box.createVerticalGlue());

        JLabel title = new JLabel("Task Management Menu");
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
        buttonAddTask.addActionListener(e -> addTask());
        panelAdmin.add(buttonAddTask);

        panelAdmin.add(Box.createVerticalGlue());

        frame.add(panelAdmin);
        frame.setVisible(true);
    }

    private void addTask() {
        JFrame frameAddTaskAdmin = new MainFrame();
        JPanel panelAdminAddTask = new JPanel();

        panelAdminAddTask.setLayout(new BoxLayout(panelAdminAddTask, BoxLayout.Y_AXIS));
        panelAdminAddTask.setBorder(new EmptyBorder(20, 20, 20, 20));

        JLabel labelShiftId = new JLabel("Shift ID");
        labelShiftId.setAlignmentX(Component.CENTER_ALIGNMENT);
        labelShiftId.setFont(new Font("SansSerif", Font.BOLD, 17));
        panelAdminAddTask.add(labelShiftId);

        JTextField fieldShiftId = new JTextField(15);
        fieldShiftId.setMaximumSize(new Dimension(300, 30));
        panelAdminAddTask.add(fieldShiftId);
        panelAdminAddTask.add(Box.createVerticalStrut(15));

        JLabel labelTitle = new JLabel("Title");
        labelTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        labelTitle.setFont(new Font("SansSerif", Font.BOLD, 17));
        panelAdminAddTask.add(labelTitle);

        JTextField fieldTitle = new JTextField(15);
        fieldTitle.setMaximumSize(new Dimension(300, 30));
        panelAdminAddTask.add(fieldTitle);
        panelAdminAddTask.add(Box.createVerticalStrut(15));

        JLabel labelDescription = new JLabel("Description");
        labelDescription.setAlignmentX(Component.CENTER_ALIGNMENT);
        labelDescription.setFont(new Font("SansSerif", Font.BOLD, 17));
        panelAdminAddTask.add(labelDescription);

        JTextField fieldDescription = new JTextField(15);
        fieldDescription.setMaximumSize(new Dimension(300, 30));
        panelAdminAddTask.add(fieldDescription);
        panelAdminAddTask.add(Box.createVerticalStrut(15));

        JLabel labelDeadline = new JLabel("Deadline");
        labelDeadline.setAlignmentX(Component.CENTER_ALIGNMENT);
        labelDeadline.setFont(new Font("SansSerif", Font.BOLD, 17));
        panelAdminAddTask.add(labelDeadline);

        JTextField fieldDeadline = new JTextField(15);
        fieldDeadline.setText("yyyy-MM-dd HH:mm:ss");
        fieldDeadline.setMaximumSize(new Dimension(300, 30));
        panelAdminAddTask.add(fieldDeadline);

        JButton buttonAddTask = new JButton("Add");
        buttonAddTask.setFont(new Font("SansSerif", Font.BOLD, 14));
        buttonAddTask.setAlignmentX(Component.CENTER_ALIGNMENT);
        buttonAddTask.setFocusPainted(false);
        buttonAddTask.setMaximumSize(new Dimension(140, 25));
        buttonAddTask.addActionListener(e -> {
            String shiftId = fieldShiftId.getText();
            String title = fieldTitle.getText();
            String description = fieldDescription.getText();
            String deadlineInput = fieldDeadline.getText();
            DateTimeFormatter formatDateTime = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            LocalDateTime deadline = LocalDateTime.parse(deadlineInput, formatDateTime);

            if (taskController.addTaskAdmin(shiftId, title, description, deadline)) {
                showDialog(frameAddTaskAdmin, "Success", "Add Task Successful", () ->{
                    frameAddTaskAdmin.dispose();
                    renderTaskViewAdmin();
                });

            } else {
                showDialog(frameAddTaskAdmin, "Error", "Add Task Error", null);
            }
        });

        panelAdminAddTask.add(Box.createVerticalStrut(15));
        panelAdminAddTask.add(buttonAddTask);
        panelAdminAddTask.add(Box.createVerticalStrut(15));
        panelAdminAddTask.add(buttonBack(frameAddTaskAdmin, () -> renderTaskViewAdmin()));

        frameAddTaskAdmin.add(panelAdminAddTask);
    }

    private void renderTaskViewStaff(){
        frame = new MainFrame();

        JPanel panelStaff = new JPanel();
        panelStaff.setLayout(new BoxLayout(panelStaff, BoxLayout.Y_AXIS));

        panelStaff.setBorder(new EmptyBorder(40, 40, 40, 40));
        panelStaff.setBackground(Color.WHITE);

        panelStaff.add(Box.createVerticalGlue());

        JLabel title = new JLabel("Task Management Menu");
        title.setFont(new Font("Poppins", Font.BOLD, 28));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        panelStaff.add(title);

        panelStaff.add(Box.createVerticalStrut(10));

        JLabel titleStaff = new JLabel("STAFF MENU");
        titleStaff.setFont(new Font("SansSerif", Font.BOLD, 14));
        titleStaff.setAlignmentX(Component.CENTER_ALIGNMENT);
        titleStaff.setForeground(new Color(100, 100, 100));
        panelStaff.add(titleStaff);

        panelStaff.add(Box.createVerticalStrut(40));

        JButton buttonViewTask = new JButton("View Task by Shift");
        buttonViewTask.setFont(new Font("SansSerif", Font.BOLD, 14));
        buttonViewTask.setAlignmentX(Component.CENTER_ALIGNMENT);
        buttonViewTask.setFocusPainted(false);
        buttonViewTask.setMaximumSize(new Dimension(250, 45));
        buttonViewTask.setPreferredSize(new Dimension(250, 45));
        buttonViewTask.addActionListener(e -> viewTaskByShiftId());
        panelStaff.add(buttonViewTask);

        JButton buttonDoSpesificTask = new JButton("Do Spesific Task");
        buttonDoSpesificTask.setFont(new Font("SansSerif", Font.BOLD, 14));
        buttonDoSpesificTask.setAlignmentX(Component.CENTER_ALIGNMENT);
        buttonDoSpesificTask.setFocusPainted(false);
        buttonDoSpesificTask.setMaximumSize(new Dimension(250, 45));
        buttonDoSpesificTask.setPreferredSize(new Dimension(250, 45));
        buttonDoSpesificTask.addActionListener(e -> doSpesificTask());
        panelStaff.add(buttonDoSpesificTask);

        JButton buttonDoAllTask = new JButton("Do All Task in Shift");
        buttonDoAllTask.setFont(new Font("SansSerif", Font.BOLD, 14));
        buttonDoAllTask.setAlignmentX(Component.CENTER_ALIGNMENT);
        buttonDoAllTask.setFocusPainted(false);
        buttonDoAllTask.setMaximumSize(new Dimension(250, 45));
        buttonDoAllTask.setPreferredSize(new Dimension(250, 45));
        buttonDoAllTask.addActionListener(e -> doAllTaskInAShift());
        panelStaff.add(buttonDoAllTask);

        JButton buttonUpdateTaskStatus = new JButton("Update Task Status");
        buttonUpdateTaskStatus.setFont(new Font("SansSerif", Font.BOLD, 14));
        buttonUpdateTaskStatus.setAlignmentX(Component.CENTER_ALIGNMENT);
        buttonUpdateTaskStatus.setFocusPainted(false);
        buttonUpdateTaskStatus.setMaximumSize(new Dimension(250, 45));
        buttonUpdateTaskStatus.setPreferredSize(new Dimension(250, 45));
        buttonUpdateTaskStatus.addActionListener(e -> updateTaskStatus());
        panelStaff.add(buttonUpdateTaskStatus);

        panelStaff.add(Box.createVerticalGlue());

        frame.add(panelStaff);
        frame.setVisible(true);
    }

    private void viewTaskByShiftId(){
        JFrame frameViewTask = new MainFrame();
        JPanel panelViewTask = new JPanel();

        panelViewTask.setLayout(new BoxLayout(panelViewTask, BoxLayout.Y_AXIS));
        panelViewTask.setBorder(new EmptyBorder(20, 20, 20, 20));

        panelViewTask.add(Box.createVerticalGlue());

        JLabel labelShiftId = new JLabel("Shift ID");
        labelShiftId.setAlignmentX(Component.CENTER_ALIGNMENT);
        labelShiftId.setFont(new Font("SansSerif", Font.BOLD, 17));
        panelViewTask.add(labelShiftId);

        panelViewTask.add(Box.createVerticalStrut(10));

        JTextField fieldShiftId = new JTextField(15);
        fieldShiftId.setMaximumSize(new Dimension(300, 30));
        panelViewTask.add(fieldShiftId);

        panelViewTask.add(Box.createVerticalStrut(15));

        JButton buttonSearch = new JButton("Search Data");
        buttonSearch.setAlignmentX(Component.CENTER_ALIGNMENT);
        buttonSearch.addActionListener(e -> {
            String shiftId = fieldShiftId.getText();

            List<Task> taskList = taskController.getTasksByShift(shiftId);
            
            if (taskList.isEmpty()) {
                showDialog(frameViewTask, "Error", "Data Not Found", () -> {
                    frameViewTask.dispose();
                    renderTaskViewStaff();
                });
            } else {
                String[] columnNames = {"Task ID", "Title", "Description", "Status", "Deadline", "Completed At"};
                DefaultTableModel model = new DefaultTableModel(columnNames, 0);
            
                for (Task task : taskList) {
                    Object[] rowData = {
                        task.getTask_id(),
                        task.getTitle(),
                        task.getDescription(),
                        task.isStatus(),
                        task.getDeadline(),
                        task.getCompletedAt()
                    };
                    model.addRow(rowData);
                }

                JDialog dialog = new JDialog(frameViewTask, "Data List", true);
                dialog.setSize(700, 400);
                dialog.setLocationRelativeTo(frameViewTask);

                JTable tableData = new JTable(model);
                JScrollPane scrollPane = new JScrollPane(tableData);
                
                dialog.add(scrollPane);
                dialog.setVisible(true);
                frameViewTask.dispose();
                renderTaskViewStaff();
            }
        });

        panelViewTask.add(buttonSearch);
        panelViewTask.add(Box.createVerticalStrut(15));
        panelViewTask.add(buttonBack(frameViewTask, () -> renderTaskViewStaff()));
        panelViewTask.add(Box.createVerticalGlue());
        frameViewTask.add(panelViewTask);
        frameViewTask.setVisible(true);

    }

    private void doSpesificTask(){
        JFrame frameDoSpesificTask = new MainFrame();
        JPanel panelDoSpesificTask = new JPanel();

        panelDoSpesificTask.setLayout(new BoxLayout(panelDoSpesificTask, BoxLayout.Y_AXIS));
        panelDoSpesificTask.setBorder(new EmptyBorder(20, 20, 20, 20));

        panelDoSpesificTask.add(Box.createVerticalGlue());

        JLabel labelTaskId = new JLabel("Task ID");
        labelTaskId.setAlignmentX(Component.CENTER_ALIGNMENT);
        labelTaskId.setFont(new Font("SansSerif", Font.BOLD, 17));
        panelDoSpesificTask.add(labelTaskId);

        panelDoSpesificTask.add(Box.createVerticalStrut(10));

        JTextField fieldTasktId = new JTextField(15);
        fieldTasktId.setMaximumSize(new Dimension(300, 30));
        panelDoSpesificTask.add(fieldTasktId);

        panelDoSpesificTask.add(Box.createVerticalStrut(15));

        JButton buttonSearch = new JButton("Update Task Status");
        buttonSearch.setAlignmentX(Component.CENTER_ALIGNMENT); 
        panelDoSpesificTask.add(buttonSearch);
        buttonSearch.addActionListener(e -> {
            String taskId = fieldTasktId.getText();
            if (taskController.doSpesificTask(taskId)) {
                showDialog(frameDoSpesificTask, "Success", "Task Completed", () -> {
                    frameDoSpesificTask.dispose();
                    renderTaskViewStaff();
                });
            } else {
                showDialog(frameDoSpesificTask, "Error", "Task Not Found", () -> {
                    frameDoSpesificTask.dispose();
                    renderTaskViewStaff();
                });
            }

        });
        panelDoSpesificTask.add(Box.createVerticalStrut(15));
        panelDoSpesificTask.add(buttonBack(frameDoSpesificTask, () -> renderTaskViewStaff()));
        panelDoSpesificTask.add(Box.createVerticalGlue());
        frameDoSpesificTask.add(panelDoSpesificTask);
        frameDoSpesificTask.setVisible(true);
    }

    private void doAllTaskInAShift(){
        JFrame frameDoAllTask = new MainFrame();
        JPanel panelDoAllTask = new JPanel();

        panelDoAllTask.setLayout(new BoxLayout(panelDoAllTask, BoxLayout.Y_AXIS));
        panelDoAllTask.setBorder(new EmptyBorder(20, 20, 20, 20));

        panelDoAllTask.add(Box.createVerticalGlue());

        JLabel labelShiftId = new JLabel("Shift ID");
        labelShiftId.setAlignmentX(Component.CENTER_ALIGNMENT);
        labelShiftId.setFont(new Font("SansSerif", Font.BOLD, 17));
        panelDoAllTask.add(labelShiftId);

        panelDoAllTask.add(Box.createVerticalStrut(10));

        JTextField fieldShiftId = new JTextField(15);
        fieldShiftId.setMaximumSize(new Dimension(300, 30));
        panelDoAllTask.add(fieldShiftId);

        panelDoAllTask.add(Box.createVerticalStrut(15));

        JButton buttonSearch = new JButton("Search Data");
        buttonSearch.setAlignmentX(Component.CENTER_ALIGNMENT);
        panelDoAllTask.add(buttonSearch);
        
        buttonSearch.addActionListener(e -> {
            String shiftId = fieldShiftId.getText();

            
            if (taskController.doAllTaskInAShift(shiftId)) {
                List<Task> taskList = taskController.getTasksByShift(shiftId);
                String[] columnNames = {"Task ID", "Title", "Description", "Status", "Deadline", "Completed At"};
                DefaultTableModel model = new DefaultTableModel(columnNames, 0);
            
                for (Task task : taskList) {
                    Object[] rowData = {
                        task.getTask_id(),
                        task.getTitle(),
                        task.getDescription(),
                        task.isStatus(),
                        task.getDeadline(),
                        task.getCompletedAt()
                    };
                    model.addRow(rowData);
                }
                JDialog dialog = new JDialog(frameDoAllTask, "Data List", true);
                dialog.setSize(700, 400);
                dialog.setLocationRelativeTo(frameDoAllTask);

                JTable tableData = new JTable(model);
                JScrollPane scrollPane = new JScrollPane(tableData);
                
                dialog.add(scrollPane);
                dialog.setVisible(true);
                
                frameDoAllTask.dispose();
                renderTaskViewStaff();
            } else {
                showDialog(frameDoAllTask, "Error", "Data Not Found", () -> {
                    frameDoAllTask.dispose();
                    renderTaskViewStaff();
                });
                
            }
        });
        panelDoAllTask.add(Box.createVerticalStrut(15));
        panelDoAllTask.add(buttonBack(frameDoAllTask, () -> renderTaskViewStaff()));
        panelDoAllTask.add(Box.createVerticalGlue());
        frameDoAllTask.add(panelDoAllTask);
    }

    private void updateTaskStatus(){
        JFrame frameUpdateTaskStatus = new MainFrame();
        JPanel panelUpdateTaskStatus = new JPanel();

        panelUpdateTaskStatus.setLayout(new BoxLayout(panelUpdateTaskStatus, BoxLayout.Y_AXIS));
        panelUpdateTaskStatus.setBorder(new EmptyBorder(20, 20, 20, 20));

        panelUpdateTaskStatus.add(Box.createVerticalGlue());

        JLabel labelTaskId = new JLabel("Task ID");
        labelTaskId.setAlignmentX(Component.CENTER_ALIGNMENT);
        labelTaskId.setFont(new Font("SansSerif", Font.BOLD, 17));
        panelUpdateTaskStatus.add(labelTaskId);

        panelUpdateTaskStatus.add(Box.createVerticalStrut(10));

        JTextField fieldTasktId = new JTextField(15);
        fieldTasktId.setMaximumSize(new Dimension(300, 30));
        panelUpdateTaskStatus.add(fieldTasktId);

        panelUpdateTaskStatus.add(Box.createVerticalStrut(15));

        JComboBox<TaskStatus> listTaskStatus = new JComboBox<>(TaskStatus.values());
        listTaskStatus.setMaximumSize(new Dimension(300, 30));
        panelUpdateTaskStatus.add(listTaskStatus);

        panelUpdateTaskStatus.add(Box.createVerticalStrut(15));

        JButton buttonSearch = new JButton("Update Task Status");
        buttonSearch.setAlignmentX(Component.CENTER_ALIGNMENT); 
        panelUpdateTaskStatus.add(buttonSearch);
        buttonSearch.addActionListener(e -> {
            String taskId = fieldTasktId.getText();
            TaskStatus selectedItemStatus = (TaskStatus) listTaskStatus.getSelectedItem();

            if (taskController.updateTaskStatus(taskId, selectedItemStatus)) {
                showDialog(frameUpdateTaskStatus, "Success", "Update Task Status Completed", () -> {
                    frameUpdateTaskStatus.dispose();
                    renderTaskViewStaff();
                });
            } else {
                showDialog(frameUpdateTaskStatus, "Error", "Failed Update", () -> {
                    frameUpdateTaskStatus.dispose();
                    renderTaskViewStaff();
                });
            }

        });
        panelUpdateTaskStatus.add(Box.createVerticalStrut(15));
        panelUpdateTaskStatus.add(buttonBack(frameUpdateTaskStatus, () -> renderTaskViewStaff()));
        panelUpdateTaskStatus.add(Box.createVerticalGlue());
        frameUpdateTaskStatus.add(panelUpdateTaskStatus);
        frameUpdateTaskStatus.setVisible(true);

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

    private void showDialog(JFrame mainFrame, String title, String message, Runnable nextAction){
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
