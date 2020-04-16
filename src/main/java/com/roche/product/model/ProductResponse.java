package com.roche.product.model;


import java.math.BigDecimal;
import java.util.Date;

public class ProductResponse {
    private String id;
    private String name;
    private BigDecimal price;
    private Date date;

    public ProductResponse(String id, String name, BigDecimal price, Date date) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.date = date;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
