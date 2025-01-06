package com.antonio.skybase.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class EmployeeDTO {
    private Integer id;

    @NotBlank(message = "First name must not be blank")
    private String firstName;

    @NotBlank(message = "Last name must not be blank")
    private String lastName;

    @NotBlank(message = "Phone number must not be blank")
    private String phoneNumber;

    @Email(message = "Email should be valid")
    @NotBlank(message = "Email must not be blank")
    private String email;

    @NotBlank(message = "Salary must not be blank")
    private String salary;

    @NotNull(message = "Job ID must not be null")
    private Integer jobId;

    private Integer flightHours;

    private Integer managerId;
}