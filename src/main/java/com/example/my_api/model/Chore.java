package com.example.my_api.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record Chore(
        long id,
        String title,
        String room,
        String priority,
        String status,
        LocalDate dueDate,
        LocalDateTime completedAt
) {
}