package com.example.taran.tutorial.repositories;

import com.example.taran.tutorial.controllers.EmployeeController;
import com.example.taran.tutorial.entities.EmployeeEntity;
import org.springframework.boot.autoconfigure.data.jpa.JpaRepositoriesAutoConfiguration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


// JPA will automatically write SQL Query
@Repository
public interface EmployeeRepository extends JpaRepository<EmployeeEntity, Long> {
}
