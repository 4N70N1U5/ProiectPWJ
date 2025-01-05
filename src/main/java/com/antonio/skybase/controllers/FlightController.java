package com.antonio.skybase.controllers;

import com.antonio.skybase.dtos.FlightDTO;
import com.antonio.skybase.entities.Flight;
import com.antonio.skybase.services.FlightService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/flights")
public class FlightController {
    @Autowired
    private FlightService flightService;

    @PostMapping
    public ResponseEntity<Flight> create(@RequestBody @Valid FlightDTO flightDTO) {
        Flight createdFlight = flightService.create(flightDTO);

        return ResponseEntity.created(ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(createdFlight.getId())
                .toUri()).body(createdFlight);
    }

    @GetMapping
    public ResponseEntity<List<Flight>> getAll() {
        return ResponseEntity.ok(flightService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Flight> getById(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(flightService.getById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Flight> update(@PathVariable("id") Integer id, @RequestBody @Valid FlightDTO flightDTO) {
        return ResponseEntity.ok(flightService.update(id, flightDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) {
        flightService.delete(id);
        return ResponseEntity.noContent().build();
    }
}