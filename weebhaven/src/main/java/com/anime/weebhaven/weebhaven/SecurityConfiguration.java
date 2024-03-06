package com.anime.weebhaven.weebhaven;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    @Autowired
    private MyUserDetailsService userDetailService;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity.authorizeHttpRequests(registry -> {

            registry.requestMatchers("/index").permitAll();
            registry.requestMatchers("/index/header").permitAll();
            registry.requestMatchers("/index/homepage").permitAll();
            registry.requestMatchers("/admin/**").hasRole("ADMIN");
            registry.requestMatchers("/user/**").hasRole("USER");
            registry.anyRequest().authenticated();
        })

                .formLogin(formLogin -> formLogin.loginPage("/login").permitAll())
                .headers(headers -> headers.frameOptions().disable())

                .build();

    }

    // @Bean
    // public UserDetailsService userDetailsService() {
    // UserDetails normalUser = User.builder()
    // .username("aman")
    // .password("$2a$12$TSqMcr5q925aJ51dTxreFOGoEYzr45hIIVAqpoBTpRdh.AVH2HDQu")
    // .roles("USER")
    // .build();

    // UserDetails adminUser = User.builder()
    // .username("admin")
    // .password("$2a$12$b9/3LmuI5xnx.0VjpYrvTOs5QTuPpn5PStslxPYlI5yIIgpU4JoJu")
    // .roles("ADMIN", "USER")
    // .build();

    // return new InMemoryUserDetailsManager(normalUser, adminUser);

    // }

    @Bean
    public UserDetailsService userDetailsService() {
        return userDetailService;

    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailService);
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
