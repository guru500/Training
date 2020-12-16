package com.copious.training.controller;


import com.copious.training.exceptions.EmployeeNotFoundException;
import com.copious.training.model.Employee;
import com.copious.training.service.EmployeeService;
import com.copious.training.util.FilterCriteria;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(value = "Control employees details.")
@RestController
@RequestMapping(value = "/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @ApiOperation(value = "Gets employee details.", notes = "Sorted employees are according to age in desc order.",
            response = Employee.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful getting employee details in desc order of their age."),
            @ApiResponse(code = 404, message = "Employee details not found."),
            @ApiResponse(code = 500, message = "Something went wrong, Internal server error")
    })
    @GetMapping("/get-employees")
    public ResponseEntity<List<Employee>> getEmployee() throws EmployeeNotFoundException {
        List<Employee> empList = employeeService.sortByAge();

        return new ResponseEntity<>(empList, HttpStatus.OK);
    }


    @ApiOperation(value = "Filter employee details.", notes = "Filter employees based on parameters.", response =
            Employee.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful getting employee details."),
            @ApiResponse(code = 404, message = "Employee details not found."),
            @ApiResponse(code = 500, message = "Something went wrong, Internal server error")
    })
    @PostMapping("/emp-filter")
    public ResponseEntity<List<Employee>> getEmployee(
            @ApiParam(name = "lowerAgeLimit", value = "Lower age limit of employee.", required = true)
            @RequestParam("lowerAgeLimit") int lowerAgeLimit,
            @ApiParam(name = "upperAgeLimit", value = "Upper age limit of employee.", required = true)
            @RequestParam("upperAgeLimit") int upperAgeLimit,
            @ApiParam(name = "gender", value = "Gender of employee.", example =
                    "Male/Female", required = true)
            @RequestParam("gender") String gender) throws EmployeeNotFoundException {

        List<Employee> empList = employeeService.sortByAge();
        return new ResponseEntity<>(empList, HttpStatus.OK);
    }

    @ApiOperation(value = "Filter employee details.", notes = "Filter employees based on gender.", response =
            Employee.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful getting employee details."),
            @ApiResponse(code = 404, message = "Employee details not found."),
            @ApiResponse(code = 500, message = "Something went wrong, Internal server error")
    })
    @PostMapping("/gender-filter")
    public ResponseEntity<List<Employee>> genderFilter(
            @ApiParam(name = "gender", type = "FilterCriteria", value = "Gender of employee.", example =
                    "Option Male/Female", required = true)
            @RequestParam("gender") FilterCriteria gender) throws EmployeeNotFoundException {

        return new ResponseEntity<>(employeeService.genderFilter(gender), HttpStatus.OK);
    }
}
