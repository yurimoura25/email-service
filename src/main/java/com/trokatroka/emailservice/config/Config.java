package com.trokatroka.emailservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .requiresChannel(channel ->
                        channel.anyRequest().requiresSecure())
                .authorizeRequests(authorize ->
                        authorize.anyRequest().permitAll())
                .build();
    }

}