package com.example.springsecurityudemy.controllers;


import com.example.springsecurityudemy.domain.Customer;
import com.example.springsecurityudemy.service.CustomerService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/login/")
@AllArgsConstructor
public class LoginController {

    private CustomerService customerService;

    @RequestMapping("user/")
    public Customer getUserDetailsAfterLogin(Authentication authentication) {
        return customerService.loginCustomer(authentication);
    }
}
