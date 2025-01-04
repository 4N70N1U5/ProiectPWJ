package com.antonio.skybase.controllers;

import com.antonio.skybase.entities.Country;
import com.antonio.skybase.services.CountryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/countries")
public class CountryController {
    @Autowired
    private CountryService countryService;

    @PostMapping
    public ResponseEntity<Country> create(@RequestBody @Valid Country country) {
        Country createdCountry = countryService.create(country);

        return ResponseEntity.created(ServletUriComponentsBuilder.fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(createdCountry.getId())
                    .toUri()).body(createdCountry);
    }

    @GetMapping
    public ResponseEntity<List<Country>> getAll() {
        return ResponseEntity.ok(countryService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Country> getById(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(countryService.getById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Country> update(@PathVariable("id") Integer id, @RequestBody Country country) {
        return ResponseEntity.ok(countryService.update(id, country));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) {
        countryService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
