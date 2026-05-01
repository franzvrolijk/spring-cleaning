package com.example.my_api.service;

import static org.springframework.http.HttpStatus.NOT_FOUND;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.example.my_api.model.Priority;
import com.example.my_api.model.Status;
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
        chores.add(new Chore(1, "Take bins out", "outside", Priority.LOW, Status.OPEN, LocalDate.now().minusDays(1), null));
        chores.add(new Chore(2, "Clean kitchen", "kitchen", Priority.HIGH, Status.OPEN, LocalDate.now().plusDays(1), null));
        chores.add(new Chore(4, "Vacuum hall", "hall", Priority.MEDIUM, Status.DONE, LocalDate.now().minusDays(2), LocalDateTime.now().minusDays(1)));
        chores.add(new Chore(7, "Dust shelves", "living-room", Priority.LOW, Status.OPEN, LocalDate.now(), null));
    }

    public List<Chore> getAll(String status) {
        if (status == null || status.isBlank()) {
            return List.copyOf(chores);
        }

        return chores.stream()
                .filter(chore -> chore.getStatus().toString().equalsIgnoreCase(status))
                .toList();
    }

    public Chore getById(long id) {
//        int index = (int) id;
//        if (index < 0 || index >= chores.size()) {
//            throw new ResponseStatusException(NOT_FOUND, "Chore not found");
//        }

        return chores.stream()
                .filter(chore -> chore.getId() == id)
                .findFirst()
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Chore not found"));
    }

    public Chore createChore(CreateChoreRequest request) {
        Chore chore = new Chore(
                nextId++,
                request.title(),
                request.room(),
                request.priority(),
                Status.OPEN,
                request.dueDate(),
                null
        );

        chores.add(chore);
        return chore;
    }

    public Chore completeChore(long id) {
        Chore existing = chores.stream()
                .filter(chore -> chore.getId() == id)
                .findFirst()
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Chore not found"));

        if ("DONE".equalsIgnoreCase(existing.getStatus().toString())) {
            return existing;
        }

        existing.setStatus(Status.DONE);

        return existing;
    }

    public ChoreStatsResponse getStats() {
        long total = chores.size();
        long open = chores.stream().filter(chore -> "OPEN".equalsIgnoreCase(chore.getStatus().toString())).count();
        long completed = chores.stream().filter(chore -> "DONE".equalsIgnoreCase(chore.getStatus().toString())).count();
        long overdue = chores.stream()
                .filter(chore -> chore.getDueDate().isBefore(LocalDate.now()))
                .filter(chore -> chore.getCompletedAt() != null).count();
        return new ChoreStatsResponse(total, open, completed, overdue);
    }
}