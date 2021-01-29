package com.copious.training.configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;


@Component
public class StaticDataLoader implements ApplicationRunner {

    private final Logger logger = LoggerFactory.getLogger(StaticDataLoader.class);
    private final Map<String, String> loaderMap = new HashMap<>();

    @Value("${data.name}")
    private String name;

    //@PropertySource("classpath:goalLabels.properties")
    @Value("${data.age}")
    private String age;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        logger.info("Application started" + args.getOptionNames());

        try {
            fetchData();
            getLoaderMap().forEach((key, value) -> {
                System.out.println(key);
                System.out.println(value);
            });

        } catch (Exception exception) {
            logger.error("Caught Error");
        }
    }

    public Map<String, String> getLoaderMap() {
        return loaderMap;
    }

    private void fetchData() {
        this.loaderMap.put("name", name);
        this.loaderMap.put("age", age);
    }
}
