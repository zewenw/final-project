package com.finalproject.usermanagement.config;

import org.springframework.core.convert.converter.Converter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;

import java.util.*;
import java.util.stream.Collectors;

/**
 * get user authorities from token
 */
public class JwtCustomizeConverter implements Converter<Jwt, Collection<GrantedAuthority>> {

    @Override
    public Collection<GrantedAuthority> convert(Jwt source) {
        ArrayList<Map<String, Object>> authorities = (ArrayList<Map<String, Object>>) source.getClaims().get("authorities");
        if (authorities == null || authorities.isEmpty()) {
            return new ArrayList<>();
        }
        Collection<GrantedAuthority> returnValue = new ArrayList<>();
        for (Map<String, Object> authority : authorities) {
//            Set<Map.Entry<String, Object>> entries = authority.entrySet();
//            for (Map.Entry<String, Object> entry : entries) {
//                SimpleGrantedAuthority grantedAuthority = new SimpleGrantedAuthority(entry.getValue().toString());
//                returnValue.addAll(Collections.singleton(grantedAuthority));
//            }
            Set<SimpleGrantedAuthority> roles = authority.entrySet().stream()
                    .filter(item -> item.getKey().contains("role"))
                    .map(entry -> new SimpleGrantedAuthority(entry.getValue().toString()))
                    .collect(Collectors.toSet());
            returnValue.addAll(roles);
        }
        return returnValue;


    }
}
