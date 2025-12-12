package repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import models.Booking;
import models.Staff;
import models.Task;
import models.enums.Department;
import models.enums.TaskStatus;
import utils.Database;
import utils.GenerateUUID;
import moduls.GlobalVariables;

public class TaskRepository {
    public static Connection conn = Database.connect();
    StaffRepository staffRepository = new StaffRepository();

    //add task
    public boolean addTask(String shiftId, String title, String descriptionTask, LocalDateTime deadline){
        String sql = "INSERT INTO task (taskid, shiftid, bookingid, title, description, status, deadline, completedat) VALUES (?, ?, NULL, ?, ?, 'ASSIGNED', ?, NULL)"; 
        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, GenerateUUID.generateUUID());
            pstmt.setString(2, shiftId);
            pstmt.setString(3, title);
            pstmt.setString(4, descriptionTask);
            pstmt.setTimestamp(5,Timestamp.valueOf(deadline));
            
            pstmt.executeUpdate();
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    //show task per shift
    public List<Task> findTasksByShiftId(String shiftId){
        List<Task> listTaskByShiftId = new ArrayList<>();
        String sql = "SELECT task.*, shift.shiftid FROM task INNER JOIN shift ON shift.shiftid = task.shiftid WHERE task.shiftid = ?";
        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, shiftId);
            ResultSet resultQuery = pstmt.executeQuery();
            while (resultQuery.next()) {
                String taskId = resultQuery.getString("taskid");
                String titleTask = resultQuery.getString("title");
                String description = resultQuery.getString("description");
                String statusString = resultQuery.getString("status");
                TaskStatus taskStatus = TaskStatus.valueOf(statusString); 
                Timestamp deadlineRaw = resultQuery.getTimestamp("deadline");
                LocalDateTime deadline = null;
                if (deadlineRaw != null) {
                    deadline = deadlineRaw.toLocalDateTime();
                }
  
                Timestamp completedAtRaw = resultQuery.getTimestamp("completedat");              
                LocalDateTime completedAt = null;
                if (completedAtRaw != null){
                    completedAt = completedAtRaw.toLocalDateTime();
                }

                Task task = new Task(taskId, titleTask, description, taskStatus, deadline, completedAt);
                listTaskByShiftId.add(task);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listTaskByShiftId;
    }

    //update task status
    public boolean updateTaskStatus(String taskId, TaskStatus status){
        try {
            if (status.equals(TaskStatus.COMPLETED) || status.equals(TaskStatus.APPROVED)) {
                String sql = "UPDATE task SET status = cast(? as taskstatus_type), completedat = ? WHERE taskid = ?";
                PreparedStatement pstmt = conn.prepareStatement(sql);
                pstmt.setString(1, status.name());
                pstmt.setTimestamp(2, Timestamp.valueOf(LocalDateTime.now()));
                pstmt.setString(3, taskId);
                pstmt.executeUpdate();
                return true;
            } else {
                String sql = "UPDATE task SET status = cast(? as taskstatus_type), completedat = ? WHERE taskid = ?";
                PreparedStatement pstmt = conn.prepareStatement(sql);
                pstmt.setString(1, status.name());
                pstmt.setNull(2, java.sql.Types.TIMESTAMP);
                pstmt.setString(3, taskId);
                pstmt.executeUpdate();
                return true;
            } 
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return false;
    }

    //get staff 
    public List<Staff> getStaff(Department department){
        String sqlGetStaffId = "SELECT st.*, t.status FROM task t INNER JOIN shift sh ON sh.shiftid = t.shiftid INNER JOIN staff st ON st.employeeid = sh.employeeid WHERE t.status != 'ON_PROGRESS' AND t.status != 'ASSIGNED' AND st.department = cast(? as department_type)";
        List<Staff> availableStaff = new ArrayList<>();

        try {
            PreparedStatement pstmtGetStaff = conn.prepareStatement(sqlGetStaffId);
            pstmtGetStaff.setString(1, department.name());
            ResultSet resultQuery = pstmtGetStaff.executeQuery();
            while (resultQuery.next()) {
                availableStaff.add(staffRepository.findStaffByStaffId(resultQuery.getString("employeeid")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return availableStaff;
        
    }

    //add extraservice
    public boolean addExtraSevices(String title, String description, String bookingID, LocalDateTime deadline, Department department, double price){
        String sqlInsert = "INSERT INTO task(taskid, shiftid, bookingid, title, description, status, deadline, completedat, price) VALUES (?, ?, ?, ?,?, 'ASSIGNED', ?, NULL, ?)";
        Random random = new Random();
        try {
            List<Staff> listStaffDepartment = getStaff(department);
            Staff chosenStaff = listStaffDepartment.get(random.nextInt(listStaffDepartment.size()));

            PreparedStatement pstmtInsert = conn.prepareStatement(sqlInsert);
            pstmtInsert.setString(1, GenerateUUID.generateUUID());
            pstmtInsert.setString(2, chosenStaff.getEmployeeID()); // ini blm shiftidnya
            pstmtInsert.setString(3, bookingID); 
            pstmtInsert.setString(4, title);
            pstmtInsert.setString(5, description);
            pstmtInsert.setTimestamp(6, Timestamp.valueOf(deadline));
            pstmtInsert.setDouble(7, price);

            pstmtInsert.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        //cara panggil function generate uuid 
        // String uuid = GenerateUUID.generateUUID();
        
        TaskRepository cobaTask = new TaskRepository();

        // System.out.println("Masukkan shift id: ");
        // String shiftIdInput = sc.nextLine();
        // List<Task> listTask = cobaTask.findTasksByShiftId(shiftIdInput);
        // for (Task t : listTask) {
        //     System.out.println(t.getTask_id() + " - " + t.getTitle() + " - " + t.getDescription() + " - " + t.isStatus() + " - " + t.getDeadline() + " - " + t.getCompletedAt());
        // }

        // System.out.println("Masukkan shift id: ");
        // String employeeIdForTask = sc.nextLine();

        // List<Task> listTaskPerStaff = cobaTask.findTasksByShiftId(employeeIdForTask);
        // for (Task t : listTaskPerStaff) {
        //     System.out.println(t.getTask_id() + " - " + t.getTitle() + " - " + t.getDescription() + " - " + t.isStatus() + " - " + t.getDeadline() + " - " + t.getCompletedAt());
        // }

        // System.out.println("masukkan task id: ");
        // String taskIdInput = sc.nextLine();
        // System.out.println("Update status (completed, cancelled, on progress): ");
        // String inputStatus = sc.nextLine();
        // TaskStatus taskStatus = null;
        // switch (inputStatus) {
        //     case "completed":
        //         taskStatus = TaskStatus.COMPLETED;
        //         break;
        //     case "cancelled":
        //         taskStatus = TaskStatus.CANCELLED;
        //         break;
        //     case "on progress":
        //         taskStatus = TaskStatus.ON_PROGRESS;
        //         break;
        //     default:
        //         break;
        // }
        // if (cobaTask.updateTaskStatus(taskIdInput, taskStatus)) {
        //     System.out.println("update berhasil");
        // } else {
        //     System.out.println("update gagal");
        // }

        // System.out.println("Masukan shift id: ");
        // String shiftIDAdd = sc.nextLine();
        // System.out.println("Masukkan title: ");
        // String tittleAdd = sc.nextLine();
        // System.out.println("Masukkan description: ");
        // String descAdd = sc.nextLine();
        // System.out.println("Masukkan deadline (yyy-mm-dd hh:mm:ss): ");
        // String deadlineInput = sc.nextLine();
        // DateTimeFormatter formatDateTime = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        // LocalDateTime deadline = LocalDateTime.parse(deadlineInput, formatDateTime);
        // if (cobaTask.addTask(shiftIDAdd, tittleAdd, descAdd, deadline)) {
        //     System.out.println("berhasil");
        // } else {
        //     System.out.println("gagal");
        // }   

    //     System.out.print("Masukkan department: ");
    //     String inputDepart = sc.nextLine();
    //     Department department = null;
    //     switch (inputDepart) {
    //         case "cleaning":
    //             department = Department.CLEANING;
    //             break;
    //         case "food":
    //             department = Department.FOOD;
    //             break;
    //         case "management":
    //             department = Department.MANAGEMENT;
    //             break;
        
    //         default:
    //             break;
    //     }
    //     List<Staff> getStaffs = cobaTask.getStaff(department);
    //     for (Staff st : getStaffs) {
    //         System.out.println(st.getNama() + "\n" + st.getDepartment() + "\n" + st.getAddress());
    //     }
    }
}
