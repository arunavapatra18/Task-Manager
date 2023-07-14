package dev.arunava.taskmanager.Task.Manager.controller;

import dev.arunava.taskmanager.Task.Manager.model.User;
import dev.arunava.taskmanager.Task.Manager.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api")
@CrossOrigin
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(path = "/register")
    public void registerUser(@RequestBody User user){
        userService.createUser(user);
    }

    @GetMapping(path = "/users")
    public List<User> getUser() {
        return userService.getUsers();
    }
}
