package com.myproject.my_shop_stock_service;

import com.myproject.my_shop_stock_service.model.Stock;
import com.myproject.my_shop_stock_service.repository.StockRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class MyShopStockServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(MyShopStockServiceApplication.class, args);
    }

    @Bean
    public CommandLineRunner loadData(StockRepository stockRepository) {
        return args -> {
            Stock stock = new Stock();
            stock.setCode("123");
            stock.setQuantity(100);

            Stock stock1 = new Stock();
            stock1.setCode("mac");
            stock1.setQuantity(0);

            stockRepository.save(stock);
            stockRepository.save(stock1);
        };
    }

}
