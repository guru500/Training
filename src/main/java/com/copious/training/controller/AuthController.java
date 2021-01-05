package com.copious.training.controller;

import com.copious.training.exceptions.CredentialException;
import com.copious.training.exceptions.GenericResponse;
import com.copious.training.model.Employee;
import com.copious.training.service.EmpUserDetailsService;
import com.copious.training.service.EmployeeService;
import com.copious.training.util.EnumExceptions;
import com.copious.training.util.JwtUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@Api(value = "Employee login control.")
@RestController
@RequestMapping(value = "/login")
public class AuthController {

    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private EmpUserDetailsService userDetailsService;
    @Autowired
    private JwtUtils jwtServices;

    @ApiOperation(value = "Authenticate users.", notes = "Authenticate users and return jwt token.",
            response = Employee.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful user authentication."),
            @ApiResponse(code = 403, message = "Wrong credentials passed."),
            @ApiResponse(code = 404, message = "User with entered user name does not exists."),
            @ApiResponse(code = 500, message = "Something went wrong, Internal server error")
    })
    @PostMapping("/authenticate")
    public ResponseEntity<GenericResponse<Object>> createAuthenticationToken(
            @RequestParam(value = "userName") String userName,
            @RequestParam(value = "userPassword") String userPassword) {

        Optional<Employee> employee = employeeService.getUserByUserName(userName);

        if (employee.isPresent()) {
            if (employee.get().getPassword().equals(userPassword)) {
                final UserDetails userDetails = userDetailsService.loadUserByUsername(userName);
                HttpHeaders responseHeader = getHttpHeaders(userDetails);

                return new ResponseEntity<>(new GenericResponse<>(true, HttpStatus.OK.name(), employee.get()),
                        responseHeader, HttpStatus.OK);
            } else {
                throw new CredentialException(EnumExceptions.INCORRECT_CREDENTIALS);
            }
        } else {
            throw new CredentialException(EnumExceptions.USER_NOT_FOUND);
        }
    }

    private HttpHeaders getHttpHeaders(UserDetails userDetails) {
        final String jwt = jwtServices.generateToken(userDetails);
        HttpHeaders responseHeader = new HttpHeaders();
        responseHeader.set("jwt-token", jwt);
        return responseHeader;
    }


}


