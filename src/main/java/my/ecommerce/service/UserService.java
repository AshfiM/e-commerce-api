package my.ecommerce.service;

import my.ecommerce.dto.Signupdto;
import my.ecommerce.exception.LoginException;
import my.ecommerce.models.UserEntity;
import my.ecommerce.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder encoder;

    public  UserService(UserRepository userRepository, BCryptPasswordEncoder encoder) {
        this.userRepository = userRepository;
        this.encoder = encoder;

    }
    public UserEntity findUser(String userName, String password) {
        UserEntity user = userRepository.findById(userName).
                orElseThrow(() -> new LoginException("Invalid Credentials"));
        if (encoder.matches(password, user.getHash())) {
            return user;
        }
        else {
            throw new LoginException("Invalid Credentials");
        }
    }

    public UserEntity addUser(Signupdto user) {
        UserEntity newUser = new UserEntity();
        newUser.setUsername(user.getUsername());
        newUser.setHash(encoder.encode(user.getPassword()));
        newUser.setRole(user.getRole());
        return userRepository.save(newUser);
    }
}
