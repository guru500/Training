package com.copious.training;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.time.LocalDate;

@CrossOrigin
@EnableScheduling
@EnableCaching
@SpringBootApplication
public class TrainingApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(TrainingApplication.class, args);
    }

    @Bean
    public Gson gson() {
        return new GsonBuilder().registerTypeAdapter(LocalDate.class, (JsonDeserializer<LocalDate>)
                (jsonElement, type,
                 jsonDeserializationContext) -> LocalDate.parse(jsonElement.getAsJsonPrimitive().getAsString())).create();
    }
}
