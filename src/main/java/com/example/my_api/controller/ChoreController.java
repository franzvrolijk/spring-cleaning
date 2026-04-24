package com.example.my_api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.my_api.dto.ChoreStatsResponse;
import com.example.my_api.dto.CreateChoreRequest;
import com.example.my_api.model.Chore;
import com.example.my_api.service.ChoreService;

@RestController
@RequestMapping("/api/chores")
public class ChoreController {

    @Autowired
    private ChoreService choreService;

    @GetMapping("/stats")
    public ChoreStatsResponse getStats() {
        return choreService.getStatsSummary();
    }

    @GetMapping
    public List<Chore> getAll(@RequestParam(required = false) String status) {
        return choreService.getAll(status);
    }

    @GetMapping("/{id}")
    public Chore getById(@PathVariable long id) {
        return choreService.getById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Chore create(@RequestBody CreateChoreRequest request) {
        return choreService.createChore(request);
    }

    @PatchMapping("/{id}/complete")
    public Chore complete(@PathVariable long id) {
        return choreService.completeChore(id);
    }
}