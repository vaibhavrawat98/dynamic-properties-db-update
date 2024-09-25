package com.example.dynamicpropertychange.controller;

import com.example.dynamicpropertychange.config.DynamicPropertyUpdater;
import com.example.dynamicpropertychange.service.DynamicPropertiesSevice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/properties")
public class PropertyController {

    @Autowired
    private DynamicPropertyUpdater propertyUpdater;

    @Autowired
    DynamicPropertiesSevice dynamicPropertiesSevice;


    @GetMapping("/get")
    public String getProperty(@RequestParam String propertyName) {
        return propertyUpdater.getPropertyValue(propertyName);
    }

    @PostMapping("/update")
    public String updateProperty(@RequestParam String propertyName, @RequestParam String newValue) {
        propertyUpdater.updateProperty(propertyName, newValue);

        return "Property updated";
    }

    @PostMapping("/updateallfromDB")
    public String updatePropertyFromDB() {
        String result = dynamicPropertiesSevice.updateAllPropertyFromDB();
        return result;
    }
}
