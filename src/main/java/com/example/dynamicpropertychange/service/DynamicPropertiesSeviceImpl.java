package com.example.dynamicpropertychange.service;

import com.example.dynamicpropertychange.config.DynamicPropertyUpdater;
import com.example.dynamicpropertychange.repo.DynamicPropertiesRepository;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

@Log
@Service
public class DynamicPropertiesSeviceImpl implements DynamicPropertiesSevice {

    @Autowired
    DynamicPropertiesRepository dynamicPropertiesRepository;

    @Autowired
    private DynamicPropertyUpdater propertyUpdater;

    @Override
    public List<Map<String, String>> getAllProperties(int to_update) {
        return dynamicPropertiesRepository.getAllProperties(to_update);
    }

    @Override
    public int updateAllToUpdatedProperties() {
        return dynamicPropertiesRepository.updateAllToUpdatedProperties();
    }

    @Override
    public String updateAllPropertyFromDB() {

        String result = "false";
        List<Map<String, String>> propertyList = dynamicPropertiesRepository.getAllProperties(1);

        try {
            if (propertyList != null && propertyList.size() > 0) {
                for (int i = 0; i < propertyList.size(); i++) {
                    propertyUpdater.updateProperty(propertyList.get(i).get("dynamic_property_name"), propertyList.get(i).get("dynamic_property_value"));
                }

                int a = dynamicPropertiesRepository.updateAllToUpdatedProperties();

                // Programmatically trigger the /refresh endpoint
                try {
                    RestTemplate restTemplate = new RestTemplate();

                    // Set headers
                    HttpHeaders headers = new HttpHeaders();
                    headers.setContentType(MediaType.APPLICATION_JSON);

                    // Create the request entity
                    HttpEntity<String> entity = new HttpEntity<>(null, headers);

                    // Call the /refresh endpoint
                    String URL = "http://localhost:8080/actuator/refresh";
                    String response = restTemplate.postForObject(URL, entity, String.class);
                    log.info("RESPONSE :::: " + response);

                } catch (Exception e) {
                    e.printStackTrace();
                }
                //return "Property updated and refreshed";

                result = "success";
            } else {
                result = "list_not_found";
            }
        } catch (Exception e) {
            e.printStackTrace();
            result = "error";
        }

        return result;
    }
}
