package com.myproject.my_shop_ordering.repository;

import com.myproject.my_shop_ordering.model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductRepository extends MongoRepository<Product, String> {
}
