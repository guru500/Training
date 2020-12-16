package com.copious.training.controller;

import com.copious.training.exceptions.CredentialExcpetion;
import com.copious.training.exceptions.EmployeeNotFoundException;
import com.copious.training.model.Employee;
import com.copious.training.security.JwtServices;
import com.copious.training.service.AppUserDetailsService;
import com.copious.training.service.EmployeeService;
import com.copious.training.util.EnumExceptions;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@Api(value = "Employee login control.")
@RestController
@RequestMapping("/login")
public class AuthController {

    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private AppUserDetailsService userDetailsService;
    @Autowired
    private JwtServices jwtServices;


    @ApiOperation(value = "Authenticate users.", notes = "Authenticate users and return jwt token.",
            response = Employee.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful user authentication."),
            @ApiResponse(code = 403, message = "Wrong credentials passed."),
            @ApiResponse(code = 404, message = "User with entered user name does not exists."),
            @ApiResponse(code = 500, message = "Something went wrong, Internal server error")
    })
    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
    public ResponseEntity<Employee> createAuthenticationToken(
            @RequestParam(value = "userName") String userName,
            @RequestParam(value = "userPassword") String userPassword) {

        Optional<Employee> employee = employeeService.getUserByUserName(userName);

        if (employee.isPresent()) {
            if (employee.get().getPassword().equals(userPassword)) {
                Employee empDetails = new Employee();
                final UserDetails userDetails = userDetailsService.loadUserByUsername(userName);
                final String jwt = jwtServices.generateToken(userDetails);
                empDetails = employee.get();
                empDetails.setJwtToken(jwt);
                return new ResponseEntity<>(empDetails, HttpStatus.OK);
            } else {
                throw new CredentialExcpetion(EnumExceptions.INCORRECT_CREDENTIALS);
            }
        } else {
            throw new CredentialExcpetion(EnumExceptions.USER_NOT_FOUND);
        }
    }
}


