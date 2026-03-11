package my.ecommerce.controller;
import my.ecommerce.dto.LoginDto;
import my.ecommerce.models.UserEntity;
import my.ecommerce.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/home")
@CrossOrigin
public class HomeController {
    private final UserService userService;
    public HomeController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public String test(){
        return "running";
    }

    @PostMapping(value = "/login",
            consumes = "application/json",
            produces = "application/json")
    public ResponseEntity<Map<String, Object>> login(@RequestBody LoginDto loginDto) {
        UserEntity user = userService.findUser(loginDto.getUsername(), loginDto.getPassword());
        Map<String, Object> log = new HashMap<>();
        log.put("msg", "login successful");
        log.put("timestamp", Instant.now());
        return ResponseEntity.ok(log);
    }

}
