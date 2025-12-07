package com.crud.CRUD.service;

import com.crud.CRUD.controller.CreateUserDto;
import com.crud.CRUD.entity.User;
import com.crud.CRUD.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service

public class UserService {
    private UserRepository userRepository;

    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public void createUser(CreateUserDto createUserDto){

    }
}
