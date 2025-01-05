package com.antonio.skybase.controllers;

import com.antonio.skybase.dtos.CityDTO;
import com.antonio.skybase.entities.City;
import com.antonio.skybase.services.CityService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/cities")
public class CityController {
    @Autowired
    private CityService cityService;

    @PostMapping
    public ResponseEntity<City> create(@RequestBody @Valid CityDTO cityDTO) {
        City createdCity = cityService.create(cityDTO);

        return ResponseEntity.created(ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(createdCity.getId())
                .toUri()).body(createdCity);
    }

    @GetMapping
    public ResponseEntity<List<City>> getAll() {
        return ResponseEntity.ok(cityService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<City> getById(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(cityService.getById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<City> update(@PathVariable("id") Integer id, @RequestBody @Valid CityDTO cityDTO) {
        return ResponseEntity.ok(cityService.update(id, cityDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) {
        cityService.delete(id);
        return ResponseEntity.noContent().build();
    }
}