package com.copious.training.service.implementation;

import com.copious.training.exceptions.EmployeeNotFoundException;
import com.copious.training.factory.EmployeeFactory;
import com.copious.training.model.Employee;
import com.copious.training.repository.EmployeeDaoImpl;
import com.copious.training.service.EmployeeService;
import com.copious.training.util.EnumExceptions;
import com.copious.training.util.FilterCriteria;
import com.copious.training.util.Param;
import org.joda.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeDaoImpl employeeDao;

    @Autowired
    private EmployeeFactory employeeFactory;

    @Override
    public Map<String, List<Employee>> pairOldAndYoungEmployee() throws EmployeeNotFoundException, ExecutionException
            , InterruptedException {

        Map<String, List<Employee>> employeeMap = new HashMap<>();

        final List<Employee> employeeList = employeeDao.getEmployees();

        //Completable future for multiple operations
        /* supplyAsync returns completable future completed by getEmployeesAgeGreaterThan50(employeeList) in
        ForkJoinPool.commonPool(). */
        CompletableFuture<List<Employee>> employeeWithAgeGreaterThan50 =
                CompletableFuture.supplyAsync(() -> getEmployeesAgeGreaterThan50(employeeList));

        CompletableFuture<List<Employee>> employeeWithAgeLessThan30 =
                CompletableFuture.supplyAsync(() -> getEmployeesAgeLessThan30(employeeList));

        //Combine 2 completable future and returns completable future
        employeeWithAgeGreaterThan50.thenCombine(employeeWithAgeLessThan30, Param::new)
                //Resolves completable future. Data pipeline.
                .thenAccept(param -> {
                    employeeMap.put("Greater than 50", param.left);
                    employeeMap.put("Less than 30", param.right);
                })
                //Exception pipeline.
                .exceptionally(throwable -> {
                    throw new EmployeeNotFoundException(EnumExceptions.EMPLOYEE_NOT_FOUND_EXCEPTIONS);
                })
                //Blocking call to wait for execution completion of futures
                .join();

        return employeeMap;
    }

    @Override
    public Map<String, List<Employee>> sequential() throws EmployeeNotFoundException {
        System.out.println(LocalDateTime.now());
        Map<String, List<Employee>> employeeMap = new HashMap<>();
        final List<Employee> employeeList = employeeDao.getEmployees();

        List<Employee> greaterThan50 = getEmployeesAgeGreaterThan50(employeeList);
        List<Employee> lessThan30 = getEmployeesAgeLessThan30(employeeList);

        //business logic
        employeeMap.put("Greater than 50", greaterThan50);
        employeeMap.put("Less than 30", lessThan30);
        System.out.println(LocalDateTime.now());
        return employeeMap;
    }


    private List<Employee> getEmployeesAgeGreaterThan50(List<Employee> employees) {

        return employees.stream().filter(emp -> (LocalDate.now().getYear() - emp.getDob().getYear() > 50)).collect(Collectors.toList());

    }

    private List<Employee> getEmployeesAgeLessThan30(List<Employee> employees) {

        return employees.stream().filter(emp -> (LocalDate.now().getYear() - emp.getDob().getYear() < 30)).collect(Collectors.toList());

    }

    @Override
    public Optional<Employee> getUserByUserName(String userName) throws EmployeeNotFoundException {
        Optional<Employee> employees =
                employeeDao.getEmployees().stream().filter(employee -> employee.getUserName().equalsIgnoreCase(userName)).findAny();

        return employees;
    }


    @Override
    public List<Employee> filterEmployee(int lowerAgeLimit, int upperAgeLimit, FilterCriteria gender) throws EmployeeNotFoundException {
        Predicate<Employee> agePredicate = age -> age.getAge() > lowerAgeLimit && age.getAge() < upperAgeLimit;
        Predicate<Employee> genderPredicate = gen -> gen.getGender().equalsIgnoreCase(String.valueOf(gender));

        List<Employee> employeeList =
                employeeDao.getEmployees().stream().filter(agePredicate.and(genderPredicate)).collect(Collectors.toList());


        return employeeList;
    }

    @Override
    public List<Employee> genderFilter(FilterCriteria criteria) {
        return employeeFactory.employeeFilter(criteria).getEmployees(employeeDao.getEmployees());
    }

    @Override
    public List<Employee> sortEmployees() {
        List<Employee> employeeList = employeeDao.getEmployees();
        employeeList.sort(Comparator.comparing(Employee::getName).thenComparing(Employee::getSalary));
        return employeeList;
    }
}
