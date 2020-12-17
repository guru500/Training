package com.copious.training.service;

import com.copious.training.exceptions.EmployeeNotFoundException;
import com.copious.training.model.Employee;
import com.copious.training.util.FilterCriteria;

import java.util.List;
import java.util.Optional;

public interface EmployeeService {

    List<Employee> sortByAge() throws EmployeeNotFoundException;

    Optional<Employee> getUserByUserName(String userName) throws EmployeeNotFoundException;

    List<Employee> filterEmployee(int lowerAgeLimit, int upperAgeLimit, FilterCriteria gender) throws EmployeeNotFoundException;

    List<Employee> genderFilter(FilterCriteria criteria);
}
