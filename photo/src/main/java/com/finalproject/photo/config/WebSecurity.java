package com.finalproject.photo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsWebFilter;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(securedEnabled = true, prePostEnabled = true)
@Order(1)
public class WebSecurity {


    @Autowired
    private DataSource dataSource;

    @Autowired
    private CustomizedAuthorizationManager customizedAuthorizationManager;

    @Bean
    SecurityFilterChain configure(HttpSecurity httpSecurity) throws Exception {

        httpSecurity.authorizeHttpRequests(auth ->
                auth
                        .requestMatchers("/actuator/**").permitAll()
                        .requestMatchers("/swagger/api-docs/**").permitAll()
                        .requestMatchers("/feign/**").permitAll()
//                        .requestMatchers(HttpMethod.GET, "/users/status/check")
                        //Scope need prefix
//                        .hasAnyAuthority("SCOPE_profile")
//                        don't attach 'ROLE_' prefix
//                        for single role,
//                        .hasRole("USER")
//                        for multiple roles
//                        .hasAnyRole("developer", "user")
                        .anyRequest().access(customizedAuthorizationManager))
                .oauth2ResourceServer(oauth2 -> oauth2.jwt(jwt -> {
                    jwt.jwtAuthenticationConverter(jwtAuthenticationConverter());
                }));
        return httpSecurity.build();
    }


    @Bean
    public CorsWebFilter corsWebFilter() {
        CorsConfiguration corsConfig = new CorsConfiguration();
        corsConfig.addAllowedOriginPattern("*");
        corsConfig.addAllowedMethod("*");
        corsConfig.addAllowedHeader("*");
        corsConfig.setAllowCredentials(true);

        org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", corsConfig);

        return new CorsWebFilter(source);
    }

    public JwtAuthenticationConverter jwtAuthenticationConverter() {

        JwtAuthenticationConverter jwtAuthenticationConverter = new JwtAuthenticationConverter();
        jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(new JwtCustomizeConverter());
        return jwtAuthenticationConverter;
    }


    @Bean
    public JdbcUserDetailsManager jdbcUserDetailsManager() {
        JdbcUserDetailsManager userDetailsManager = new JdbcUserDetailsManager();
        userDetailsManager.setDataSource(dataSource);
        return userDetailsManager;
    }

}
