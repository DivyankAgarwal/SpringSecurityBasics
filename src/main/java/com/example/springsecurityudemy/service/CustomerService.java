package com.example.springsecurityudemy.service;

import com.example.springsecurityudemy.domain.Customer;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.example.springsecurityudemy.repository.CustomerRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;

    private final PasswordEncoder passwordEncoder;


//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        String email;
//        String password;
//
//        List<GrantedAuthority> authorities;
//
//        List<Customer> customerList = customerRepository.findCustomerByEmail(username);
//
//        if(customerList.isEmpty()){
//            throw new UsernameNotFoundException("User details not found");
//        } else{
//            email = customerList.get(0).getEmail();
//            password = customerList.get(0).getPassword();
//            authorities = new ArrayList<>();
//            authorities.add(new SimpleGrantedAuthority(customerList.get(0).getRole()));
//        }
//
//        return new User(email,password,authorities);
//    }

    public List<Customer> registerNewCustomer(List<Customer> customerList) {
        for (var entry : customerList) {
            entry.setPassword(passwordEncoder.encode(entry.getPassword()));
            entry.setCreatedAt(LocalDateTime.now());

        }
        return customerRepository.saveAll(customerList);

    }

    public List<Customer> getAllUserInformation(){
        return customerRepository.findAll();
    }

    public Customer loginCustomer(Authentication authentication){
        List<Customer> customerList = customerRepository.findCustomerByEmail(authentication.getName());
        if ( !customerList.isEmpty()){
            System.out.println(customerList.get(0));
            return customerList.get(0);
        } else {
            return null;
        }
    }
}
