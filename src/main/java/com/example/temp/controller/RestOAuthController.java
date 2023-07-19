package com.example.temp.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RestOAuthController {
    @GetMapping("/test")
    public String index() {
        return "Hello World";
    }
}
