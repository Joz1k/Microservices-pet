package com.myproject.my_shop_ordering.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import com.myproject.my_shop_ordering.dto.ProductRequest;
import com.myproject.my_shop_ordering.dto.ProductResponse;
import com.myproject.my_shop_ordering.model.Product;
import com.myproject.my_shop_ordering.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {

    private final ProductRepository productRepository;
    public void createProduct(ProductRequest request) {
        Product product = Product.builder().name(request.getName())
                .description(request.getDescription()).price(request.getPrice()).build();

        productRepository.save(product);

        log.info("Product {} is created and saved", product.getId());
    }

    public List<ProductResponse> getAllProducts() {
        List<Product> productList = productRepository.findAll();
        return productList.stream().map(this::mapToProductResponse).toList();
    }

    private ProductResponse mapToProductResponse(Product product) {
        return ProductResponse.builder().id(product.getId()).name(product.getName())
                .description(product.getDescription()).price(product.getPrice()).build();
    }
}
