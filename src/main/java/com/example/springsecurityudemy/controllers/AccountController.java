package com.example.springsecurityudemy.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/accounts/")
public class AccountController {

    @GetMapping("")
    public String accountHome(){
        return "Account Controller";
    }
}
