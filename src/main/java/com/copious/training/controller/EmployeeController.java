package com.copious.training.controller;


import com.copious.training.model.Employee;
import com.copious.training.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;

    @GetMapping("/get-employees")
    public ResponseEntity<List<Employee>> getEmployee() {
        List<Employee> empList = new ArrayList<>();

        try {
            empList = employeeService.sortByAge();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new ResponseEntity<>(empList, HttpStatus.OK);
    }

    @PostMapping("/emp-filter")
    public ResponseEntity<List<Employee>> getEmployee(@RequestParam("lowerAgeLimit") int lowerAgeLimit,
                                                      @RequestParam("upperAgeLimit") int upperAgeLimit,
                                                      @RequestParam("gender") String gender) {
        List<Employee> empList = new ArrayList<>();

        try {
            empList = employeeService.sortByAge();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new ResponseEntity<>(empList, HttpStatus.OK);
    }
}
