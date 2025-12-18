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

import models.Shift;
import models.Staff;
import models.Task;
import models.enums.Department;
import models.enums.TaskStatus;
import utils.Database;
import utils.GenerateUUID;

public class TaskRepository {
    public static Connection conn = Database.connect();
    ShiftRepository shiftRepository;
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
    

    //add extraservice
    public boolean addExtraSevices(String title, String description, String bookingID, LocalDateTime deadline, Department department, double price){
        String sqlInsert = "INSERT INTO task(taskid, shiftid, bookingid, title, description, status, deadline, completedat, price) VALUES (?, ?, ?, ?,?, 'ASSIGNED', ?, NULL, ?)";
        Random random = new Random();
        try {
            List<Shift> listShiftDepartment = shiftRepository.getShiftByDepartment(department);
            Shift chosenStaff = listShiftDepartment.get(random.nextInt(listShiftDepartment.size()));

            PreparedStatement pstmtInsert = conn.prepareStatement(sqlInsert);
            pstmtInsert.setString(1, GenerateUUID.generateUUID());
            pstmtInsert.setString(2, chosenStaff.getShiftId()); 
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
}
