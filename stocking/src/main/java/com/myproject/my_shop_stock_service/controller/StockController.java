package com.myproject.my_shop_stock_service.controller;

import com.myproject.my_shop_stock_service.dto.StockResponse;
import com.myproject.my_shop_stock_service.service.StockService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/stock")
@RequiredArgsConstructor
public class StockController {

    private final StockService stockService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<StockResponse> isInStock(@RequestParam List<String> code) {
        return stockService.isInStock(code);
    }
}
