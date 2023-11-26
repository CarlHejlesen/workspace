package team.backend.security;

import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Cookie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.util.WebUtils;

import java.io.IOException;

@Component
public class AuthTokenFilter extends OncePerRequestFilter {

    @Autowired
    private UserService userService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, 
                                    HttpServletResponse response, 
                                    FilterChain filterChain) 
                                    throws ServletException, IOException {
        String path = request.getRequestURI();
                System.out.println("ehj her kommer vi ind");
        // Bypass the filter for the login endpoint
        if (!"/try_login".equals(path)) {
            Cookie sessionCookie = WebUtils.getCookie(request, "auth_token");
            if (sessionCookie == null || !userService.isSessionValid(sessionCookie.getValue())) {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                response.getWriter().write("Not logged in or invalid session");
                response.getWriter().flush();
                return; // Stop the filter chain
            }
        }

        filterChain.doFilter(request, response); // Continue the filter chain
    }
}
