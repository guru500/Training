package com.copious.training.controller;


import com.copious.training.exceptions.EmployeeNotFoundException;
import com.copious.training.model.Employee;
import com.copious.training.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;

    @GetMapping("/get-employees")
    public ResponseEntity<List<Employee>> getEmployee() throws EmployeeNotFoundException {
        List<Employee> empList = employeeService.sortByAge();

        return new ResponseEntity<>(empList, HttpStatus.OK);
    }


    @PostMapping("/emp-filter")
    public ResponseEntity<List<Employee>> getEmployee(@RequestParam("lowerAgeLimit") int lowerAgeLimit,
                                                      @RequestParam("upperAgeLimit") int upperAgeLimit,
                                                      @RequestParam("gender") String gender) throws EmployeeNotFoundException {

        List<Employee> empList = employeeService.sortByAge();
        return new ResponseEntity<>(empList, HttpStatus.OK);
    }
}
