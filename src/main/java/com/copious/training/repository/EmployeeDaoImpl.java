package com.copious.training.repository;

import com.copious.training.exceptions.EmployeeNotFoundException;
import com.copious.training.model.Employee;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
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
            Type listType = new TypeToken<ArrayList<Employee>>(){}.getType();
            List<Employee> empList = gson.fromJson(reader, listType);

            return empList;
        } catch (IOException e) {
            e.printStackTrace();
            throw new EmployeeNotFoundException("Employee not found while reading JSON employee record");
        }
    }

}
