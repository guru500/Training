package com.copious.training.repository;

import com.copious.training.exceptions.EmployeeIOException;
import com.copious.training.model.Employee;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Repository
public class EmployeeDaoImpl implements EmploeeDao {

    @Value("classpath:employee.json")
    Resource resourceFile;

    @Override
    public List<Employee> getEmployees() throws IOException {
        InputStream stream = resourceFile.getInputStream();

        ObjectMapper mapper = new ObjectMapper();
        List<Employee> empList = new ObjectMapper().readValue(stream, new TypeReference<List<Employee>>(){});

        return empList;
    }
}
