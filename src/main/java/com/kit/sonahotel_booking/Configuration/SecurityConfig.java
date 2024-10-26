package com.kit.sonahotel_booking.Configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

  private final String[] PUBLIC_ENDPOINTS = {
          "/api/customer/login",
          "/api/customer/register",
          "/api/customer/verify",
          "/api/customer/logout",
          "/api/bedtype/**"  // Allows access to deeper paths like /api/bedtype/update/{id}
  };

/*  @Bean
  public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
    httpSecurity.authorizeHttpRequests(
        authorized -> authorized.requestMatchers(HttpMethod.POST, PUBLIC_ENDPOINTS)
                .permitAll()
                .requestMatchers(HttpMethod.PUT, PUBLIC_ENDPOINTS)
                .permitAll()
            .anyRequest()
            .authenticated());
    httpSecurity.csrf(AbstractHttpConfigurer::disable);
    return httpSecurity.build();
  }*/
  @Bean
  public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
    httpSecurity
            .authorizeHttpRequests(authorize -> authorize
                    .anyRequest().permitAll()  // Allows all requests
            );
    httpSecurity.csrf(AbstractHttpConfigurer::disable); // Optionally disable CSRF for easier testing (not recommended in production)

    return httpSecurity.build();
  }


  @Bean
  PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder(10);
  }
}
