package com.example.dynamicpropertychange.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RefreshScope
@RestController
public class GreetingController {

    @Value("${greeting}")
    private String greetingMessage;

    @GetMapping("/greet")
    public String greet() {

        System.err.println("Initialized greeting: " + greetingMessage);
        return greetingMessage;
    }
}
