package team.backend;


import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ControllerClass {
    
    @PostMapping("/myendpoint")
    public MyResponseObject handlePostRequest(@RequestBody MyRequestObject request) {
        // Behandl anmodningen og returner et svar
        return new MyResponseObject();
    }

}