package com.manage.insurance.properties;

import java.util.Properties;

import com.manage.insurance.constant.Constants;

/**
 * Class read data in file message.properties
 * 
 * @author lexuanhai
 *
 */
public class MessageProperties {
    public static Properties properties;

    public static void loadProperties() {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        properties = new Properties();
        try {
            properties.load(classLoader.getResourceAsStream(Constants.FILE_MESSAGE));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 
     * @param key
     * @return
     */
    public static String getString(String key) {
        if (properties == null) {
            loadProperties();
        }
        String data = properties.getProperty(key);
        return data;
    }
}
