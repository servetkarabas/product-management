package com.roche.product.model;

public enum StatusType {

    ACTIVE(1,"ACTIVE"), DELETED(2,"DELETED");
    int id;
    String  name;

    StatusType(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
