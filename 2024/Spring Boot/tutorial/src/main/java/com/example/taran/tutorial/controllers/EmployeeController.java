package com.example.taran.tutorial.controllers;

//Operations
//GET /employees
//POST /employees
//DELETE /employees/{id}

import com.example.taran.tutorial.dto.EmployeeDTO;
import com.example.taran.tutorial.services.EmployeeService;
import jakarta.websocket.server.PathParam;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(path = "/employees")
public class EmployeeController {
    private final EmployeeService employeeService;
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    // PathVariable means that endpoint -> should be there
    @GetMapping(path = "/{id}")
    public EmployeeDTO getEmployeeById(@PathVariable("id") Long id) {
        return employeeService.getEmployeeById(id) ;
    }

    @GetMapping
    public List<EmployeeDTO> getAllEmployees() {
        return employeeService.getAllEmployees();
    }
    // PathParam means optional endpoints
    // http://localhost:8080/employees?sortBy=HI&limit=123
    @PostMapping
    public EmployeeDTO createNewEmployee(@RequestBody EmployeeDTO employeeDTO) {
return employeeService.createNewEmployee(employeeDTO);
    }

    @DeleteMapping(path = "/{id}")
    public boolean deleteEmployee(@PathVariable("id") Long id) {
      return employeeService.deleteEmployeeById(id);
    }
}







//
//@RestController
//public class EmployeeController {
//
//    // PathVariable means that endpoint -> should be there
//    @GetMapping(path = "/employees/{id}")
//    public EmployeeDTO getEmployees(@PathVariable("id") Long id) {
//        return new EmployeeDTO(12l,"Taran",LocalDate.of(2002,12,02),true);
//    }
//
//    // PathParam means optional endpoints
//    // http://localhost:8080/employees?sortBy=HI&limit=123
//    @GetMapping(path = "/employees")
//    public String getEmployees(@PathParam("sortBy") String sortBy,@PathParam("limit") Integer limit) {
//        return "Hello" + sortBy + " "+ limit;
//    }
//
//}
