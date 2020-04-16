package com.roche.product.controller;

import com.roche.product.model.Product;
import com.roche.product.model.ProductRequest;
import com.roche.product.model.ProductResponse;
import com.roche.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private ProductService  productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService =  productService;
    }

    @PostMapping
    public ProductResponse createProduct(@RequestBody ProductRequest newProduct){
        return  productService.create(newProduct);
    }

    @GetMapping("/{id}")
    public ProductResponse getProduct(@RequestParam String id){
        return  new ProductResponse("id","new", BigDecimal.TEN, new Date());
    }

    @GetMapping
    public List<ProductResponse> getAllProducts(){
        return Arrays.asList(  new ProductResponse("id","new", BigDecimal.TEN, new Date()));
    }

    @DeleteMapping("/{id}")
    public ProductResponse deleteProduct(@RequestParam String id){
        return  new ProductResponse("id","new", BigDecimal.TEN, new Date());
    }

    @PutMapping("/{id}")
    public ProductResponse updateProduct(@RequestBody ProductRequest newProduct, @RequestParam String id){
        return  new ProductResponse("id","new", BigDecimal.TEN, new Date());
    }
}
