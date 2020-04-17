package com.roche.product.controller;

import com.roche.product.model.ProductRequest;
import com.roche.product.model.ProductResponse;
import com.roche.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    @ResponseStatus(CREATED)
    public ProductResponse createProduct(@RequestBody ProductRequest newProduct) {
        return productService.create(newProduct);
    }

    @GetMapping("/{id}")
    public ProductResponse getProduct(@RequestParam String id) {
        return productService.find(id);
    }

    @GetMapping
    public List<ProductResponse> getAllProducts() {
        return productService.findAll();
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(OK)
    public void deleteProduct(@RequestParam String id) {
        productService.delete(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(OK)
    public ProductResponse updateProduct(@RequestBody ProductRequest update, @RequestParam String id) {
        return productService.update(id, update);
    }
}
