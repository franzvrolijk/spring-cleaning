package com.example.my_api.service;

import static org.springframework.http.HttpStatus.NOT_FOUND;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.my_api.dto.ChoreStatsResponse;
import com.example.my_api.dto.CreateChoreRequest;
import com.example.my_api.model.Chore;

@Service
public class ChoreService {
    private final List<Chore> chores = new ArrayList<>();
    private long nextId = 8;

    public ChoreService() {
        // Sample data to have something to work with, this is not usually how it would be done in a real application, but it is sufficient for this example
        chores.add(new Chore(1, "Take bins out", "outside", "LOW", "OPEN", LocalDate.now().minusDays(1), null));
        chores.add(new Chore(2, "Clean kitchen", "kitchen", "HIGH", "OPEN", LocalDate.now().plusDays(1), null));
        chores.add(new Chore(4, "Vacuum hall", "hall", "MEDIUM", "DONE", LocalDate.now().minusDays(2), LocalDateTime.now().minusDays(1)));
        chores.add(new Chore(7, "Dust shelves", "living-room", "LOW", "OPEN", LocalDate.now(), null));
    }

    public List<Chore> getAll(String status) {
        if (status == null || status.isBlank()) {
            return List.copyOf(chores);
        }

        return chores.stream()
                .filter(chore -> chore.status().equalsIgnoreCase(status))
                .toList();
    }

    public Chore getById(long id) {
        int index = (int) id;
        if (index < 0 || index >= chores.size()) {
            throw new ResponseStatusException(NOT_FOUND, "Chore not found");
        }

        return chores.get(index);
    }

    public Chore createChore(CreateChoreRequest request) {
        Chore chore = new Chore(
                nextId++,
                request.title(),
                request.room(),
                request.priority(),
                "OPEN",
                request.dueDate(),
                null
        );

        chores.add(chore);
        return chore;
    }

    public Chore completeChore(long id) {
        Chore existing = chores.stream()
                .filter(chore -> chore.id() == id)
                .findFirst()
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Chore not found"));

        if ("DONE".equalsIgnoreCase(existing.status())) {
            return existing;
        }

        Chore updated = new Chore(
                existing.id(),
                existing.title(),
                existing.room(),
                existing.priority(),
                "DONE",
                existing.dueDate(),
                LocalDateTime.now()
        );

        return updated;
    }

    public ChoreStatsResponse getStats() {
        long total = chores.size();
        long open = chores.stream().filter(chore -> "OPEN".equalsIgnoreCase(chore.status())).count();
        long completed = chores.stream().filter(chore -> "DONE".equalsIgnoreCase(chore.status())).count();
        long overdue = chores.stream().filter(chore -> chore.dueDate().isBefore(LocalDate.now())).count();
        return new ChoreStatsResponse(total, open, completed, overdue);
    }
}