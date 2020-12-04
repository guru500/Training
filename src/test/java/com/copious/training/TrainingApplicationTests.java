package com.copious.training;

import com.copious.training.factory.EmployeeFactory;
import com.copious.training.factory.EmployeeFemale;
import com.copious.training.factory.EmployeeFilter;
import com.copious.training.factory.EmployeeMale;
import com.copious.training.model.Employee;
import com.copious.training.repository.EmployeeDaoImpl;
import com.copious.training.service.EmployeeServiceImpl;
import com.copious.training.util.FilterCriteria;
import com.jayway.jsonpath.Criteria;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TrainingApplicationTests {

    private static List<Employee> mockEmpList = new ArrayList<>();
    @Mock
    private EmployeeDaoImpl employeeDao;
    @Mock
    private EmployeeFactory factory;
    @InjectMocks
    private EmployeeServiceImpl employeeService;

    @BeforeClass
    public static void loadMockEmployees() {
        Employee emp1 = new Employee(1, "Gurcharan", 2000, 27, java.time.LocalDate.of(1993, 11, 4), "male");
        Employee emp2 = new Employee(2, "Megan", 2000, 25, java.time.LocalDate.of(1997, 1, 8), "female");
        Employee emp3 = new Employee(3, "Melina", 2000, 22, java.time.LocalDate.of(1999, 2, 12), "female");
        mockEmpList.add(emp1);
        mockEmpList.add(emp2);
        mockEmpList.add(emp3);
    }

    @Before
    public void mockDao() {
        when(employeeDao.getEmployees()).thenReturn(mockEmpList);
    }

    @Before
    public void initializeFactory() {
        when(factory.employeeFilter(FilterCriteria.FEMALE)).thenReturn(new EmployeeFemale());
        when(factory.employeeFilter(FilterCriteria.MALE)).thenReturn(new EmployeeMale());
    }

    @Test
    public void sortAgeTest() {
        List<Employee> sortByAge = employeeService.sortByAge();
        Assert.assertEquals(Arrays.asList(22, 25, 27),
                sortByAge.stream().map(p -> p.getAge()).collect(Collectors.toList()));
    }

    @Test
    public void genderFilterTest() {
        List<Employee> femaleEmployee = employeeService.genderFilter(FilterCriteria.FEMALE);
        List<Employee> maleEmployee = employeeService.genderFilter(FilterCriteria.MALE);
        Assert.assertEquals(2, femaleEmployee.size());
        Assert.assertEquals(1, maleEmployee.size());
    }

    @Test
    public void empFilterTest() {
        List<Employee> employees = employeeService.filterEmployee(21, 24, FilterCriteria.FEMALE);
        Assert.assertEquals(Arrays.asList("MELINA"),
                employees.stream().map(p -> p.getName().toUpperCase()).collect(Collectors.toList()));
    }
}
