package my.ecommerce.exception;

import org.springframework.http.ResponseEntity;

import java.util.Map;

public class LoginException extends RuntimeException{
    public LoginException(String message) {
        super(message);
    }
}
