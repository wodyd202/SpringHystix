package com.orderservice.service.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;

@Embeddable
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OrderLine {
    private String productName;
    private long quantity;
    private long price;

    public static OrderLine of(String productName, long quantity, long price){
        return new OrderLine(productName, quantity, price);
    }
}
