package com.copious.training.util;

import com.copious.training.exceptions.DbConfigException;
import com.copious.training.model.DbConfig;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;

public final class DbConfigSingleton {

    static Properties props;
    private static DbConfigSingleton dbConfigInstance = null;
    @Autowired
    LoadConfig config;

    private DbConfigSingleton() {
        load();
    }

    public static synchronized DbConfigSingleton getConfigInstance() {
        if (dbConfigInstance == null) {
            int i = 1;
            System.out.println("Times :: " + i++);
            dbConfigInstance = new DbConfigSingleton();
        }
        return dbConfigInstance;
    }

    public void load() {
        System.out.println("here i am");
        List<DbConfig> configs = config.getDbProperties();
        configs.forEach(config -> props.setProperty(config.getKey(), config.getValue()));

    }
}
