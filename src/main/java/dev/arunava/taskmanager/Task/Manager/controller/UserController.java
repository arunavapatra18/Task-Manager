package dev.arunava.taskmanager.Task.Manager.controller;

import dev.arunava.taskmanager.Task.Manager.model.User;
import dev.arunava.taskmanager.Task.Manager.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * The Rest Controller for handling User related requests
 */
@RestController
@RequestMapping(path = "/api/user")
@CrossOrigin
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * POST METHOD: Register a new user.
     * @param user The User object passed by the user.
     * @return HTTP Response
     */
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(path = "/register")
    public ResponseEntity<String> registerUser(@RequestBody User user){
        return userService.createUser(user);
    }

    /**
     * POST METHOD: Login a user.
     * @param user The User object with credentials
     * @return HTTP Response
     */
    @PostMapping(path = "/login")
    public ResponseEntity<String> loginUser(@RequestBody User user) {
        return userService.login(user);
    }

    /**
     * GET METHOD: Lists all the users registered. Only allowed for ADMIN.
     * @return List<User> The list of all the users.
     */
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping(path = "")
    public List<User> getAllUsers() {
        return userService.getUsers();
    }

}
