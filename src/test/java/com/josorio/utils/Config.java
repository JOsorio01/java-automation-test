package com.josorio.utils;

import java.io.InputStream;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Config {

    private static final Logger log = LoggerFactory.getLogger(Config.class);
    private static final String DEFAULT_PROPERTIES = "config/default.properties";
    private static Properties properties;

    public static void initialize() {
        // load default properties
        properties = loadProperties();
        // check for any override
        for (String key : properties.stringPropertyNames()) {
            if (System.getProperties().containsKey(key)) {
                properties.setProperty(key, System.getProperty(key));
            }
        }
        // print
        log.debug("-------------------- Test Properties ---------------------");
        for (String key : properties.stringPropertyNames()) {
            log.debug("{}={}", key, properties.getProperty(key));
        }
        log.debug("----------------------------------------------------------");
    }

    public static String get(String key) {
        return properties.getProperty(key);
    }

    public static Properties loadProperties() {
        Properties defaultProperties = new Properties();
        try (InputStream stream = ResourceLoader.getResource(DEFAULT_PROPERTIES)) {
            defaultProperties.load(stream);
        } catch (Exception e) {
            log.error("Unable to read property file {}", DEFAULT_PROPERTIES, e);
        }
        return defaultProperties;
    }
}
