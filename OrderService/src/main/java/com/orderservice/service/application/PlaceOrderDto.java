package com.orderservice.service.application;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PlaceOrderDto {
    private List<OrderLine> orderLine;

    private PlaceOrderDto(List<OrderLine> orderLine){
        this.orderLine = orderLine;
    }

    public static PlaceOrderDto orderLineOf(List<OrderLine> orderLine){
        return new PlaceOrderDto(orderLine);
    }

    public List<Long> getProductIds() {
        return orderLine.stream().map(OrderLine::getProductId).collect(toList());
    }
}
