package com.copious.training.service;

import com.copious.training.model.Employee;
import com.copious.training.repository.EmploeeDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeService{

    @Autowired
    EmploeeDao emploeeDao;

    @Override
    public List<Employee> sortByAge() {
        List<Employee> employees = new ArrayList<>();
        try {
            employees = emploeeDao.getEmployees().stream().sorted(Comparator.comparing(Employee::getAge)).collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return employees;
    }
}
