package com.example.my_api.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

//public record Chore(
//        long id,
//        String title,
//        String room,
//        Priority priority,
//        Status status,
//        LocalDate dueDate,
//        LocalDateTime completedAt
//) {
//}

public class Chore{
    private long id;
    private String title;
    private String room;
    private Priority priority;

    public void setStatus(Status status) {
        this.status = status;
    }

    private Status status;
    private LocalDate dueDate;
    private LocalDateTime completedAt;

    public Chore(long id, String title, String room, Priority priority, Status status, LocalDate dueDate, LocalDateTime completedAt) {
        this.id = id;
        this.title = title;
        this.room = room;
        this.priority = priority;
        this.status = status;
        this.dueDate = dueDate;
        this.completedAt = completedAt;
    }

    public long getId() {
        return id;
    }

    public Status getStatus() {
        return status;
    }

    public String getTitle() {
        return title;
    }

    public String getRoom() {
        return room;
    }

    public Priority getPriority() {
        return priority;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public LocalDateTime getCompletedAt() {
        return completedAt;
    }
}