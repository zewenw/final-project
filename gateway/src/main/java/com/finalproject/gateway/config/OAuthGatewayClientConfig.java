package com.finalproject.gateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
public class OAuthGatewayClientConfig {

//    @Autowired
//    private CustomizedAuthorizationManager customizedAuthorizationManager;

    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {
        http
                .authorizeExchange(authorize -> authorize
//                        .anyExchange().access(customizedAuthorizationManager)
                        .anyExchange().authenticated()
                )
                .oauth2Login(Customizer.withDefaults())
                .cors(ServerHttpSecurity.CorsSpec::disable);
        return http.build();
    }

}
