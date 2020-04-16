package com.roche.product.controller;


import com.roche.product.model.Product;
import com.roche.product.model.ProductRequest;
import com.roche.product.model.ProductResponse;
import com.roche.product.service.ProductService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;


import static java.math.BigDecimal.*;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@ExtendWith(SpringExtension.class)
@WebMvcTest
public class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductService service;


    @Test
    public void shouldSuccesResponse_WhenPostProduct() throws Exception {
        Product product = new Product("Asprin", ONE);
        ProductRequest productRequest = new ProductRequest();
        productRequest.setName("Asprin");
        productRequest.setPrice(ONE);
        ProductResponse productResponse = new ProductResponse();


        Mockito.when(service.create(any())).thenReturn(productResponse);

        ResultActions resultActions = mockMvc.perform(post("/api/products").contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON).content("{\"name\":\"Asprin\"}"))
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
                .andExpect(jsonPath("name").value("Asprin"));
    }

    @Test
    public void shouldGetWholeProductList_WhenGetList() throws Exception {
        mockMvc.perform(get("/api/products").contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)).andExpect(MockMvcResultMatchers.status().is2xxSuccessful());
//                .andExpect(jsonPath("name").exists());
    }


}