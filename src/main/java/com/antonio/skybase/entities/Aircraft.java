package com.antonio.skybase.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "aircraft")
@Data
public class Aircraft {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String type;

    @Column(name = "`range`")
    private Integer range;

    @Column
    private Integer capacity;
}
