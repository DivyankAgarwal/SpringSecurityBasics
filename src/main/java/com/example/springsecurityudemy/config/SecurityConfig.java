package com.example.springsecurityudemy.config;


import com.example.springsecurityudemy.filter.JWTTokenGeneratorFilter;
import com.example.springsecurityudemy.filter.JWTTokenValidatorFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.csrf.CsrfTokenRequestAttributeHandler;
import org.springframework.web.cors.CorsConfiguration;

import java.util.Arrays;
import java.util.Collections;

@Configuration
public class SecurityConfig {

    private static final String RESTRICTED_ENDPOINTS_ROOT_PATH = "/api/";

    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {

//        http.authorizeHttpRequests().requestMatchers("/api/v1/**").authenticated()
//                .requestMatchers("/api/default/**").permitAll().and().formLogin().and().httpBasic();
//        return http.build();


        // DSL Method
//        http.csrf().disable()
//                .authorizeHttpRequests((requests -> requests
//                .requestMatchers("/api/v1/**").authenticated()
//                .requestMatchers("/api/default/**").permitAll())).formLogin(Customizer.withDefaults()).httpBasic(Customizer.withDefaults());
//
//        return http.build();

        CsrfTokenRequestAttributeHandler requestHandler = new CsrfTokenRequestAttributeHandler();
        requestHandler.setCsrfRequestAttributeName("_csrf");

        http
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                .cors(corsCustomizer -> corsCustomizer.configurationSource(request -> {
                    CorsConfiguration config = new CorsConfiguration();
                    config.setAllowedOrigins(Collections.singletonList("*"));
                    config.setAllowedMethods(Collections.singletonList("*"));
                    config.setAllowCredentials(true);
                    config.setAllowedHeaders(Collections.singletonList("*"));
                    config.setExposedHeaders(Arrays.asList("Authorization"));   //We are telling UI application that we are gonna send JWT token in the header response.
                    config.setMaxAge(3600L);
                    return config;
                })).csrf(csrf -> csrf.csrfTokenRequestHandler(requestHandler).ignoringRequestMatchers("/api/default/**")
                        .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse()))
                .addFilterBefore(new JWTTokenValidatorFilter(), BasicAuthenticationFilter.class)
                .addFilterAfter(new JWTTokenGeneratorFilter(), BasicAuthenticationFilter.class)

//                .addFilterAfter(new CsrfCookieFilter(), BasicAuthenticationFilter.class)
//                .addFilterBefore(new RequestValidationBeforeFilter(), BasicAuthenticationFilter.class)
//                .addFilterAt(new AuthoritiesLoggingAtFilter(), BasicAuthenticationFilter.class)
//                .addFilterAfter(new AuthoritiesLoggingAfterFilter(), BasicAuthenticationFilter.class)

                .authorizeHttpRequests((requests) -> requests
                        .requestMatchers(RESTRICTED_ENDPOINTS_ROOT_PATH + "accounts/").hasRole("USER")
                        .requestMatchers(RESTRICTED_ENDPOINTS_ROOT_PATH + "balance/").hasAnyRole("USER", "ADMIN")

//                        .requestMatchers("/myLoans").hasRole("USER")
//                        .requestMatchers("/myCards").hasRole("USER")

                        .requestMatchers(RESTRICTED_ENDPOINTS_ROOT_PATH + "login/user/").authenticated()
                        .requestMatchers("/swagger-ui-custom.html").permitAll()
                        .requestMatchers("/api/default/**").permitAll())
                .formLogin(Customizer.withDefaults())
                .httpBasic(Customizer.withDefaults());
        return http.build();


    }

//    @Bean
//    public InMemoryUserDetailsManager userDetailsManager() {
//
//        UserDetails admin = User.withDefaultPasswordEncoder().username("admin").password("admin123").authorities("admin").build();
//
//        UserDetails user = User.withDefaultPasswordEncoder().username("user").password("user123").authorities("read").build();
//
//        return new InMemoryUserDetailsManager(admin,user);
//    }

    //    @Bean
//    public UserDetailsService userDetailsService(DataSource dataSource){
//        return new JdbcUserDetailsManager(dataSource);
//    }
//
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
