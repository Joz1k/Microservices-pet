package com.myproject.my_shop_ordering.service;


import com.myproject.my_shop_ordering.dto.OrderItemsDTO;
import com.myproject.my_shop_ordering.dto.OrderRequest;
import com.myproject.my_shop_ordering.dto.StockResponse;
import com.myproject.my_shop_ordering.model.Order;
import com.myproject.my_shop_ordering.model.OrderItems;
import com.myproject.my_shop_ordering.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderService {

    private final OrderRepository orderRepository;
    private final WebClient.Builder webClientBuilder;

    public void makeOrder(OrderRequest request) {
        Order order = new Order();
        order.setOrderNumber(UUID.randomUUID().toString());

        List<OrderItems> items = request.getItemsDTOList().stream().map(this::mapToDTO).toList();
        order.setOrderItemsList(items);

        List<String> itemList = order.getOrderItemsList().stream().map(OrderItems::getCode).toList();

        StockResponse[] stockResponses = webClientBuilder.build().get().uri("http://myShopStockService/api/stock",
                        uriBuilder -> uriBuilder.queryParam("code", itemList).build())
                .retrieve()
                .bodyToMono(StockResponse[].class)
                .block();

        boolean allMatch = false;
        if (stockResponses != null) {
            allMatch = Arrays.stream(stockResponses).allMatch(StockResponse::isInStock);
        }
        if (allMatch) {
            orderRepository.save(order);
        } else {
            throw new IllegalArgumentException("Out of stock, sorry :(");
        }

    }

    private OrderItems mapToDTO(OrderItemsDTO orderItemsDTO) {
        OrderItems orderItems = new OrderItems();
        orderItems.setCode(orderItemsDTO.getCode());
        orderItems.setPrice(orderItemsDTO.getPrice());
        orderItems.setQuantity(orderItemsDTO.getQuantity());
        return orderItems;
    }
}
