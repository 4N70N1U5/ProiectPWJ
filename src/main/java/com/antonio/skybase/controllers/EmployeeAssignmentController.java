package com.antonio.skybase.controllers;

import com.antonio.skybase.dtos.EmployeeAssignmentDTO;
import com.antonio.skybase.entities.EmployeeAssignment;
import com.antonio.skybase.entities.EmployeeAssignmentId;
import com.antonio.skybase.services.EmployeeAssignmentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/employee-assignments")
public class EmployeeAssignmentController {
    @Autowired
    private EmployeeAssignmentService employeeAssignmentService;

    @PostMapping
    public ResponseEntity<EmployeeAssignment> create(@RequestBody @Valid EmployeeAssignmentDTO employeeAssignmentDTO) {
        EmployeeAssignment createdEmployeeAssignment = employeeAssignmentService.create(employeeAssignmentDTO);

        return ResponseEntity.created(ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{employeeId}/{flightId}/{date}")
                .buildAndExpand(createdEmployeeAssignment.getId().getEmployeeId(),
                        createdEmployeeAssignment.getId().getFlightId(),
                        createdEmployeeAssignment.getId().getDate())
                .toUri()).body(createdEmployeeAssignment);
    }

    @GetMapping
    public ResponseEntity<List<EmployeeAssignment>> getAll() {
        return ResponseEntity.ok(employeeAssignmentService.getAll());
    }

    @GetMapping("/{employeeId}/{flightId}/{date}")
    public ResponseEntity<EmployeeAssignment> getById(@PathVariable("employeeId") Integer employeeId,
                                                      @PathVariable("flightId") Integer flightId,
                                                      @PathVariable("date") LocalDate date) {
        EmployeeAssignmentId id = new EmployeeAssignmentId();
        id.setEmployeeId(employeeId);
        id.setFlightId(flightId);
        id.setDate(date);
        return ResponseEntity.ok(employeeAssignmentService.getById(id));
    }

    @GetMapping("/by-date")
    public ResponseEntity<List<EmployeeAssignment>> getAssignmentsByDate(@RequestParam("date") LocalDate date) {
        return ResponseEntity.ok(employeeAssignmentService.getAssignmentsByDate(date));
    }

    @GetMapping("/by-employee")
    public ResponseEntity<List<EmployeeAssignment>> getAssignmentsByEmployeeAndDateRange(@RequestParam("employeeId") Integer employeeId,
                                                                                         @RequestParam("startDate") LocalDate startDate,
                                                                                         @RequestParam("endDate") LocalDate endDate) {
        return ResponseEntity.ok(employeeAssignmentService.getAssignmentsByEmployeeAndDateRange(employeeId, startDate, endDate));
    }

    @GetMapping("/by-flight")
    public ResponseEntity<List<EmployeeAssignment>> getAssignmentsByFlightAndDateRange(@RequestParam("flightId") Integer flightId,
                                                                                       @RequestParam("startDate") LocalDate startDate,
                                                                                       @RequestParam("endDate") LocalDate endDate) {
        return ResponseEntity.ok(employeeAssignmentService.getAssignmentsByFlightAndDateRange(flightId, startDate, endDate));
    }

    @DeleteMapping("/{employeeId}/{flightId}/{date}")
    public ResponseEntity<Void> delete(@PathVariable("employeeId") Integer employeeId,
                                       @PathVariable("flightId") Integer flightId,
                                       @PathVariable("date") LocalDate date) {
        EmployeeAssignmentId id = new EmployeeAssignmentId();
        id.setEmployeeId(employeeId);
        id.setFlightId(flightId);
        id.setDate(date);
        employeeAssignmentService.delete(id);
        return ResponseEntity.noContent().build();
    }
}