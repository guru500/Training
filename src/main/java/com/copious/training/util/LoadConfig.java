package com.copious.training.util;

import com.copious.training.exceptions.DbConfigException;
import com.copious.training.model.DbConfig;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

@Component
public class LoadConfig {

    @Autowired
    private ResourceLoader loader;

    public List<DbConfig> getDbProperties() throws DbConfigException {
        try {
            List<DbConfig> configList =
                    new ObjectMapper().readValue(loader.getResource("classpath:connection.json").getInputStream(),
                            new TypeReference<List<DbConfig>>() {
                            });

            return configList;
        } catch (IOException e) {
            throw new DbConfigException(EnumExceptions.DB_CONFIG_EXCEPTION);
        }
    }


}
