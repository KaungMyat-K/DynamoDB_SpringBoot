package com.db.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.db.entity.Employee;
import com.db.repository.EmployeeService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
@RequestMapping("/employee")
public class EmployeeController {


    @Autowired
    private EmployeeService employeeRepo;
    

    @PostMapping
    public Employee save(@RequestBody Employee employee) {
       return employeeRepo.save(employee);
    }

    @PutMapping("/update/{id}")
    public String update(@RequestBody Employee employee,@PathVariable String id) {
        return employeeRepo.update(employee, id);
    }
    
    @GetMapping("/{id}")
    public Employee getEmployeeById(@PathVariable String id) {
        return employeeRepo.getEmployeeById(id);
    }
    
    @DeleteMapping("/{id}")
    public String delete(@PathVariable String id) {
        return employeeRepo.delete(id);
    }

}
