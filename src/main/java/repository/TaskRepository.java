package repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import models.Task;
import models.enums.TaskStatus;
import utils.Database;

public class TaskRepository {
    public static Connection conn = Database.connect();

    // public boolean addTask(String shiftId, String title, String descriptionTask, TaskStatus status, LocalDateTime deadDateTime, LocalDateTime completedAt){
    //     String sql = "INSERT INTO task (taskid, shiftid, title, description, )"; 

    // }
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
        String sql = "UPDATE task SET status = cast(? as taskstatus_type) WHERE taskid = ?";
        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, status.name());
            pstmt.setString(2, taskId);
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return false;
    }
}
