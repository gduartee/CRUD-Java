package com.crud.CRUD.controller;

import com.crud.CRUD.controller.dto.CreateStockDto;
import com.crud.CRUD.entity.Stock;
import com.crud.CRUD.service.StockService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/stocks")
public class StockController {

    private StockService stockService;

    public StockController(StockService stockService) {
        this.stockService = stockService;
    }

    @PostMapping
    public ResponseEntity<Void> createStock(@RequestBody CreateStockDto createStockDto){
        stockService.createStock(createStockDto);

        return ResponseEntity.ok().build();
    }
}
