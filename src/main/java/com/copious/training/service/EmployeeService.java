package com.copious.training.service;

import com.copious.training.exceptions.EmployeeNotFoundException;
import com.copious.training.model.Employee;
import com.copious.training.util.FilterCriteria;

import java.io.IOException;
import java.util.List;

public interface EmployeeService {

    List<Employee> sortByAge() throws EmployeeNotFoundException;

    List<Employee> genderFilter(FilterCriteria criteria);
}
