package com.crud.CRUD.controller;

import com.crud.CRUD.entity.User;
import com.crud.CRUD.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/v1/users")
public class UserController {
    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody CreateUserDto createUserDto){
        var user = userService.createUser(createUserDto);
        URI uri = URI.create("/v1/users/" + user.getUserId().toString());

        return ResponseEntity.created(uri).body(user);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<User> getUserById(@PathVariable("userId") String userId) {
        var user = userService.getUserById(userId);
        if(user.isPresent())
            return ResponseEntity.ok(user.get());
        else
            return ResponseEntity.notFound().build();
    }

    @GetMapping()
    public ResponseEntity<List<User>> listUsers(){
        var users = userService.listUsers();

        return ResponseEntity.ok(users);
    }
}
