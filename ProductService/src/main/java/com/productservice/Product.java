package com.productservice;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Product {
    private String name;
    private long price;
    private long quantity;

    public static Product of(String name, long price, long quantity){
        return new Product(name, price, quantity);
    }
}
