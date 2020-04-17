package com.roche.product.model;

import java.math.BigDecimal;
import java.util.Date;
import java.util.function.Consumer;

public class ProductResponseBuilder {
    public String id;
    public String name;
    public BigDecimal price;
    public Date date;


    public ProductResponseBuilder with(
            Consumer<ProductResponseBuilder> builderFunction) {
        builderFunction.accept(this);
        return this;
    }

    public ProductResponse build() {
        return new ProductResponse(id,name,price,date);
    }

}
