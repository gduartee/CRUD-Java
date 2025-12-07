package com.crud.CRUD.service;

import com.crud.CRUD.controller.CreateUserDto;
import com.crud.CRUD.entity.User;
import com.crud.CRUD.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.UUID;

@Service

public class UserService {
    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public UUID createUser(CreateUserDto createUserDto) {
        // DTO -> ENTITY
        var entity = new User
                (
                        createUserDto.username(),
                        createUserDto.email(),
                        createUserDto.password()
                );
        var userSaved = userRepository.save(entity);
        return userSaved.getUserId();
    }
}
