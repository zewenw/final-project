package com.finalproject.gateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.session.CookieWebSessionIdResolver;
import org.springframework.web.server.session.DefaultWebSessionManager;
import org.springframework.web.server.session.WebSessionManager;

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

    @Bean
    @Primary
    public WebSessionManager webSessionManager() {
        DefaultWebSessionManager defaultWebSessionManager = new DefaultWebSessionManager();
        defaultWebSessionManager.setSessionIdResolver(new CookieWebSessionIdResolver(){
            @Override
            public void expireSession(ServerWebExchange exchange) {
                //banned session, do nothing
            }
        });

        return defaultWebSessionManager;
    }

}
