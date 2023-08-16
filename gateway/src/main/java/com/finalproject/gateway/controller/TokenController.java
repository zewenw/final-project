package com.finalproject.gateway.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.CurrentSecurityContext;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.annotation.RegisteredOAuth2AuthorizedClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;

@RestController
public class TokenController {


    @GetMapping("/secret")
    public Mono<Map<String, Object>> index(@CurrentSecurityContext(expression = "authentication") Authentication authentication,
                                           @RegisteredOAuth2AuthorizedClient OAuth2AuthorizedClient oAuth2AuthorizedClient) {
        Map<String, Object> map = new HashMap<>(2);
        map.put("currentUser", authentication);
        map.put("authorizedClient", oAuth2AuthorizedClient);
        return Mono.justOrEmpty(map);
    }

    @GetMapping("/username")
    public Mono<String> username(@RegisteredOAuth2AuthorizedClient OAuth2AuthorizedClient oAuth2AuthorizedClient) {
        return Mono.justOrEmpty(oAuth2AuthorizedClient.getPrincipalName());
    }
}
