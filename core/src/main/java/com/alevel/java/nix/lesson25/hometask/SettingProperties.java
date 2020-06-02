package com.alevel.java.nix.lesson25.hometask;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Properties;

public class SettingProperties {

    public static void main(String[] args) {
        Properties properties = new Properties();

        try (Reader reader = new BufferedReader(new FileReader("core/src/main/java/com/alevel/java/nix/lesson25/hometask/app.properties"))) {
            properties.load(reader);
        } catch (IOException e) {
            e.printStackTrace();
        }
        AppProperties appProperties = setProperties(properties, AppProperties.class);
        System.out.println("connectionURL: " + appProperties.connectionURL);
        System.out.println("maxConnections: " + appProperties.maxConnections);
        System.out.println("connectionStatus: " + appProperties.connectionStatus);
    }


    public static <T> T setProperties(Properties properties, Class<T> tClass) {
        try {
            Constructor<T> constructor = tClass.getConstructor();

            T object = constructor.newInstance();

            for (Field field : tClass.getFields()) {

                PropertyKey key = field.getAnnotation(PropertyKey.class);

                String valueProperties = properties.getProperty(key.value());

                var type = field.getType();

                if (type == int.class) {
                    field.setInt(object, Integer.parseInt(valueProperties));
                } else if (type == long.class) {
                    field.setLong(object, Long.parseLong(valueProperties));
                } else if (type == double.class) {
                    field.setDouble(object, Double.parseDouble(valueProperties));
                } else if (type == boolean.class) {
                    field.setBoolean(object, Boolean.parseBoolean(valueProperties));
                } else if (type == String.class) {
                    field.set(object, valueProperties);
                }
            }
            return object;
        } catch (InstantiationException | InvocationTargetException | IllegalAccessException | NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }
}
