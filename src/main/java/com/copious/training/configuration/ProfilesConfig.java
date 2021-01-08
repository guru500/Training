package com.copious.training.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@ConfigurationProperties("profile")
public class ProfilesConfig {
    private String name;
    private String year;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    @Profile("dev")
    @Bean
    public void devProfileActive() {
        System.out.println(name);
        System.out.println(year);
    }

    @Profile("prod")
    @Bean
    public void prodProfileActive() {
        System.out.println(name);
        System.out.println(year);
    }

}
