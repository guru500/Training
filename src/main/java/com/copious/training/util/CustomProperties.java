package com.copious.training.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource(value = "custom.properties")
public class CustomProperties {

    @Value("${name}")
    private String name;

    @Value("${year}")
    private Integer year;


    @Bean
    public void loadedProperties() throws Exception {
        System.out.println("name: " + name + " &" + "year : " + year);
    }
}
