package com.myproject.my_shop_stock_service.repository;

import com.myproject.my_shop_stock_service.model.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StockRepository extends JpaRepository<Stock, Long> {
    List<Stock> findByCodeIn(List<String> code);
}
