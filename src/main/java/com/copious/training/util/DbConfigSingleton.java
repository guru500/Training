package com.copious.training.util;

import com.copious.training.model.DbConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Properties;

@Component
public final class DbConfigSingleton {

    private static DbConfigSingleton dbConfigInstance = null;
    @Autowired
    LoadConfig config;
    private Properties props;

    private DbConfigSingleton() {
    }

    public static synchronized DbConfigSingleton getConfigInstance() {
        if (dbConfigInstance == null) {
            System.out.println("here is ");
            dbConfigInstance = new DbConfigSingleton();
        }
        return dbConfigInstance;
    }

    @PostConstruct
    public void load() {
        props = new Properties();
        List<DbConfig> configs = config.getDbProperties();
        configs.forEach(config -> props.setProperty(config.getKey(), config.getValue()));
    }

}
