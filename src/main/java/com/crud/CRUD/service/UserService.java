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
    public User createUser(CreateUserDto createUserDto) {
        // DTO -> ENTITY
        var entity = new User
                (
                        UUID.randomUUID(),
                        createUserDto.username(),
                        createUserDto.email(),
                        createUserDto.password(),
                        Instant.now(),
                        null
                );
        return userRepository.save(entity);
    }
}
