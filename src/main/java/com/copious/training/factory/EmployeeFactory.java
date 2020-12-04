package com.copious.training.factory;

import com.copious.training.util.FilterCriteria;
import org.springframework.stereotype.Component;

@Component
public class EmployeeFactory {

    public EmployeeFilter employeeFilter(FilterCriteria criteria) {

        switch (criteria) {
            case MALE:
                return new EmployeeMale();

            case FEMALE:
                return new EmployeeFemale();

            default:
                throw new IllegalStateException("Unexpected value: " + criteria);
        }
    }
}
