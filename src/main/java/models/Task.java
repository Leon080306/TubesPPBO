package models;

import models.enums.TaskStatus;

import java.time.LocalDateTime;

public class Task {
    private String task_id;
    private String title;
    private String description;
    private TaskStatus status;
    private LocalDateTime deadline;
    private LocalDateTime completedAt;

    public Task(String task_id, String title, String description, TaskStatus status, LocalDateTime deadline, LocalDateTime completedAt) {
        this.task_id = task_id;
        this.title = title;
        this.description = description;
        this.status = status;
        this.deadline = deadline;
        this.completedAt = completedAt;
    }

    public String getTask_id() {
        return task_id;
    }
    public void setTask_id(String task_id) {
        this.task_id = task_id;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public TaskStatus isStatus() {
        return status;
    }
    public void setStatus(TaskStatus status) {
        this.status = status;
    }
    public LocalDateTime getDeadline() {
        return deadline;
    }
    public void setDeadline(LocalDateTime deadline) {
        this.deadline = deadline;
    }
    public LocalDateTime getCompletedAt() {
        return completedAt;
    }
    public void setCompletedAt(LocalDateTime completedAt) {
        this.completedAt = completedAt;
    }
}
