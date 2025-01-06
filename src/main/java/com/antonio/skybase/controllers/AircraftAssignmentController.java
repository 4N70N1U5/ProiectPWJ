package com.antonio.skybase.controllers;

import com.antonio.skybase.dtos.AircraftAssignmentDTO;
import com.antonio.skybase.entities.AircraftAssignment;
import com.antonio.skybase.entities.AircraftAssignmentId;
import com.antonio.skybase.services.AircraftAssignmentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/aircraft-assignments")
public class AircraftAssignmentController {
    @Autowired
    private AircraftAssignmentService aircraftAssignmentService;

    @PostMapping
    public ResponseEntity<AircraftAssignment> create(@RequestBody @Valid AircraftAssignmentDTO aircraftAssignmentDTO) {
        AircraftAssignment createdAircraftAssignment = aircraftAssignmentService.create(aircraftAssignmentDTO);

        return ResponseEntity.created(ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{aircraftId}/{flightId}/{date}")
                .buildAndExpand(createdAircraftAssignment.getId().getAircraftId(),
                        createdAircraftAssignment.getId().getFlightId(),
                        createdAircraftAssignment.getId().getDate())
                .toUri()).body(createdAircraftAssignment);
    }

    @GetMapping
    public ResponseEntity<List<AircraftAssignment>> getAll() {
        return ResponseEntity.ok(aircraftAssignmentService.getAll());
    }

    @GetMapping("/{aircraftId}/{flightId}/{date}")
    public ResponseEntity<AircraftAssignment> getById(@PathVariable("aircraftId") Integer aircraftId,
                                                      @PathVariable("flightId") Integer flightId,
                                                      @PathVariable("date") LocalDate date) {
        AircraftAssignmentId id = new AircraftAssignmentId();
        id.setAircraftId(aircraftId);
        id.setFlightId(flightId);
        id.setDate(date);
        return ResponseEntity.ok(aircraftAssignmentService.getById(id));
    }

    @GetMapping("/by-date")
    public ResponseEntity<List<AircraftAssignment>> getAssignmentsByDate(@RequestParam("date") LocalDate date) {
        return ResponseEntity.ok(aircraftAssignmentService.getAssignmentsByDate(date));
    }

    @GetMapping("/by-aircraft")
    public ResponseEntity<List<AircraftAssignment>> getAssignmentsByAircraftAndDateRange(@RequestParam("aircraftId") Integer aircraftId,
                                                                                         @RequestParam("startDate") LocalDate startDate,
                                                                                         @RequestParam("endDate") LocalDate endDate) {
        return ResponseEntity.ok(aircraftAssignmentService.getAssignmentsByAircraftAndDateRange(aircraftId, startDate, endDate));
    }

    @GetMapping("/by-flight")
    public ResponseEntity<List<AircraftAssignment>> getAssignmentsByFlightAndDateRange(@RequestParam("flightId") Integer flightId,
                                                                                         @RequestParam("startDate") LocalDate startDate,
                                                                                         @RequestParam("endDate") LocalDate endDate) {
        return ResponseEntity.ok(aircraftAssignmentService.getAssignmentsByFlightAndDateRange(flightId, startDate, endDate));
    }

    @DeleteMapping("/{aircraftId}/{flightId}/{date}")
    public ResponseEntity<Void> delete(@PathVariable("aircraftId") Integer aircraftId,
                                       @PathVariable("flightId") Integer flightId,
                                       @PathVariable("date") LocalDate date) {
        AircraftAssignmentId id = new AircraftAssignmentId();
        id.setAircraftId(aircraftId);
        id.setFlightId(flightId);
        id.setDate(date);
        aircraftAssignmentService.delete(id);
        return ResponseEntity.noContent().build();
    }
}