package com.example.dynamicpropertychange.config;

import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.Environment;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.core.env.PropertySource;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@Log
public class DynamicPropertyUpdater {

    @Autowired
    private Environment environment;

    @Value("${greeting}")
    private String greetingMessage;

    /**
     * Updates a property in the environment. This method looks for the property and updates
     * it in a mutable source or creates a new dynamic property source if necessary.
     *
     * @param propertyName The name of the property to update.
     * @param newValue     The new value to set for the property.
     */
    public void updateProperty(String propertyName, String newValue) {
        MutablePropertySources propertySources = ((ConfigurableEnvironment) environment).getPropertySources();

        if (propertySources.contains("dynamicProperties")) {
            PropertySource<?> propertySource = propertySources.get("dynamicProperties");
            if (propertySource.getSource() instanceof Map) {
                Map<String, Object> sourceMap = (Map<String, Object>) propertySource.getSource();
                sourceMap.put(propertyName, newValue);
                log.info("Property updated successfully: " + propertyName + " = " + newValue);
            }
        } else {
            Map<String, Object> newSource = new HashMap<>();
            newSource.put(propertyName, newValue);
            propertySources.addFirst(new PropertySource<Map<String, Object>>("dynamicProperties", newSource) {
                @Override
                public Object getProperty(String name) {
                    return newSource.get(name);
                }
            });
            log.info("Property added successfully: " + propertyName + " = " + newValue);
        }

        // Log current properties to verify the update
        log.info("Current greetingMessage :::::: " + environment.getProperty("greeting"));
    }


    /**
     * Retrieves the value of a property from the environment.
     *
     * @param propertyName The name of the property.
     * @return The value of the property, or null if not found.
     */
    public String getPropertyValue(String propertyName) {
        return environment.getProperty(propertyName);
    }
}
