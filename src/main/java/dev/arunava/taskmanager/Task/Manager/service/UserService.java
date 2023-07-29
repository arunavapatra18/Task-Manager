package dev.arunava.taskmanager.Task.Manager.service;

import dev.arunava.taskmanager.Task.Manager.model.User;
import dev.arunava.taskmanager.Task.Manager.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * The Service class for managing the business logic for User class
 */
@Service
public class UserService {
    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final JwtTokenUtil jwtTokenUtil;

    @Autowired
    private AuthenticationManager authenticationManager;

    public UserService(UserRepository userRepository, @Lazy PasswordEncoder passwordEncoder, JwtTokenUtil jwtTokenUtil) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtTokenUtil = jwtTokenUtil;
    }

    /**
     * Create a new user.
     * @param user The User data to be created
     */
    public ResponseEntity<String> createUser(User user) {
        Optional<User> existingUser = userRepository.findByEmail(user.getEmail());

        if(existingUser.isPresent())
            return ResponseEntity.status (HttpStatus.CONFLICT).body("Email already in use!");

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return ResponseEntity.ok("Registration successful!");
    }

    /**
     * List all the users
     * @return A list of all users in the database
     */
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    /**
     * Login an existing user
     * @param user The User object with credentials
     * @return HTTP Response
     */
    public ResponseEntity<String> login(User user) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword())
            );

            String token = jwtTokenUtil.generateToken((UserDetails) authentication.getPrincipal());

            return ResponseEntity.ok(token);
        } catch (AuthenticationException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid Credentials!");
        }
    }
}
