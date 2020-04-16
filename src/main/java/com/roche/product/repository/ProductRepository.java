package com.roche.product.repository;

import com.roche.product.model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ProductRepository extends MongoRepository<Product, String> {

    Product findByIdByStatus(String id, String status);

    List<Product> findAllByStatus(String name);
}
