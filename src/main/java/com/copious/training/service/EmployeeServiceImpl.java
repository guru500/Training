package com.copious.training.service;

import com.copious.training.model.Employee;
import com.copious.training.repository.EmploeeDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    EmploeeDao employeeDao;

    @Override
    public List<Employee> sortByAge() {
        List<Employee> employees = new ArrayList<>();
        try {
            employees = employeeDao.getEmployees().stream().sorted(Comparator.comparing(Employee::getAge)).collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return employees;
    }

    public List<Employee> betweenAgeEmployee(int lowerAgeLimit, int upperAgeLimit, String gender) {
        Predicate<Employee> agePredicate = age -> age.getAge() > lowerAgeLimit && age.getAge() < upperAgeLimit;
        Predicate<Employee> genderPredicate = gen -> gen.getGender().equalsIgnoreCase(gender);
        List<Employee> employeeList = new ArrayList<>();

        try {
            employeeList = employeeDao.getEmployees().stream().filter(agePredicate.and(genderPredicate)).collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }

        return employeeList;
    }
}
