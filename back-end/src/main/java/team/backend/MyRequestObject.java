package team.backend;

public class MyRequestObject {
    private String username;
    private String password;

    // Getters
    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    // Setters
    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    // Validation method
    public boolean isValid() {
        return username != null && password != null &&
               !username.isEmpty() && !password.isEmpty() &&
               username.length() <= 255 && password.length() <= 255;
    }
}
