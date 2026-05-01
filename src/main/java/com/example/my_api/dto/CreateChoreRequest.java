package com.example.my_api.dto;

import java.time.LocalDate;

import com.example.my_api.model.Priority;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreateChoreRequest(
        @NotBlank String title,
        @NotBlank String room,
        @NotBlank Priority priority,
        @NotNull LocalDate dueDate
) {
}