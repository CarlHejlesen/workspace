package team.backend;

// Import statements for Spring Security configuration classes
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;

import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import java.util.Arrays;
import static org.springframework.security.config.Customizer.withDefaults;



// Annotation to denote this class as a configuration class for Spring
@Configuration
// This annotation enables Spring Security for the web application
@EnableWebSecurity
public class WebSecurityConfig {

    // A bean that defines the security filter chain which dictates how security is
    // handled
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        // Begin configuring HttpSecurity
        http
                // Configures authorization for HTTP requests
                .authorizeHttpRequests((authz) -> authz
                        .requestMatchers("./try_login").permitAll() // should do so try login dont get authenticated
                                                                    // when trying to log in.
                        // Specifies that any request must be authenticated
                        .anyRequest().authenticated())
                // Enables HTTP Basic Authentication with default settings
                .httpBasic(withDefaults());
        // Builds and returns the SecurityFilterChain object
        return http.build();
    }

    // Bean to customize web security, allowing certain requests to bypass security
    // filters
    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        // Configures the customizer to ignore certain request matchers
        return (web) -> web.ignoring().requestMatchers("");
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("*")); // Be more specific in production!
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "OPTIONS"));
        configuration.setAllowedHeaders(Arrays.asList("*"));
        configuration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/try_login", configuration);
        return source;
    }
}
