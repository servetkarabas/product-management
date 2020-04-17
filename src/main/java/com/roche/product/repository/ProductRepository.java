package com.roche.product.repository;

import com.roche.product.model.Product;
import com.roche.product.model.StatusType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, String> {
    Product findByIdByStatu(String id, StatusType status);
    List<Product> findByStatu(StatusType status);
}
