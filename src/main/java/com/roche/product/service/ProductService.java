package com.roche.product.service;

import com.roche.product.model.Product;
import com.roche.product.model.ProductRequest;
import com.roche.product.model.ProductResponse;
import com.roche.product.model.ProductResponseBuilder;
import com.roche.product.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
    private ProductRepository   productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public ProductResponse create(ProductRequest newProduct) {
        Product product = new Product(newProduct.getName(), newProduct.getPrice());

        Product save = productRepository.save(product);

        ProductResponse productResponse = new ProductResponseBuilder().with(pb -> {
            pb.date = save.getDate();
            pb.name = save.getName();
            pb.id = save.getId();
            pb.price = save.getPrice();
        }).build();

        return productResponse;
    }

}
