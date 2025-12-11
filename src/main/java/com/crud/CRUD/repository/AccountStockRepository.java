package com.crud.CRUD.repository;

import com.crud.CRUD.entity.AccountStock;
import com.crud.CRUD.entity.AccountStockId;
import com.crud.CRUD.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface AccountStockRepository extends JpaRepository<AccountStock, AccountStockId> {

}
