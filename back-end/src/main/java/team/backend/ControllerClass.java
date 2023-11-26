package team.backend;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
public class ControllerClass {

    @CrossOrigin
    @PostMapping("/sayhello")
    public ResponseEntity<String> sayHello() {
        // Da AuthTokenFilter håndterer autentificeringen, kan du antage brugeren er autentificeret på dette tidspunkt.
        // Udfør den ønskede forretningslogik her.
        // Returner dit respons.
        System.out.println("HEJ MED DIG DEEEEEEEEEEEET VIRKER");
        return ResponseEntity.ok("Hej, din session er gyldig!");
    }
}