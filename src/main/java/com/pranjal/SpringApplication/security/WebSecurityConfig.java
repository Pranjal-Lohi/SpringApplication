package com.pranjal.SpringApplication.security;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.pranjal.SpringApplication.util.constants.Privillages;

@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true, securedEnabled = true)
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

    };

    @Bean
    public static PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @SuppressWarnings({ "deprecation", "removal" })
	@Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        http
        .authorizeRequests()
        .requestMatchers(WHITELIST).permitAll()
        .requestMatchers("/profile/**").authenticated()
        .requestMatchers("/admin/**").hasRole("ADMIN")
        .requestMatchers("/editor/**").hasAnyRole("ADMIN","EDITOR")
        .requestMatchers("/test").hasAuthority(Privillages.ACCESS_ADMIN_PANEL.getPrivillage())
        .and()
        .formLogin()
        .loginPage("/login").loginProcessingUrl("/login")
        .usernameParameter("email").passwordParameter("password")
        .defaultSuccessUrl("/", true).failureUrl("/login?error")
        .permitAll()
        .and()
        .logout().logoutUrl("/logout")
        .logoutSuccessUrl("/")
        .and()
        .httpBasic();

        //TODO: remove these after upgrading the DB from H2 infile DB
        http.csrf().disable();
        http.headers().frameOptions().disable();

        return http.build();
    }
    
}

