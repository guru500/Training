package com.copious.training.repository;

import com.copious.training.model.Employee;

import java.io.IOException;
import java.util.List;

public interface EmploeeDao {
    List<Employee> getEmployees() throws IOException;
}
