package com.antonio.skybase.controllers;

import com.antonio.skybase.entities.Aircraft;
import com.antonio.skybase.services.AircraftService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

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