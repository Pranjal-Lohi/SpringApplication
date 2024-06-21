package com.pranjal.SpringApplication.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class WebSecurityConfig {

    private static final String[] WHITELIST = {
                "/",
                "/login",
                "/register",
                "/db-console/**",
                "/css/**",
                "/fonts/**",
                "/images/**",
                "/js/**",
                "/resources/**",
                "/posts/**"
    };

    @Bean
    public static PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .headers(headers -> headers.frameOptions(configurer -> configurer.sameOrigin()))
                .authorizeHttpRequests(authorizeRequests -> authorizeRequests
                        .requestMatchers(WHITELIST).permitAll()
                        .requestMatchers("/profile/**").authenticated()
                        .requestMatchers("/update_photo/**").authenticated()
                        .requestMatchers("/posts/add/**").authenticated()
                        .requestMatchers("/admin/**").hasRole("ADMIN")
                        .requestMatchers("/editor/**").hasAnyRole("ADMIN", "EDITOR")
                        .requestMatchers("/forgot-password").permitAll()
                        .requestMatchers("/reset-password").permitAll()
                        .requestMatchers("/change-password").permitAll()
                )
                .formLogin(formLogin -> formLogin
                        .loginPage("/login")
                        .loginProcessingUrl("/login")
                        .usernameParameter("email")
                        .passwordParameter("password")
                        .defaultSuccessUrl("/", true)
                        .failureUrl("/login?error")
                        .permitAll()
                )
                .logout(logout -> logout
                        .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                        .logoutSuccessUrl("/")
                        .permitAll()
                )
                .rememberMe(rememberMe -> rememberMe.rememberMeParameter("remember-me"))
                .httpBasic(httpBasic -> httpBasic
                        .authenticationEntryPoint(new CustomHttpBasicAuthenticationEntryPoint())
                );

        // TODO: remove these after upgrading the DB from H2 in-memory DB
        http.csrf(csrf -> csrf.disable());
        http.headers(headers -> headers.frameOptions(configurer -> configurer.disable()));

        return http.build();
    }
}
