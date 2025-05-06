package com.example.twitter.config;

import com.example.twitter.security.ApiKeyFilter;
import com.example.twitter.security.JwtFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(UserDetailsService userDetailsService){
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService);
        provider.setPasswordEncoder(passwordEncoder());
        return new ProviderManager(provider);
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity, ApiKeyFilter apiKeyFilter, JwtFilter jwtFilter) throws Exception{
        return httpSecurity.csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> {
                    auth.requestMatchers("/auth/register/user").permitAll();
                    auth.requestMatchers("/auth/login/**").permitAll();
                    auth.requestMatchers("/auth/register/admin").hasRole("ADMIN");
                        auth.anyRequest().authenticated();
                })
                .addFilterBefore(apiKeyFilter, UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
                .formLogin(Customizer.withDefaults())
                .httpBasic(Customizer.withDefaults())
                .build();
    }


}
