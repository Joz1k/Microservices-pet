package com.myproject.my_shop_ordering.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StockResponse {
    private String code;
    private boolean isInStock;
}
