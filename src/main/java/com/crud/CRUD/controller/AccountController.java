package com.crud.CRUD.controller;

import com.crud.CRUD.controller.dto.AccountStockResponseDto;
import com.crud.CRUD.controller.dto.AssociateAccountStockDto;
import com.crud.CRUD.service.AccountService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("v1/accounts")
public class AccountController {
    private AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping("{accountId}/stocks")
    public ResponseEntity<Void> associateStock(@PathVariable("accountId") String accountId,
                                               @RequestBody AssociateAccountStockDto associateAccountStockDto){

        accountService.associateStock(accountId, associateAccountStockDto);

        return ResponseEntity.ok().build();
    }

    @GetMapping("{accountId}/stocks")
    public ResponseEntity<List<AccountStockResponseDto>> listStocks(@PathVariable("accountId") String accountId){
        var stocks = accountService.listStocks(accountId);

        return ResponseEntity.ok(stocks);
    }
}
