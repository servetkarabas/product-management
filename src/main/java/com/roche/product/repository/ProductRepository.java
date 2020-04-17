package com.roche.product.repository;

import com.roche.product.model.Product;
import com.roche.product.model.StatusType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, String> {
    Optional<Product> findByIdAndStatus(String id, StatusType status);
    List<Product> findByStatus(StatusType status);

}
