package com.copious.training.service.implementation;

import com.copious.training.model.City;
import com.copious.training.repository.CityDao;
import com.copious.training.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
public class CityServiceImpl implements CityService {

    @Autowired
    private CityDao cityDao;

    @Async("taskExecutor")
    @Override
    public CompletableFuture<List<City>> getAllCities() throws InterruptedException {
        System.out.println("Started : getAllCities()" + Thread.currentThread().getName());
        List<City> cityList = cityDao.findCities();
        doSome1();
        doSome2();
        return CompletableFuture.completedFuture(cityList);
    }

    @Async("taskExecutor")
    @Override
    public CompletableFuture<List<City>> doSome1() throws InterruptedException {
        System.out.println("Started : doSome1() " + Thread.currentThread().getName());
        List<City> cityList = cityDao.findCities();
        Thread.sleep(3000);
        return CompletableFuture.completedFuture(cityList);
    }

    @Async("taskExecutor")
    @Override
    public CompletableFuture<List<City>> doSome2() throws InterruptedException {
        System.out.println("Started : doSome2()" + Thread.currentThread().getName());
        List<City> cityList = cityDao.findCities();
        Thread.sleep(4000);
        return CompletableFuture.completedFuture(cityList);
    }

}
