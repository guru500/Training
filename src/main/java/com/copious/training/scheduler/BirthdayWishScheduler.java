package com.copious.training.scheduler;

import com.copious.training.model.Employee;
import com.copious.training.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.function.Predicate;

@Component
public class BirthdayWishScheduler {
    @Autowired
    EmployeeService employeeService;

    @Scheduled(cron = "0 0 1 * * *")
    public void sendBirthdayWish() {
        LocalDate currentDate = LocalDate.now();
        employeeService.sortByAge().stream().filter(p -> p.getDob().getMonth().equals(currentDate.getMonth())
                && p.getDob().getDayOfMonth() == currentDate.getDayOfMonth()).
                forEach(p -> System.out.println("Happy birthday " + p.getName()));
    }
}

