package models;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class Shift {
    private LocalTime startTime;
    private LocalTime endTime;
    private LocalDate date;
    private boolean isPresent;
    private List<Task> tasksList;

    public Shift(LocalTime startTime, LocalTime endTime, LocalDate date, boolean isPresent) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.date = date;
        this.isPresent = isPresent;
        this.tasksList = new ArrayList<>();
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public boolean isPresent() {
        return isPresent;
    }

    public void setPresent(boolean present) {
        isPresent = present;
    }

    public List<Task> getTasksList() {
        return tasksList;
    }
}
