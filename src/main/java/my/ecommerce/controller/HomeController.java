package my.ecommerce.controller;
import my.ecommerce.dto.LoginDto;
import my.ecommerce.dto.Signupdto;
import my.ecommerce.models.UserEntity;
import my.ecommerce.security.JwtService;
import my.ecommerce.service.UserService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
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
    private final JwtService jwtService;
    public HomeController(UserService userService, JwtService jwtService) {

        this.userService = userService;
        this.jwtService = jwtService;
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
        String token = jwtService.generateToken(user.getUsername(), user.getRole());
        ResponseCookie responseCookie = ResponseCookie.from("jwt-token", token)
                .httpOnly(true)
                .secure(false)
                .path("/")
                .maxAge(86400)
                .build();

        Map<String, Object> log = new HashMap<>();
        log.put("msg", "login successful");
        log.put("timestamp", Instant.now());
        return ResponseEntity.ok()
                .header(HttpHeaders.SET_COOKIE, responseCookie.toString())
                .body(log);
    }

    @PostMapping(value = "/signup",
    consumes = "application/json",
    produces="application/json")
    public ResponseEntity<Map<String, Object>> signup(@RequestBody Signupdto signupdto) {
        UserEntity user = userService.addUser(signupdto);
        Map<String, Object> res = new HashMap<>();
        res.put("username", user.getUsername());
        res.put("msg", "user add successfully");
        return ResponseEntity.ok(res);
    }

    @GetMapping("/admin")
    public ResponseEntity<Map<String, Object>> adminHome(){
        Map<String, Object> admin = new HashMap<>();
        admin.put("login", "successful");
        return ResponseEntity.ok(admin);
    }
}
