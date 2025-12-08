package com.crud.CRUD.service;

import com.crud.CRUD.controller.CreateUserDto;
import com.crud.CRUD.entity.User;
import com.crud.CRUD.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service

public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public User createUser(CreateUserDto createUserDto) {
        // DTO -> ENTITY
        var entity = new User
                (
                        createUserDto.username(),
                        createUserDto.email(),
                        createUserDto.password()
                );
        return userRepository.save(entity);
    }

    public Optional<User> getUserById(String userId){
        return userRepository.findById(UUID.fromString(userId));

    }

    public List<User> listUsers(){
        return userRepository.findAll();
    }
}
