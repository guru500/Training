package com.copious.training.factory;

import com.copious.training.model.Employee;

import java.util.List;
import java.util.stream.Collectors;

public class EmployeeFemale implements EmployeeFilter {

    @Override
    public List<Employee> getEmployees(List<Employee> employees) {
        return employees.stream().filter(p -> p.getGender().equalsIgnoreCase("female")).collect(Collectors.toList());
    }
}
