package com.mavericksstube.maverickshub.security.config;

import com.mavericksstube.maverickshub.security.filters.CustomUsernamePasswordAuthenticationFilter;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
@AllArgsConstructor
public class SecurityConfig {
    private final CustomUsernamePasswordAuthenticationFilter authenticationFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{
        authenticationFilter.setFilterProcessesUrl("/api/v1/auth");
        return httpSecurity.csrf(c -> c.disable()).cors(c -> c.disable())
                .addFilterAt(authenticationFilter, BasicAuthenticationFilter.class)
                .authorizeHttpRequests(c -> c.anyRequest().permitAll()).build();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return NoOpPasswordEncoder.getInstance();
    }

}
