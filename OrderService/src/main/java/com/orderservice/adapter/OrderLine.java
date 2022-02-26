package com.orderservice.adapter;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OrderLine {
    private long productId;
    private long quantity;

    public com.orderservice.service.application.OrderLine toDto(){
        return com.orderservice.service.application.OrderLine.of(productId, quantity);
    }
}
