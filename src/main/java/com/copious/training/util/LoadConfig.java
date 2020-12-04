package com.copious.training.util;

import com.copious.training.exceptions.DbConfigException;
import com.copious.training.model.DbConfig;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Configurable(preConstruction = true)
public class LoadConfig {

    @Autowired
    ResourceLoader loader;

    public List<DbConfig> getDbProperties() throws DbConfigException {
        System.out.println("here");
        try {
            Resource resource = loader.getResource("classpath:connection.json");
            InputStream stream = resource.getInputStream();
            ObjectMapper mapper = new ObjectMapper();
            List<DbConfig> configList = new ObjectMapper().readValue(stream, new TypeReference<List<DbConfig>>() {
            });

            System.out.println("list is  :: " + configList.size());
            return configList;
        } catch (IOException e) {
            throw new DbConfigException("Failed to load configuration file");
        }
    }


}
