package com.roche.product.service;

import com.roche.product.exception.ProductNotFoundException;
import com.roche.product.model.*;
import com.roche.product.repository.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.roche.product.model.StatusType.ACTIVE;
import static java.util.stream.Collectors.toList;

@Service
public class ProductService {
    private Logger  logger = LoggerFactory.getLogger(ProductService.class);

    private ProductRepository   productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public ProductResponse create(ProductRequest newProduct) {
        Product product = new Product(newProduct.getName(), newProduct.getPrice());
        product.setStatus(ACTIVE.getName());

        Product save = productRepository.save(product);

        ProductResponse productResponse = toResponse(save);

        return productResponse;
    }

    public ProductResponse find(String id) {
        Product product = findActiveProduct(id);
        ProductResponse productResponse = toResponse(product);
        return productResponse;

    }

    public void delete(String id) {
        Product product = findActiveProduct(id);
        product.setStatus(StatusType.DELETED.getName());
        productRepository.save(product);
    }


    public ProductResponse update(String id, ProductRequest    request) {
        Product product = findActiveProduct(id);
        product.setName(request.getName());
        product.setPrice(request.getPrice());

        Product updated = productRepository.save(product);

        ProductResponse productResponse = toResponse(updated);
        return productResponse;
    }

    public List<ProductResponse> findAll() {
        return productRepository.findAllByStatus(ACTIVE.getName()).stream().map(this::toResponse).collect(toList());
    }


    private Product findActiveProduct(String id) {
        try{
            return  productRepository.findByIdByStatus(id, ACTIVE.getName());
        }catch (Exception e){
            logger.error("product could not find.", e);
            throw new ProductNotFoundException();
        }
    }

    private ProductResponse toResponse(Product product) {
        return new ProductResponseBuilder().with(pb -> {
            pb.date = product.getDate();
            pb.name = product.getName();
            pb.id = product.getId();
            pb.price = product.getPrice();
        }).build();
    }
}
