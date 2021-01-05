package com.copious.training;

import com.copious.training.factory.EmployeeFactory;
import com.copious.training.factory.EmployeeFemale;
import com.copious.training.factory.EmployeeMale;
import com.copious.training.model.Employee;
import com.copious.training.repository.EmployeeDaoImpl;
import com.copious.training.service.implementation.EmployeeServiceImpl;
import com.copious.training.util.FilterCriteria;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static com.copious.training.TestHelperMethods.loadMockEmployees;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class EmployeeServiceTest {

    @Mock
    private EmployeeDaoImpl employeeDao;
    @Mock
    private EmployeeFactory factory;
    @InjectMocks
    private EmployeeServiceImpl employeeService;

    @Test
    public void genderFilterTest() {
        when(employeeDao.getEmployees()).thenReturn(loadMockEmployees());
        when(factory.employeeFilter(FilterCriteria.FEMALE)).thenReturn(new EmployeeFemale());
        when(factory.employeeFilter(FilterCriteria.MALE)).thenReturn(new EmployeeMale());

        List<Employee> femaleEmployee = employeeService.genderFilter(FilterCriteria.FEMALE);
        List<Employee> maleEmployee = employeeService.genderFilter(FilterCriteria.MALE);

        Assert.assertEquals(2, femaleEmployee.size());
        Assert.assertEquals(1, maleEmployee.size());
    }

    @Test
    public void empFilterTest() {
        when(employeeDao.getEmployees()).thenReturn(loadMockEmployees());

        List<Employee> employees = employeeService.filterEmployee(21, 24, FilterCriteria.FEMALE);

        Assert.assertEquals(Arrays.asList("MELINA"),
                employees.stream().map(p -> p.getName().toUpperCase()).collect(Collectors.toList()));

        verify(employeeDao).getEmployees();
    }
}
