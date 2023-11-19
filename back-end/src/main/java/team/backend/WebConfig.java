package team.backend;

// Import statements for Spring framework annotations and classes
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

// Indicates that this class contains bean definitions for the application context
@Configuration
// This class implements the WebMvcConfigurer interface, allowing it to customize the default configuration provided by Spring MVC
public class WebConfig implements WebMvcConfigurer {

    // Override the addCorsMappings method from the WebMvcConfigurer interface
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        // Register CORS configuration for all endpoints ("/**") in the application
        registry.addMapping("/**")
            // Specify that only requests from "http://localhost:3000" are allowed to access these endpoints
            // This should be changed to the specific domain you expect your frontend to be served from
            .allowedOrigins("http://localhost:5173/") // or the specific origin you need
            // Allow these HTTP methods for CORS requests. This covers most use cases for RESTful APIs
            .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
            // Allow sending cookies and authentication data in CORS requests
            // This should only be enabled if you need to manage session or authentication data in cross-origin requests
            .allowCredentials(true);
    }

    // Other beans and configurations can be added here
    // This is typically where you would add other methods that customize the default Spring MVC configuration
}
