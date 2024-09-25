package com.example.dynamicpropertychange.service;

import java.util.List;
import java.util.Map;

public interface DynamicPropertiesSevice {

    List<Map<String, String>> getAllProperties(int to_update);

    int updateAllToUpdatedProperties();

    String updateAllPropertyFromDB();
}
