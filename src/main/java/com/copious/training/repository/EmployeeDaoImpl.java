package com.copious.training.repository;

import com.copious.training.exceptions.EmployeeNotFoundException;
import com.copious.training.model.Employee;
import com.copious.training.util.EnumExceptions;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Repository;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

@Repository
public class EmployeeDaoImpl implements EmployeeDao {

    @Autowired
    Gson gson;

    @Value("classpath:employee.json")
    Resource resourceFile;

    @Override
    public List<Employee> getEmployees() throws EmployeeNotFoundException {
        try {
            InputStream stream = resourceFile.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
            Type listType = new TypeToken<ArrayList<Employee>>() {
            }.getType();
            List<Employee> empList = gson.fromJson(reader, listType);

            return empList;
        } catch (IOException e) {
            throw new EmployeeNotFoundException(EnumExceptions.EMPLOYEE_NOT_FOUND_EXCEPTIONS);
        }
    }

    @Override
    public Employee findWhereUserNameEquals(String userName) throws EmployeeNotFoundException {
        try {
            InputStream stream = resourceFile.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
            Type listType = new TypeToken<ArrayList<Employee>>() {
            }.getType();
            List<Employee> empList = gson.fromJson(reader, listType);

            Employee emp = empList.stream().filter(k -> k.getUserName().equalsIgnoreCase(userName)).findFirst().get();
            return emp;
        } catch (IOException e) {
            throw new EmployeeNotFoundException(EnumExceptions.EMPLOYEE_NOT_FOUND_EXCEPTIONS);
        }
    }

}
