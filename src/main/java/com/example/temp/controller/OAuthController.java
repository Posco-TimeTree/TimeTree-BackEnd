package com.example.temp.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class OAuthController {
    private final static Logger logger = LoggerFactory.getLogger(OAuthController.class);
    @GetMapping("/loginForm")
    public String home() {
        logger.info("로그인 페이지");
        return "loginForm";
    }

    @GetMapping("/private")
    public String privatePage() {
        logger.info("사설 페이지");
        return "privatePage";
    }
    @GetMapping("/admin")
    public String adminPage() {
        logger.info("관리자 페이지");
        return "adminPage";
    }
}