package com.copious.training.controller;

import com.copious.training.exceptions.EmployeeNotFoundException;
import com.copious.training.exceptions.GenericResponse;
import com.copious.training.model.City;
import com.copious.training.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/world")
public class WorldController {

    @Autowired
    private CityService cityService;

    @GetMapping("/cities")
    public ResponseEntity<GenericResponse<Object>> getProcessedCity() throws EmployeeNotFoundException {
        System.out.println(new Timestamp(System.currentTimeMillis()));
        try {
            cityService.getAllCities();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(new Timestamp(System.currentTimeMillis()));
        return new ResponseEntity<>(new GenericResponse<>(true, HttpStatus.OK.name(),
                new String("Processed request")),
                HttpStatus.OK);
    }
}
