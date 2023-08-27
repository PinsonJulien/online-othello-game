package com.pinson.othello.auths;

import com.pinson.othello.auths.jwts.JwtRequestFilter;
import com.pinson.othello.players.OthelloPlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(
    prePostEnabled = true
)
public class WebSecurityConfiguration {

    private final JwtRequestFilter jwtRequestFilter;
    private final OthelloPlayerService othelloPlayerService;

    @Autowired
    public WebSecurityConfiguration(
        final JwtRequestFilter jwtRequestFilter,
        final OthelloPlayerService othelloPlayerService
    ) {
        this.jwtRequestFilter = jwtRequestFilter;
        this.othelloPlayerService = othelloPlayerService;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(
        final AuthenticationConfiguration authenticationConfiguration
    ) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(this.othelloPlayerService);
        provider.setPasswordEncoder(this.passwordEncoder());
        return provider;
    }

    @Bean
    public SecurityFilterChain configure(final HttpSecurity http) throws Exception {
        return http
            .cors(Customizer.withDefaults()) // May not be needed
            .csrf(AbstractHttpConfigurer::disable)
            .authorizeHttpRequests((authorize) -> {
                authorize.requestMatchers(
                    "/api/v1/auth/**"
                ).permitAll()
                .anyRequest().authenticated();
            })
            .sessionManagement(manager -> manager.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .authenticationProvider(authenticationProvider())
            .addFilterBefore(this.jwtRequestFilter, UsernamePasswordAuthenticationFilter.class)
            .build();
    }
}
