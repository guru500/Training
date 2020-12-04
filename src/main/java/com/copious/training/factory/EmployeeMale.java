package com.copious.training.factory;

import com.copious.training.model.Employee;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class EmployeeMale implements EmployeeFilter{

    @Override
    public List<Employee> getEmployees(List<Employee> employees){

        return employees.stream().filter(gender -> gender.getGender().equalsIgnoreCase("male")).collect(Collectors.toList());

    }
}
