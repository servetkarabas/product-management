package com.roche.product.service;

import com.roche.product.model.Product;
import com.roche.product.model.ProductRequest;
import com.roche.product.model.ProductResponse;
import com.roche.product.repository.ProductRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static java.math.BigDecimal.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {

    private static Product product;
    private static ProductRequest productRequest;

    private static List<Product> products;

    @Mock
    private ProductRepository repository;

    @InjectMocks
    private ProductService service;

    @BeforeAll
    public static void setup() {
        productRequest = new ProductRequest();
        product = new Product("Asprin", TEN);
        products = new ArrayList<>();
        products.add(product);
    }

    @Test
    public void createProductTest_Success() {
        productRequest.setName("test");
        when(repository.save(any(Product.class))).thenReturn(product);

        ProductResponse productResponse = service.create(productRequest);

        assertThat(productResponse).isNotNull();
        assertThat(productResponse.getName()).isEqualTo("test");
    }


    @Test
    public void findAllTest_Success() {
        when(repository.findAll()).thenReturn(products);

        List<ProductResponse> products = service.findAll();

        assertThat(products).isNotEmpty();
        assertThat(products).hasSize(ProductServiceTest.products.size());
        assertThat(products).isEqualTo(ProductServiceTest.products);
    }


    @Test
    public void deleteProductTest_Success() {
        when(repository.findById("1")).thenReturn(Optional.of(product));

        service.delete("1");

//        assertThat(delete).isEqualTo(true);
    }

    @Test
    public void updateProductTest_Success() {

    }
}