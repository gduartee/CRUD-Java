package com.crud.CRUD.repository;

import com.crud.CRUD.entity.Account;
import com.crud.CRUD.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface AccountRepository extends JpaRepository<Account, UUID> {

}
