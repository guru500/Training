package com.copious.training.factory;

import com.copious.training.model.Employee;

import java.util.List;

public interface EmployeeFilter {
    List<Employee> getEmployees(List<Employee> employees);
}
