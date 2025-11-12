package models;

import java.time.LocalDateTime;

import models.enums.TaskStatus;

public class ExtraServices extends Task{
    private double price;
    public ExtraServices(String taskID, String title, String description, TaskStatus status, LocalDateTime deadline, LocalDateTime completedAt, double price){
        super(taskID, title, description, status, deadline, completedAt);
        this.price = price;
    }

    public double getPrice(){
        return this.price;
    }
    public void setPrice(double price){
        this.price = price;
    }
}
