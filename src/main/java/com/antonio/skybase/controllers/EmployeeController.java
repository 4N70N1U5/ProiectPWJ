package com.antonio.skybase.controllers;

import com.antonio.skybase.dtos.EmployeeDTO;
import com.antonio.skybase.entities.Employee;
import com.antonio.skybase.services.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @PostMapping
    public ResponseEntity<Employee> create(@RequestBody @Valid EmployeeDTO employeeDTO) {
        Employee createdEmployee = employeeService.create(employeeDTO);

        return ResponseEntity.created(ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(createdEmployee.getId())
                .toUri()).body(createdEmployee);
    }

    @GetMapping
    public ResponseEntity<List<Employee>> getAll() {
        return ResponseEntity.ok(employeeService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Employee> getById(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(employeeService.getById(id));
    }

    @GetMapping("/available")
    public ResponseEntity<List<Employee>> getAvailableEmployees(@RequestParam("date") LocalDate date) {
        return ResponseEntity.ok(employeeService.getAvailableEmployeesByDate(date));
    }

    @GetMapping("/{id}/availabilities")
    public ResponseEntity<List<LocalDate>> getEmployeeAvailabilities(@PathVariable("id") Integer id, @RequestParam("startDate") LocalDate startDate, @RequestParam("endDate") LocalDate endDate) {
        return ResponseEntity.ok(employeeService.getEmployeeAvailabilities(id, startDate, endDate));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Employee> update(@PathVariable("id") Integer id, @RequestBody @Valid EmployeeDTO employeeDTO) {
        return ResponseEntity.ok(employeeService.update(id, employeeDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) {
        employeeService.delete(id);
        return ResponseEntity.noContent().build();
    }
}