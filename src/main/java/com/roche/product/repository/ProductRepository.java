package com.roche.product.repository;

import com.roche.product.model.Product;
import com.roche.product.model.StatusType;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends MongoRepository<Product, String> {
    Optional<Product> findByIdAndStatus(String id, StatusType status);
    List<Product> findByStatus(StatusType status);

}
