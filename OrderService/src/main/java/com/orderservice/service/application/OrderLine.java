package com.orderservice.service.application;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OrderLine {
    private long productId;
    private long quantity;

    public static OrderLine of(long productId, long quantity){
        return new OrderLine(productId, quantity);
    }
}
