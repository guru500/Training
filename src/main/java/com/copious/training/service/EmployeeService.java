package com.copious.training.service;

import com.copious.training.exceptions.EmployeeNotFoundException;
import com.copious.training.model.Employee;
import com.copious.training.util.FilterCriteria;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ExecutionException;

public interface EmployeeService {

    Map<String, List<Employee>> pairOldAndYoungEmployee() throws EmployeeNotFoundException, ExecutionException, InterruptedException;

    Map<String, List<Employee>> sequential() throws EmployeeNotFoundException;

    Optional<Employee> getUserByUserName(String userName) throws EmployeeNotFoundException;

    List<Employee> filterEmployee(int lowerAgeLimit, int upperAgeLimit, FilterCriteria gender) throws EmployeeNotFoundException;

    List<Employee> genderFilter(FilterCriteria criteria);

    List<Employee> sortEmployees();
}
