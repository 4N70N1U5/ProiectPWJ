package com.antonio.skybase.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "jobs")
@Data
public class Job {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String title;

    @Column(name = "min_salary", nullable = false)
    private Double minSalary;

    @Column(name = "max_salary", nullable = false)
    private Double maxSalary;

    @ManyToOne
    @JoinColumn(name = "department_id", nullable = false)
    private Department department;
}
