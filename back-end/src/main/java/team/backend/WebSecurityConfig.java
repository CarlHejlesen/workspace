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
                        .requestMatchers("/try_login").permitAll() // should do so try login dont get authenticated
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

    // Annotation indicating that this method will produce a bean to be managed by
    // the Spring container
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        // Create a new instance of CorsConfiguration to specify our CORS configuration
        CorsConfiguration configuration = new CorsConfiguration();
        // Set the allowed origins for CORS requests. Here "*" means all origins are allowed.
        // In a production environment, it is recommended to specify only trusted
        // origins for security.
        configuration.setAllowedOrigins(Arrays.asList("*")); // Be more specific in production!
        // Set the allowed HTTP methods for CORS requests. Only "GET", "POST", and
        // "OPTIONS" are allowed here.
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "OPTIONS"));
        // Set the allowed headers in CORS requests. Here "*" means all headers are
        // allowed.
        // This allows the client to send any standard HTTP headers with the CORS
        // request.
        configuration.setAllowedHeaders(Arrays.asList("*"));
        // Indicate whether the browser should include credentials, such as cookies or
        // HTTP authentication, in CORS requests.
        configuration.setAllowCredentials(true);

        // Create an instance of UrlBasedCorsConfigurationSource which will be used to
        // hold our CORS configuration
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        // Register the CorsConfiguration we defined earlier for a specific path
        // pattern, in this case, "/try_login".
        // This means the CORS configuration will be applied only to requests that match
        // this pattern.
        source.registerCorsConfiguration("/try_login", configuration);
        // Return the configured CorsConfigurationSource instance which will be used by
        // Spring Security
        // to control CORS interactions for the "/try_login" endpoint.
        return source;
    }

}
