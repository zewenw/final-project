package com.finalproject.authserver.config;

import com.finalproject.authserver.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;

@Slf4j
@Configuration
public class UserDetailConifg {


    @Bean
    UserService userService(){
        log.info("=========userService===========");
        return new UserService();
    }


    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider
                = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userService());
//        authProvider.setPasswordEncoder(encoder());
        return authProvider;
    }

//    @Bean
//    public PasswordEncoder encoder() {
//        return new BCryptPasswordEncoder(11);
//    }

}
