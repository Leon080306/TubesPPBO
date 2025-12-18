package repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import models.ExtraServices;
import models.Staff;
import models.Task;
import models.enums.Department;
import models.enums.RoomType;
import models.enums.TaskStatus;
import utils.Database;
import utils.GenerateUUID;

public class TaskRepository {
    public static Connection conn = Database.connect();
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
            return pstmt.executeUpdate() > 0;

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
                return pstmt.executeUpdate() > 0;
            } else {
                String sql = "UPDATE task SET status = cast(? as taskstatus_type), completedat = ? WHERE taskid = ?";
                PreparedStatement pstmt = conn.prepareStatement(sql);
                pstmt.setString(1, status.name());
                pstmt.setNull(2, java.sql.Types.TIMESTAMP);
                pstmt.setString(3, taskId);
                return pstmt.executeUpdate() > 0;
            } 
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return false;
    }

    //get staff 
    public List<Staff> getStaffbyDepartment(Department department){
        String sqlGetStaffId = "SELECT st.*, t.status FROM task t INNER JOIN shift sh ON sh.shiftid = t.shiftid INNER JOIN staff st ON st.employeeid = sh.employeeid WHERE t.status != 'ON_PROGRESS' AND t.status != 'ASSIGNED' AND st.department = cast(? as department_type)";
        List<Staff> availableStaff = new ArrayList<>();

        try {
            PreparedStatement pstmtGetStaff = conn.prepareStatement(sqlGetStaffId);
            pstmtGetStaff.setString(1, department.name());
            ResultSet resultQuery = pstmtGetStaff.executeQuery();
            while (resultQuery.next()) {
                availableStaff.add(StaffRepository.findStaffByStaffId(resultQuery.getString("employeeid")));
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
            List<Staff> listStaffDepartment = getStaffbyDepartment(department);
            Staff chosenStaff = listStaffDepartment.get(random.nextInt(listStaffDepartment.size()));

            PreparedStatement pstmtInsert = conn.prepareStatement(sqlInsert);
            pstmtInsert.setString(1, GenerateUUID.generateUUID());
            pstmtInsert.setString(2, chosenStaff.getEmployeeID()); 
            pstmtInsert.setString(3, bookingID); 
            pstmtInsert.setString(4, title);
            pstmtInsert.setString(5, description);
            pstmtInsert.setTimestamp(6, Timestamp.valueOf(deadline));
            pstmtInsert.setDouble(7, price);
            return pstmtInsert.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    //get extra service by bookingid
    public static List<ExtraServices> getExtraServicesByBookingId(String bookingId) {
        List<ExtraServices> services = new ArrayList<>();
        String sql = "SELECT * FROM task WHERE bookingid = ? AND price IS NOT NULL";
        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, bookingId);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                if (rs.getString("taskid") == null) continue;

                Timestamp deadlineRaw = rs.getTimestamp("deadline");
                LocalDateTime deadline = (deadlineRaw != null) ? deadlineRaw.toLocalDateTime() : null;

                Timestamp completedAtRaw = rs.getTimestamp("completedat");
                LocalDateTime completedAt = (completedAtRaw != null) ? completedAtRaw.toLocalDateTime() : null;

                TaskStatus taskStatus = TaskStatus.valueOf(rs.getString("status"));
                ExtraServices service = new ExtraServices(
                        rs.getString("taskid"),
                        rs.getString("title"),
                        rs.getString("description"),
                        taskStatus,
                        deadline,
                        completedAt,
                        rs.getDouble("price")
                );
                services.add(service);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return services;
    }
}
