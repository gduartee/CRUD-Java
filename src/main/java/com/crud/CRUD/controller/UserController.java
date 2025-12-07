package com.crud.CRUD.controller;

import com.crud.CRUD.entity.User;
import com.crud.CRUD.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/v1/users")
public class UserController {
    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody CreateUserDto createUserDto) {
        User user = userService.createUser(createUserDto);
        URI location = URI.create("v1/users/" + user.getUserId());
        return ResponseEntity.created(location).body(user);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<User> getUserById(@PathVariable("userId") String userId) {

        return null;
    }
}
