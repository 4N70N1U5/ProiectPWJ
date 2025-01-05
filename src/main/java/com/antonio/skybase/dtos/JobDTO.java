package com.antonio.skybase.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class JobDTO {
    private Integer id;

    @NotBlank(message = "Job title must not be blank")
    private String title;

    @NotNull(message = "Minimum salary must not be null")
    private Double minSalary;

    @NotNull(message = "Maximum salary must not be null")
    private Double maxSalary;

    @NotNull(message = "Department ID must not be null")
    private Integer departmentId;
}