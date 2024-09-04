package com.example.springsecurityudemy.config;

import com.example.springsecurityudemy.domain.Authorities;
import com.example.springsecurityudemy.domain.Customer;
import com.example.springsecurityudemy.repository.CustomerRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
@AllArgsConstructor
public class CustomAuthenticationProvider implements AuthenticationProvider {

    private final CustomerRepository customerRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String password = authentication.getCredentials().toString();
        System.out.println("Username: " + username);
        System.out.println("Password: " + password);

        List<Customer> customerList = customerRepository.findCustomerByEmail(username);

        if(!customerList.isEmpty()){
            if (passwordEncoder.matches(password,customerList.get(0).getPassword())){

                return new UsernamePasswordAuthenticationToken(username,
                        password,
                        getGrantedAuthorities(customerList.get(0).getAuthoritiesSet()));
            }else{
                throw new BadCredentialsException("Invalid Password");
            }
        }else{
            throw new BadCredentialsException("No user registered with this details");
        }
    }

    private List<GrantedAuthority> getGrantedAuthorities(Set<Authorities> authoritiesSet){
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        for (Authorities authority : authoritiesSet){
            grantedAuthorities.add(new SimpleGrantedAuthority(authority.getName()));
        }
        return grantedAuthorities;

    }

    @Override
    public boolean supports(Class<?> authentication) {
        return (UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication));
    }
}
