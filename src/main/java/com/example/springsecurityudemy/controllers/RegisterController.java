package com.example.springsecurityudemy.controllers;

import com.example.springsecurityudemy.service.CustomerService;
import com.example.springsecurityudemy.domain.Customer;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/default/register/")
@AllArgsConstructor
public class RegisterController {

    private final CustomerService customerService;

    @PostMapping()
    public List<Customer> registerCustomer(@RequestBody List<Customer> customerList){

        return customerService.registerNewCustomer(customerList);



    }

    @GetMapping()
    public List<Customer> getAllUserInformation(){
        return customerService.getAllUserInformation();
    }
}
