package team.backend.security;

import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import java.util.Scanner;
import team.backend.repository.UserRepository;
import team.backend.model.User;

@Component
public class TerminalInputRunner implements CommandLineRunner {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public TerminalInputRunner(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.print("Enter username: ");
            String username = scanner.nextLine();
            System.out.print("Enter password: ");
            String password = scanner.nextLine();

            // Hash and salt the password
            String encodedPassword = passwordEncoder.encode(password);

            // Save to database
            User user = new User();
            user.setUsername(username);
            user.setPassword(encodedPassword);
            userRepository.save(user);

            System.out.println("User saved successfully");
        }
        // Scanner is auto-closed here due to try-with-resources
    }
}
