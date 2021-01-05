package com.copious.training.controller;

import com.copious.training.util.DbConfigSingleton;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/config")
public class LoadConfigController {

    @GetMapping("/singleton-test")
    public ResponseEntity<Object> singletonEx() {

        DbConfigSingleton.getConfigInstance();


        return new ResponseEntity<>(HttpStatus.OK);
    }
}
