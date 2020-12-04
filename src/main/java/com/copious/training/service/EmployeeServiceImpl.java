package com.copious.training.service;

import com.copious.training.exceptions.EmployeeNotFoundException;
import com.copious.training.factory.EmployeeFactory;
import com.copious.training.model.Employee;
import com.copious.training.repository.EmployeeDaoImpl;
import com.copious.training.util.FilterCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    EmployeeDaoImpl employeeDao;

    @Autowired
    EmployeeFactory employeeFactory;

    @Override
    public List<Employee> sortByAge() throws EmployeeNotFoundException {
        List<Employee> employees =
                employeeDao.getEmployees().stream().sorted(Comparator.comparing(Employee::getAge)).collect(Collectors.toList());

        return employees;
    }


    public List<Employee> filterEmployee(int lowerAgeLimit, int upperAgeLimit, FilterCriteria gender) throws EmployeeNotFoundException {
        Predicate<Employee> agePredicate = age -> age.getAge() > lowerAgeLimit && age.getAge() < upperAgeLimit;
        Predicate<Employee> genderPredicate = gen -> gen.getGender().equalsIgnoreCase(String.valueOf(gender));
        List<Employee> employeeList =
                employeeDao.getEmployees().stream().filter(agePredicate.and(genderPredicate)).collect(Collectors.toList());


        return employeeList;
    }

    @Override
    public List<Employee> genderFilter(FilterCriteria criteria) {
        return employeeFactory.employeeFilter(criteria).getEmployees(employeeDao.getEmployees());
    }
}
