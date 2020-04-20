package com.roche.product.controller;


import com.roche.product.model.ProductRequest;
import com.roche.product.model.ProductResponse;
import com.roche.product.repository.ProductRepository;
import com.roche.product.service.ProductService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Arrays;
import java.util.Date;

import static java.math.BigDecimal.ONE;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@ExtendWith(SpringExtension.class)
@WebMvcTest
public class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductService service;

    @MockBean
    private ProductRepository repository;

    @Test
    public void shouldSuccesResponse_WhenPostProduct() throws Exception {
        ProductRequest request = new ProductRequest();
        request.setName("Asprin");
        request.setPrice(ONE);
        ProductResponse response = new ProductResponse("1", request.getName(), request.getPrice(), new Date());


        when(service.create(any())).thenReturn(response);

        ResultActions resultActions = mockMvc.perform(post("/api/products").contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON).content("{\"name\":\"Asprin\"}"))
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
                .andExpect(jsonPath("name").value("Asprin"));
    }

    @Test
    public void shouldGetWholeProductList_WhenGetList() throws Exception {
        ProductRequest request = new ProductRequest();
        request.setName("Asprin");
        request.setPrice(ONE);
        ProductResponse response = new ProductResponse("1", request.getName(), request.getPrice(), new Date());

        when(service.findAll()).thenReturn(Arrays.asList(response));

        mockMvc.perform(get("/api/products").contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)).andExpect(MockMvcResultMatchers.status().is2xxSuccessful());
    }

    @Test
    public void shouldDeleteRecord_WhenDelete() throws Exception {
        ProductRequest request = new ProductRequest();
        request.setName("Asprin");
        request.setPrice(ONE);
        ProductResponse response = new ProductResponse("1", request.getName(), request.getPrice(), new Date());

        doNothing().when(service).delete(anyString());

        mockMvc.perform(delete("/api/products/{id}", "1").contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)).andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void shouldUpdateRecord_WhenUpdate() throws Exception {
        ProductRequest request = new ProductRequest();
        request.setName("Asprin");
        request.setPrice(ONE);
        ProductResponse response = new ProductResponse("1", request.getName(), request.getPrice(), new Date());
        ProductResponse update = new ProductResponse("1", "updated", request.getPrice(), new Date());

        when(service.update(any(), any())).thenReturn(update);

        mockMvc.perform(put("/api/products/1").content("{\"name\":\"Asprin\",\"price\":100}").contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)).andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
                .andExpect(jsonPath("name").value("updated"));
    }

}