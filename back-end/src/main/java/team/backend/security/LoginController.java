package team.backend.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseCookie;
import jakarta.servlet.http.HttpServletResponse;
//import java.util.UUID;
import team.backend.MyRequestObject;

@CrossOrigin
@RestController
public class LoginController {
    
    @Autowired
    private UserService userService;

    @PostMapping("/try_login")
    public ResponseEntity<?> handleLogin(@RequestBody MyRequestObject request, HttpServletResponse response) {
        if (request.isValid()) {
            boolean isValidUser = userService.validateUser(request.getUsername(), request.getPassword());
            if (isValidUser) {
                // Create a secure cookie using the generateSessionId method from UserService
                String cookieValue = userService.generateSessionId(); // This method now calls
                                                                      // UserService.generateSessionId()
                    
                // Store the session
                userService.createSession(request.getUsername(), cookieValue); // Ensure you implement this method

                ResponseCookie cookie = ResponseCookie.from("auth_token", cookieValue)
                        .httpOnly(true) // Mitigates risk of client side script accessing the protected cookie
                        .secure(true) // Ensures the cookie is only sent over HTTPS
                        .sameSite("Lax") // Strict or Lax. Strict is more secure
                        .path("/") // The cookie is available to all paths
                        .maxAge(30 * 24 * 60 * 60) // Sets max age to 30 days
                        .build();

                // Add the cookie to the response
                response.addHeader("Set-Cookie", cookie.toString());

                // Return the response entity as normal
                return new ResponseEntity<>("Login successful", HttpStatus.OK);
            } else {
                // User is invalid, return an error
                return new ResponseEntity<>("Invalid username or password", HttpStatus.UNAUTHORIZED);
            }
        } else {
            // Request data is invalid
            return new ResponseEntity<>("Invalid input", HttpStatus.BAD_REQUEST);
        }
    }
}
