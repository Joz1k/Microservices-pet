package com.myproject.my_shop_stock_service.service;


import com.myproject.my_shop_stock_service.dto.StockResponse;
import com.myproject.my_shop_stock_service.repository.StockRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StockService {

    private final StockRepository stockRepository;

    @Transactional(readOnly = true)
    public List<StockResponse> isInStock(List<String> code) {
        return stockRepository.findByCodeIn(code).stream()
                .map(item ->
                        StockResponse.builder().code(item.getCode()).isInStock(item.getQuantity() > 0).build()
                ).toList();
    }
}
