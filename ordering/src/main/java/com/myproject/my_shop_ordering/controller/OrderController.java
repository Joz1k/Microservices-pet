package com.myproject.my_shop_ordering.controller;

import com.myproject.my_shop_ordering.dto.OrderRequest;
import com.myproject.my_shop_ordering.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/order")
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String makeOrder(@RequestBody OrderRequest request) {
        orderService.makeOrder(request);
        return "Order made successfully";
    }
}
