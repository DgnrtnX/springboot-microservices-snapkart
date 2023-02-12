package com.heisenberg.discoveryserver.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private static final Logger log = LoggerFactory.getLogger(SecurityConfig.class);
    @Value("${eureka.username}")
    private String eurekaUsername;

    @Value("${eureka.password}")
    private String eurekaPassword;

    @Value("${eureka.authorities}")
    private String eurekaAuthorities;


    @Bean
    public InMemoryUserDetailsManager userDetailsService() {
        log.info("user: {},password: {}, authorities:{}", eurekaUsername, eurekaPassword, eurekaAuthorities);
        UserDetails user = User.withDefaultPasswordEncoder()
                .username(eurekaUsername)
                .password(eurekaPassword)
                .authorities(eurekaAuthorities)
                .build();
        return new InMemoryUserDetailsManager(user);
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.csrf().disable()
                .authorizeHttpRequests()
                .anyRequest().authenticated()
                .and().httpBasic();
        return httpSecurity.build();
    }

}
