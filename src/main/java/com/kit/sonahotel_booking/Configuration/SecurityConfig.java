package com.kit.sonahotel_booking.Configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.authorizeHttpRequests(authorized ->authorized.anyRequest().permitAll());
        httpSecurity.csrf(AbstractHttpConfigurer::disable);
        httpSecurity.httpBasic(Customizer.withDefaults());
        httpSecurity.formLogin(AbstractHttpConfigurer::disable);
        return httpSecurity.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        // Trả về một `InMemoryUserDetailsManager` trống để không sinh user mặc định
        return new InMemoryUserDetailsManager();
    }
}
