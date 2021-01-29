package com.copious.training.controllers;

import com.copious.training.controller.EmployeeController;
import com.copious.training.exceptions.GlobalExceptionHandler;
import com.copious.training.model.Employee;
import com.copious.training.service.EmployeeService;
import com.copious.training.util.FilterCriteria;
import com.copious.training.util.Response;
import com.copious.training.util.UtilityMethods;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.LinkedMultiValueMap;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(MockitoJUnitRunner.class)
public class EmployeeControllerTest {

    @Mock
    private EmployeeService employeeService;

    @InjectMocks
    private EmployeeController employeeController;

    private MockMvc mockMvc;

    @Before
    public void onStart() {
        mockMvc =
                MockMvcBuilders.standaloneSetup(employeeController).setControllerAdvice(new GlobalExceptionHandler()).build();
    }

    @Test
    public void sortEmployeeTest() throws Exception {

        List<Employee> employeeList = UtilityMethods.jsonToObject("employee.json", Employee.class);

        Mockito.when(employeeService.sortEmployees()).thenReturn(employeeList);
        MvcResult mvcResult = mockMvc.perform(get("/employee/sort-employee")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        Response response = UtilityMethods.jsonStringToObject(mvcResult.getResponse().getContentAsString(),
                Response.class);

        Assert.assertNotNull(response.getPayload());
        Assert.assertTrue(response.getSuccessful());
        Assert.assertEquals(response.getHttpStatus(), HttpStatus.OK.name());
    }

    @Test
    public void employeeFilterTest() throws Exception {

        List<Employee> employeeList = UtilityMethods.jsonToObject("employee.json", Employee.class);
        Mockito.when(employeeService.filterEmployee(anyInt(), anyInt(), any(FilterCriteria.class))).thenReturn(employeeList);

        LinkedMultiValueMap<String, String> requestParams = new LinkedMultiValueMap<>();
        requestParams.add("lowerAgeLimit", "30");
        requestParams.add("upperAgeLimit", "50");
        requestParams.add("gender", "MALE");

        MvcResult mvcResult = mockMvc.perform(post("/employee/emp-filter")
                .params(requestParams) 
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        Response response = UtilityMethods.jsonStringToObject(mvcResult.getResponse().getContentAsString(),
                Response.class);

        Assert.assertNotNull(response.getPayload());
        Assert.assertTrue(response.getSuccessful());
        Assert.assertEquals(response.getHttpStatus(), HttpStatus.OK.name());
    }
}
