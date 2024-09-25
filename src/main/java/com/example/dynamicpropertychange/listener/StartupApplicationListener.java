package com.example.dynamicpropertychange.listener;

import com.example.dynamicpropertychange.service.DynamicPropertiesSeviceImpl;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Log
@Component
public class StartupApplicationListener implements ApplicationListener<ApplicationReadyEvent> {

    @Autowired
    private DynamicPropertiesSeviceImpl dynamicPropertiesSeviceImpl;

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        // Call your method here
        String result = dynamicPropertiesSeviceImpl.updateAllPropertyFromDB();
        log.severe("Properties updated on startup: " + result);
    }
}
