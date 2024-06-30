package ar.mikellbobadilla.app.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http.authorizeHttpRequests(request -> {
            request.requestMatchers("/register", "/css/**").permitAll();
            request.anyRequest().authenticated();
        });
        http.csrf(Customizer.withDefaults());
        http.httpBasic(Customizer.withDefaults());
        http.formLogin(login -> login.loginPage("/login").permitAll());
        http.rememberMe(Customizer.withDefaults());

        return http.build();
    }
}
