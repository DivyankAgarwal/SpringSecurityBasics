package com.example.springsecurityudemy.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/default/")
public class GreetingsController {


    @GetMapping("welcome/")
    public String welcome(){
        return "Welcome to spring security demo";
    }

    @GetMapping("notice/")
    public String notice(){
        return "Welcome to notice demo";
    }
}
