package com.example.taran.tutorial.entities;

import com.fasterxml.jackson.annotation.JsonTypeId;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.web.WebProperties;

import java.time.LocalDate;


@Entity
@Table(name = "employees")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private LocalDate dateOfJoining;
    private Boolean isActive;
}
