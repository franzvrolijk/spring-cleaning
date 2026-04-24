package com.example.my_api.dto;

public record ChoreStatsResponse(
        long total,
        long open,
        long completed,
        long overdue
) {
}