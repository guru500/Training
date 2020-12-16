package com.copious.training.service;

import com.copious.training.exceptions.EmployeeNotFoundException;
import com.copious.training.model.Employee;
import com.copious.training.repository.EmployeeDao;
import com.copious.training.util.EnumExceptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public class AppUserDetailsService implements UserDetailsService {

    @Autowired
    EmployeeDao employeeDao;


    @Override
    public UserDetails loadUserByUsername(String username) {
        Employee employee = new Employee();

        employee = employeeDao.findWhereUserNameEquals(username);

        if (employee == null) {
            throw new EmployeeNotFoundException(EnumExceptions.EMPLOYEE_NOT_FOUND_EXCEPTIONS);
        }

        return new EmployeePrincipal(employee);
    }
}
