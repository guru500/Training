package com.copious.training.scheduler;

import com.copious.training.repository.EmployeeDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

import java.time.LocalDate;
import java.util.concurrent.ExecutionException;

//@Component
public class BirthdayWishScheduler {
    @Autowired
    EmployeeDao employeeDao;

    //    @Scheduled(cron = "0 0 1 * * *")
    @Scheduled(fixedRate = 10000)
    public void sendBirthdayWish() throws ExecutionException, InterruptedException {
        LocalDate currentDate = LocalDate.now();
        employeeDao.getEmployees().stream().filter(p -> p.getDob().getMonth().equals(currentDate.getMonth())
                && p.getDob().getDayOfMonth() == currentDate.getDayOfMonth()).
                forEach(p -> System.out.println("Happy birthday " + p.getName()));
    }
}

