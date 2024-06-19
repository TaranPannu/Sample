package com.example.taran.tutorial.services;

import com.example.taran.tutorial.dto.EmployeeDTO;
import com.example.taran.tutorial.entities.EmployeeEntity;
import com.example.taran.tutorial.repositories.EmployeeRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeService {

    final EmployeeRepository employeeRepository;
    final ModelMapper modelMapper;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
        this.modelMapper = new ModelMapper();
    }

    public EmployeeDTO getEmployeeById(Long id) {
        EmployeeEntity employeeEntity = employeeRepository.getById(id);
return modelMapper.map(employeeEntity, EmployeeDTO.class);
    }

    public EmployeeDTO createNewEmployee(EmployeeDTO employeeDTO) {
EmployeeEntity employeeEntity = modelMapper.map(employeeDTO, EmployeeEntity.class);
return modelMapper.map(employeeRepository.save(employeeEntity), EmployeeDTO.class);
    }

    public List<EmployeeDTO> getAllEmployees() {
        List<EmployeeDTO> list = new ArrayList<>();
        for (EmployeeEntity employeeEntity : employeeRepository.findAll()) {
            EmployeeDTO map = modelMapper.map(employeeEntity, EmployeeDTO.class);
            list.add(map);
        }
        return list;
    }

    public boolean deleteEmployeeById(Long id) {
        boolean exists = employeeRepository.existsById(id);
        if(exists) {
            employeeRepository.deleteById(id);
            return true;
        }
        return false;
    }
}


