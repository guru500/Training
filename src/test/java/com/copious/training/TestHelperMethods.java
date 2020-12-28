package com.copious.training;

import com.copious.training.model.Employee;

import java.util.ArrayList;
import java.util.List;

public class TestHelperMethods {

    public static List<Employee> loadMockEmployees() {
        List<Employee> mockEmpList = new ArrayList<>();
        Employee emp1 = new Employee(1, "Gurcharan", 2000, 27, java.time.LocalDate.of(1993, 11, 4), "male");
        Employee emp2 = new Employee(2, "Megan", 2000, 25, java.time.LocalDate.of(1997, 1, 8), "female");
        Employee emp3 = new Employee(3, "Melina", 2000, 22, java.time.LocalDate.of(1999, 2, 12), "female");
        mockEmpList.add(emp1);
        mockEmpList.add(emp2);
        mockEmpList.add(emp3);

        return mockEmpList;
    }
}
