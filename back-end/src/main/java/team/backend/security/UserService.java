package team.backend.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import team.backend.model.User;
import team.backend.repository.UserRepository;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    // This is just for illustration purposes. In a real application, you'd use a persistent store.
    private final Map<String, User> sessions = new ConcurrentHashMap<>();

    public boolean validateUser(String username, String password) {
        User user = userRepository.findByUsername(username);
        return user != null && bCryptPasswordEncoder.matches(password, user.getPassword());
    }

    // Call this method to create a session after successful login
    public String createSession(String username, String sessionId) {
        User user = userRepository.findByUsername(username);
        System.out.println("vi tester on den existere");
        if (user != null) {
            sessions.put(sessionId, user);
            return sessionId;
        }
        return null; // Return null if the user does not exist
    }

    // Implement a method to generate a unique session ID for the user.
    // This could be a securely generated random string.
    private final SecureRandom secureRandom = new SecureRandom();
    public String generateSessionId() {
        // Generate a random 128-bit value
        return new BigInteger(130, secureRandom).toString(32); // Using base 32 encoding
    }

    // Call this method to check if the session ID from the cookie is valid
    public boolean isSessionValid(String sessionId) {
        return sessions.containsKey(sessionId);
    }

    // Call this method to end a session during logout
    public void endSession(String sessionId) {
        sessions.remove(sessionId);
    }
}
