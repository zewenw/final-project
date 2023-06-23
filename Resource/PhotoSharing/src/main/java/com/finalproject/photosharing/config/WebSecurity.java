package com.finalproject.photosharing.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(securedEnabled = true, prePostEnabled = true)
public class WebSecurity {

    @Bean
    SecurityFilterChain configure(HttpSecurity httpSecurity) throws Exception{

        httpSecurity.authorizeHttpRequests(auth ->
                auth
                        .requestMatchers(HttpMethod.GET, "/users/status/check")
                        //Scope need prefix
//                        .hasAnyAuthority("SCOPE_profile")
//                        don't attach 'ROLE_' prefix
//                        for single role,
                        .hasRole("USER")
//                        for multiple roles
//                        .hasAnyRole("developer", "user")
                        .anyRequest().authenticated())
                .oauth2ResourceServer(oauth2 -> oauth2.jwt(jwt -> {}));
        return httpSecurity.build();
    }

}