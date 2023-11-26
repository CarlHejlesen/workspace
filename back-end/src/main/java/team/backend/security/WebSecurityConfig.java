package team.backend.security;


// Import statements for Spring Security configuration classes
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.security.config.Customizer;

import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import java.util.Arrays;


import org.springframework.security.config.http.SessionCreationPolicy;

// Annotation to denote this class as a configuration class for Spring
@Configuration
// This annotation enables Spring Security for the web application
@EnableWebSecurity
public class WebSecurityConfig {
  

    // A bean that defines the security filter chain which dictates how security is
    // handled
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http, AuthTokenFilter authTokenFilter) throws Exception {
        http
                // Deaktiver CSRF beskyttelse
                .csrf(csrf -> csrf.disable())
                // Tillad CORS og konfigurer efter behov
                .cors(Customizer.withDefaults())
                .addFilterBefore(authTokenFilter, UsernamePasswordAuthenticationFilter.class)
                // Konfigurer session management til at være stateless
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                // Konfigurer sikkerhedsregler for anmodninger
                .authorizeHttpRequests(authz -> authz
                        .requestMatchers("/try_login").permitAll() 
                        .anyRequest().authenticated() // Kræv autentificering for alle andre anmodninger
                )
                // Deaktiver Basic Authentication
                .httpBasic(httpBasic -> httpBasic.disable());

        return http.build();
    }


    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // Annotation indicating that this method will produce a bean to be managed by
    // the Spring container
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("http://localhost:5173")); // Angiv den korrekte frontend-origin
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS")); // Tillad nødvendige
                                                                                                   // metoder
        configuration.setAllowedHeaders(Arrays.asList("*")); // Tillad alle headers
        configuration.setAllowCredentials(true); // Tillad credentials som cookies og HTTP-autentifikation

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration); // Anvend denne konfiguration til alle stier
        return source;
    }

}
