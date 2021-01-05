package com.copious.training.controller;


import com.copious.training.exceptions.EmployeeNotFoundException;
import com.copious.training.exceptions.GenericResponse;
import com.copious.training.model.Employee;
import com.copious.training.service.EmployeeService;
import com.copious.training.util.FilterCriteria;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.ExecutionException;

@Api(value = "Control employees details.")
@RestController
@RequestMapping("/employee")
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
    public ResponseEntity<GenericResponse<Object>> getEmployee() throws EmployeeNotFoundException, ExecutionException
            , InterruptedException {


        return new ResponseEntity<>(new GenericResponse<>(true, HttpStatus.OK.name(),
                employeeService.pairOldAndYoungEmployee()),
                HttpStatus.OK);
    }

    @GetMapping("/sequential")
    public ResponseEntity<GenericResponse<Object>> sequential() throws EmployeeNotFoundException {

        return new ResponseEntity<>(new GenericResponse<>(true, HttpStatus.OK.name(),
                employeeService.sequential()),
                HttpStatus.OK);
    }


    @ApiOperation(value = "Filter employee details.", notes = "Filter employees based on parameters.", response =
            Employee.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful getting employee details."),
            @ApiResponse(code = 404, message = "Employee details not found."),
            @ApiResponse(code = 500, message = "Something went wrong, Internal server error")
    })
    @PostMapping("/emp-filter")
    public ResponseEntity<GenericResponse<Object>> getEmployee(
            @ApiParam(name = "lowerAgeLimit", value = "Lower age limit of employee.", required = true)
            @RequestParam("lowerAgeLimit") Integer lowerAgeLimit,
            @ApiParam(name = "upperAgeLimit", value = "Upper age limit of employee.", required = true)
            @RequestParam("upperAgeLimit") Integer upperAgeLimit,
            @ApiParam(name = "gender", value = "Gender of employee.", example =
                    "Male/Female", required = true)
            @RequestParam("gender") FilterCriteria gender) throws EmployeeNotFoundException {

        return new ResponseEntity<>(new GenericResponse<>(true, HttpStatus.OK.name(),
                employeeService.filterEmployee(lowerAgeLimit, upperAgeLimit, gender)),
                HttpStatus.OK);
    }

    @ApiOperation(value = "Filter employee details.", notes = "Filter employees based on gender.", response =
            Employee.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful getting employee details."),
            @ApiResponse(code = 404, message = "Employee details not found."),
            @ApiResponse(code = 500, message = "Something went wrong, Internal server error")
    })
    @PostMapping("/gender-filter")
    public ResponseEntity<GenericResponse<Object>> genderFilter(
            @ApiParam(name = "gender", type = "FilterCriteria", value = "Gender of employee.", example =
                    "Option Male/Female", required = true)
            @RequestParam("gender") FilterCriteria gender) throws EmployeeNotFoundException {

        return new ResponseEntity<>(new GenericResponse<>(true, HttpStatus.OK.name(),
                employeeService.genderFilter(gender)),
                HttpStatus.OK);
    }

    @ApiOperation(value = "Sort employee details.", notes = "Sort by name and then salary.", response =
            Employee.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful sorting employee details."),
            @ApiResponse(code = 404, message = "Employee details not found."),
            @ApiResponse(code = 500, message = "Something went wrong, Internal server error")
    })
    @PostMapping("/sort-employee")
    public ResponseEntity<GenericResponse<Object>> sortEmployees() throws EmployeeNotFoundException {

        return new ResponseEntity<>(new GenericResponse<>(true, HttpStatus.OK.name(),
                employeeService.sortEmployees()),
                HttpStatus.OK);
    }
}
