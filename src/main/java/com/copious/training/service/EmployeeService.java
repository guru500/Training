package com.copious.training.service;

import com.copious.training.exceptions.EmployeeNotFoundException;
import com.copious.training.model.Employee;

import java.io.IOException;
import java.util.List;

public interface EmployeeService {

    List<Employee> sortByAge() throws EmployeeNotFoundException;
}
