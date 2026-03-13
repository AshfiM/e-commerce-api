package my.ecommerce.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;


@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<Map<String, Object>> loginExceptionHandler(LoginException ex){
        Map<String, Object> error = new HashMap<>();
        error.put("msg", ex.getMessage());
        error.put("status", HttpStatus.UNAUTHORIZED.value());
        error.put("timestamp", Instant.now());
        return new ResponseEntity<>(error,HttpStatus.UNAUTHORIZED);
    }

}
