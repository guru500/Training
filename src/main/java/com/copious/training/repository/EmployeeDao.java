package com.copious.training.repository;

import com.copious.training.exceptions.EmployeeNotFoundException;
import com.copious.training.model.Employee;

import java.io.IOException;
import java.util.List;

public interface EmployeeDao {
    List<Employee> getEmployees() throws EmployeeNotFoundException;
}
