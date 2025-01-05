package com.antonio.skybase.controllers;

import com.antonio.skybase.dtos.JobDTO;
import com.antonio.skybase.entities.Job;
import com.antonio.skybase.services.JobService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/jobs")
public class JobController {
    @Autowired
    private JobService jobService;

    @PostMapping
    public ResponseEntity<Job> create(@RequestBody @Valid JobDTO jobDTO) {
        Job createdJob = jobService.create(jobDTO);

        return ResponseEntity.created(ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(createdJob.getId())
                .toUri()).body(createdJob);
    }

    @GetMapping
    public ResponseEntity<List<Job>> getAll() {
        return ResponseEntity.ok(jobService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Job> getById(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(jobService.getById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Job> update(@PathVariable("id") Integer id, @RequestBody @Valid JobDTO jobDTO) {
        return ResponseEntity.ok(jobService.update(id, jobDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) {
        jobService.delete(id);
        return ResponseEntity.noContent().build();
    }
}