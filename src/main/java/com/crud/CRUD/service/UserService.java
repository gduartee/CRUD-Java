package com.crud.CRUD.service;

import com.crud.CRUD.controller.dto.AccountResponseDto;
import com.crud.CRUD.controller.dto.CreateAccountDto;
import com.crud.CRUD.controller.dto.CreateUserDto;
import com.crud.CRUD.controller.dto.UpdateUserDto;
import com.crud.CRUD.entity.Account;
import com.crud.CRUD.entity.BillingAddress;
import com.crud.CRUD.entity.User;
import com.crud.CRUD.repository.AccountRepository;

import com.crud.CRUD.repository.BillingAddressRepository;
import com.crud.CRUD.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service

public class UserService {
    private UserRepository userRepository;
    private AccountRepository accountRepository;
    private BillingAddressRepository billingAddressRepository;


    public UserService(UserRepository userRepository, AccountRepository accountRepository, BillingAddressRepository billingAddressRepository) {
        this.userRepository = userRepository;
        this.accountRepository = accountRepository;
        this.billingAddressRepository = billingAddressRepository;
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

    public Optional<User> getUserById(String userId) {
        return userRepository.findById(UUID.fromString(userId));

    }

    public List<User> listUsers() {
        return userRepository.findAll();
    }

    public void updateUserById(String userId, UpdateUserDto updateUserDto) {
        var id = UUID.fromString(userId);

        var userEntity = userRepository.findById(id);

        if (userEntity.isPresent()) {
            var user = userEntity.get();

            if (updateUserDto.username() != null)
                user.setUsername(updateUserDto.username());

            if (updateUserDto.password() != null)
                user.setPassword(updateUserDto.password());

            userRepository.save(user);
        }

    }

    public void deleteById(String userId) {
        var id = UUID.fromString(userId);
        var userExists = userRepository.existsById(id);

        if (userExists)
            userRepository.deleteById(id);
    }

    public void createAccount(String userId, CreateAccountDto createAccountDto) {

        var user = userRepository.findById(UUID.fromString(userId)).orElseThrow(() -> new ResponseStatusException((HttpStatus.NOT_FOUND)));

        // DTO->ENTITY
        var account = new Account(
                user,
                null,
                createAccountDto.description(),
                new ArrayList<>()
        );

        var accountCreated = accountRepository.save(account);

        var billingAddress = new BillingAddress(
                accountCreated.getAccountId(),
                account,
                createAccountDto.street(),
                createAccountDto.number()
        );

        billingAddressRepository.save(billingAddress);
    }

    public List<AccountResponseDto> listAccounts(String userId){
        var user = userRepository.findById(UUID.fromString(userId)).orElseThrow(() -> new ResponseStatusException((HttpStatus.NOT_FOUND)));

        return user.getAccounts()
                .stream()
                .map(ac ->
                        new AccountResponseDto(ac.getAccountId().toString(), ac.getDescription()))
                .toList();
    }
}
