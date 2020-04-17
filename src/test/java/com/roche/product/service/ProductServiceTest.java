package com.roche.product.service;

import com.roche.product.exception.ProductNotFoundException;
import com.roche.product.model.Product;
import com.roche.product.model.ProductRequest;
import com.roche.product.model.ProductResponse;
import com.roche.product.repository.ProductRepository;
import jdk.nashorn.internal.ir.annotations.Ignore;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.roche.product.model.StatusType.ACTIVE;
import static java.math.BigDecimal.ONE;
import static java.math.BigDecimal.TEN;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

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
        assertThat(productResponse.getName()).isEqualTo("Asprin");
    }


    @Test
    public void findAllTest_Success() {
        when(repository.findByStatus(any())).thenReturn(products);

        List<ProductResponse> products = service.findAll();

        assertThat(products).isNotEmpty();
        assertThat(products).hasSize(ProductServiceTest.products.size());
    }


    @Test
    @Ignore
    public void shouldGetNotFoundException_WhenDeleteWrongProduct() {
        when(repository.findById(any())).thenReturn(Optional.of(product));
        product.setId("1");

        assertThrows(ProductNotFoundException.class, () -> {
            service.delete("22222222222");
        });
    }

    @Test
    public void updateProductTest_Success() {
        product.setPrice(TEN);
        product.setStatus(ACTIVE);
        when(repository.findById(any())).thenReturn(Optional.of(product));
        when(repository.save(any())).thenReturn(product);

        productRequest.setPrice(ONE);
        ProductResponse update = service.update("1", productRequest);


        assertThat(update.getPrice()).isEqualTo(ONE);
    }
}