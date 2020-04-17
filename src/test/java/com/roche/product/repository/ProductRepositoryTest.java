package com.roche.product.repository;


import com.roche.product.model.Product;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static java.math.BigDecimal.TEN;
import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class ProductRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private ProductRepository repository;

    @Test
    public void getAccountTest() {
        Product expected = entityManager.persist(new Product("Asprin", TEN));

        Optional<Product> productOptional = repository.findById(expected.getId());

        assertThat(productOptional.get().getName()).isEqualTo(expected.getName());
    }
}
