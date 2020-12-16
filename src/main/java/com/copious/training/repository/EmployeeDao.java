package com.copious.training.repository;

import com.copious.training.exceptions.EmployeeNotFoundException;
import com.copious.training.model.Employee;

import java.util.List;

public interface EmployeeDao {
    List<Employee> getEmployees() throws EmployeeNotFoundException;

    Employee findWhereUserNameEquals(String userName) throws EmployeeNotFoundException;
}
