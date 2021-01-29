package com.copious.training.service;

import com.copious.training.model.City;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface CityService {

    CompletableFuture<List<City>> getAllCities() throws InterruptedException;

    CompletableFuture<List<City>> doSome1() throws InterruptedException;

    CompletableFuture<List<City>> doSome2() throws InterruptedException;
}
