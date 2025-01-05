package com.antonio.skybase.controllers;

import com.antonio.skybase.dtos.AirportDTO;
import com.antonio.skybase.entities.Airport;
import com.antonio.skybase.services.AirportService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/airports")
public class AirportController {
    @Autowired
    private AirportService airportService;

    @PostMapping
    public ResponseEntity<Airport> create(@RequestBody @Valid AirportDTO airportDTO) {
        Airport createdAirport = airportService.create(airportDTO);

        return ResponseEntity.created(ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(createdAirport.getId())
                .toUri()).body(createdAirport);
    }

    @GetMapping
    public ResponseEntity<List<Airport>> getAll() {
        return ResponseEntity.ok(airportService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Airport> getById(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(airportService.getById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Airport> update(@PathVariable("id") Integer id, @RequestBody @Valid AirportDTO airportDTO) {
        return ResponseEntity.ok(airportService.update(id, airportDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) {
        airportService.delete(id);
        return ResponseEntity.noContent().build();
    }
}