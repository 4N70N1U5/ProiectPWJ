package com.antonio.skybase.controllers;

import com.antonio.skybase.entities.Aircraft;
import com.antonio.skybase.services.AircraftService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/aircraft")
public class AircraftController {
    @Autowired
    private AircraftService aircraftService;

    @PostMapping
    public ResponseEntity<Aircraft> create(@RequestBody @Valid Aircraft aircraft) {
        Aircraft createdAircraft = aircraftService.create(aircraft);

        return ResponseEntity.created(ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(createdAircraft.getId())
                .toUri()).body(createdAircraft);
    }

    @GetMapping
    public ResponseEntity<List<Aircraft>> getAll() {
        return ResponseEntity.ok(aircraftService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Aircraft> getById(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(aircraftService.getById(id));
    }

    @GetMapping("/available")
    public ResponseEntity<List<Aircraft>> getAvailableAircraft(@RequestParam("date") LocalDate date) {
        return ResponseEntity.ok(aircraftService.getAvailableAircraftByDate(date));
    }

    @GetMapping("/{id}/availabilities")
    public ResponseEntity<List<LocalDate>> getAircraftAvailabilities(@PathVariable("id") Integer id, @RequestParam("startDate") LocalDate startDate, @RequestParam("endDate") LocalDate endDate) {
        return ResponseEntity.ok(aircraftService.getAircraftAvailabilities(id, startDate, endDate));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Aircraft> update(@PathVariable("id") Integer id, @RequestBody @Valid Aircraft aircraft) {
        return ResponseEntity.ok(aircraftService.update(id, aircraft));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) {
        aircraftService.delete(id);
        return ResponseEntity.noContent().build();
    }
}