package com.vodafone.warehouseservice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class WelcomeController {
    @GetMapping("welcome")
    String welcomeScreen() {
        return "<h1>Welcome Abdelaziz to Vodafone. Vodafone hope the success for you</h1>";
    }

    @GetMapping("security")
    String testSecurity() {
        return "<h1>Security</h1>";
    }

}
