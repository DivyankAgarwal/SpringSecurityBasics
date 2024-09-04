package com.example.springsecurityudemy.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/balance/")
public class BalanceController {


    @GetMapping("")
    public String balanceHome(){
        return "Balance Controller";
    }
}
