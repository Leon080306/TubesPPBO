package controller;

import java.time.LocalDateTime;
import java.util.List;
 
import models.Task;
import models.enums.Department;
import models.enums.TaskStatus;
import repository.TaskRepository;

public class TaskController {
    TaskRepository taskRepository;

    public TaskController(){
        taskRepository = new TaskRepository();
    }

    public boolean addTaskAdmin(String shiftId, String title, String descriptionTask, LocalDateTime deadline){
        if (taskRepository.addTask(shiftId, title, descriptionTask, deadline)) {
            return true;
        }
        return false;
    }

    public List<Task> getTasksByShift(String shiftId){
        return taskRepository.findTasksByShiftId(shiftId);
    }

    public boolean doSpesificTask(String taskId){
        if(taskRepository.updateTaskStatus(taskId, TaskStatus.COMPLETED)){
            return true;
        } 
        return false;
    }

    public boolean doAllTaskInAShift(String shiftId){
        for (Task current_Task : taskRepository.findTasksByShiftId(shiftId)) {
            if (!doSpesificTask(current_Task.getTask_id())) {
                return false;
            }
        }
        return true;
    }

    public boolean updateTaskStatus(String taskId, TaskStatus status){
        if (taskRepository.updateTaskStatus(taskId, status)) {
            return true;
        }
        return false;
    }

    public boolean addExtraSevices(String title, String description, String bookingID, LocalDateTime deadline, Department department){
        if (taskRepository.addExtraSevices(title, description, bookingID, deadline, department)) {
            return true;
        }
        return false;
    }
}
