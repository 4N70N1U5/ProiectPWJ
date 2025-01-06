package com.antonio.skybase.services;

import com.antonio.skybase.dtos.EmployeeDTO;
import com.antonio.skybase.entities.Employee;
import com.antonio.skybase.entities.Job;
import com.antonio.skybase.exceptions.BadRequestException;
import com.antonio.skybase.exceptions.NotFoundException;
import com.antonio.skybase.repositories.EmployeeRepository;
import com.antonio.skybase.repositories.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private JobRepository jobRepository;

    public Employee create(EmployeeDTO employeeDTO) {
        Job job = validateJobExists(employeeDTO.getJobId());
        Employee manager = employeeDTO.getManagerId() != null ? validateEmployeeExists(employeeDTO.getManagerId()) : null;

        Employee employee = new Employee();
        employee.setFirstName(employeeDTO.getFirstName());
        employee.setLastName(employeeDTO.getLastName());
        employee.setPhoneNumber(employeeDTO.getPhoneNumber());
        employee.setEmail(employeeDTO.getEmail());
        employee.setSalary(employeeDTO.getSalary());
        employee.setJob(job);
        employee.setFlightHours(employeeDTO.getFlightHours());
        employee.setManager(manager);

        return employeeRepository.save(employee);
    }

    public List<Employee> getAll() {
        return employeeRepository.findAll();
    }

    public Employee getById(Integer id) {
        return employeeRepository.findById(id).orElseThrow(() -> new NotFoundException("Employee with ID " + id + " not found"));
    }

    public Employee update(Integer id, EmployeeDTO employeeDTO) {
        Employee employeeToUpdate = employeeRepository.findById(id).orElseThrow(() -> new NotFoundException("Employee with ID " + id + " not found"));
        Job job = validateJobExists(employeeDTO.getJobId());
        Employee manager = employeeDTO.getManagerId() != null ? validateEmployeeExists(employeeDTO.getManagerId()) : null;

        employeeToUpdate.setFirstName(employeeDTO.getFirstName());
        employeeToUpdate.setLastName(employeeDTO.getLastName());
        employeeToUpdate.setPhoneNumber(employeeDTO.getPhoneNumber());
        employeeToUpdate.setEmail(employeeDTO.getEmail());
        employeeToUpdate.setSalary(employeeDTO.getSalary());
        employeeToUpdate.setJob(job);
        employeeToUpdate.setFlightHours(employeeDTO.getFlightHours());
        employeeToUpdate.setManager(manager);

        return employeeRepository.save(employeeToUpdate);
    }

    public void delete(Integer id) {
        employeeRepository.deleteById(id);
    }

    private Job validateJobExists(Integer jobId) {
        return jobRepository.findById(jobId).orElseThrow(() -> new BadRequestException("Job with ID " + jobId + " does not exist"));
    }

    private Employee validateEmployeeExists(Integer employeeId) {
        return employeeRepository.findById(employeeId).orElseThrow(() -> new BadRequestException("Employee with ID " + employeeId + " does not exist"));
    }
}