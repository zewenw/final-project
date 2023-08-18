package com.finalproject.gateway.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.oauth2.client.ReactiveOAuth2AuthorizedClientManager;
import org.springframework.security.oauth2.client.ReactiveOAuth2AuthorizedClientProvider;
import org.springframework.security.oauth2.client.ReactiveOAuth2AuthorizedClientProviderBuilder;
import org.springframework.security.oauth2.client.oidc.web.server.logout.OidcClientInitiatedServerLogoutSuccessHandler;
import org.springframework.security.oauth2.client.registration.ReactiveClientRegistrationRepository;
import org.springframework.security.oauth2.client.web.DefaultReactiveOAuth2AuthorizedClientManager;
import org.springframework.security.oauth2.client.web.server.ServerOAuth2AuthorizedClientRepository;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.server.authentication.logout.DelegatingServerLogoutHandler;
import org.springframework.security.web.server.authentication.logout.SecurityContextServerLogoutHandler;
import org.springframework.security.web.server.authentication.logout.ServerLogoutSuccessHandler;
import org.springframework.security.web.server.authentication.logout.WebSessionServerLogoutHandler;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsWebFilter;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;

@Slf4j
@Configuration
public class OAuthGatewayClientConfig {

//    @Autowired
//    private CustomizedAuthorizationManager customizedAuthorizationManager;


    @Autowired
    private ReactiveClientRegistrationRepository clientRegistrationRepository;




    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {
        http
                .csrf(ServerHttpSecurity.CsrfSpec::disable)
                .authorizeExchange(authorize -> authorize
                        .pathMatchers("/oauth2/authorization/**").permitAll()
//                        .anyExchange().access(customizedAuthorizationManager)
                                .anyExchange().authenticated()
                )
                .oauth2Login(Customizer.withDefaults())
                .logout(logoutSpec -> logoutSpec
                        .logoutHandler(logoutHandler())
                        .logoutSuccessHandler(oidcLogoutSuccessHandler()))
                .cors(ServerHttpSecurity.CorsSpec::disable);
        return http.build();
    }

    @Bean
    public CorsWebFilter corsWebFilter() {
        CorsConfiguration corsConfig = new CorsConfiguration();
        corsConfig.addAllowedOriginPattern("*");
        corsConfig.addAllowedMethod("*");
        corsConfig.addAllowedHeader("*");
        corsConfig.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", corsConfig);

        return new CorsWebFilter(source);
    }

    /**
     * todo logout login 400 bad request
     * https://stackoverflow.com/questions/133925/javascript-post-request-like-a-form-submit/133997#133997
     * https://stackoverflow.com/questions/71251098/logout-via-spring-cloud-gateway-does-not-work-with-spring-security-oidc-and-key
     */
    private ServerLogoutSuccessHandler oidcLogoutSuccessHandler() {
        OidcClientInitiatedServerLogoutSuccessHandler oidcLogoutSuccessHandler =
                new OidcClientInitiatedServerLogoutSuccessHandler(this.clientRegistrationRepository);
        // Sets the location that the End-User's User Agent will be redirected to
        // after the logout has been performed at the Provider
        log.info("===============================oidcLogoutSuccessHandler========================================");
        oidcLogoutSuccessHandler.setPostLogoutRedirectUri("http://localhost:3000/home");
        return oidcLogoutSuccessHandler;
    }

    private DelegatingServerLogoutHandler logoutHandler() {
        log.info("===============================logoutHandler========================================");
        //Invalidate session on logout
        return new DelegatingServerLogoutHandler(
                new SecurityContextServerLogoutHandler(), new WebSessionServerLogoutHandler());
    }


    @Bean
    @Primary
    public ReactiveOAuth2AuthorizedClientManager authorizedClientManager(
            ReactiveClientRegistrationRepository clientRegistrationRepository,
            ServerOAuth2AuthorizedClientRepository authorizedClientRepository) {

        // @formatter:off
        ReactiveOAuth2AuthorizedClientProvider authorizedClientProvider =
                ReactiveOAuth2AuthorizedClientProviderBuilder.builder()
                        .authorizationCode()
                        .refreshToken()
                        .clientCredentials()
                        .build();
        // @formatter:on
        DefaultReactiveOAuth2AuthorizedClientManager authorizedClientManager = new DefaultReactiveOAuth2AuthorizedClientManager(
                clientRegistrationRepository, authorizedClientRepository);
        authorizedClientManager.setAuthorizedClientProvider(authorizedClientProvider);

        return authorizedClientManager;
    }

}
