package com.orderservice.adapter;

import com.orderservice.service.application.PlaceOrderDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Getter
@NoArgsConstructor
public class PlaceOrderRequest {
    private List<OrderLine> orderLine;

    public PlaceOrderDto toDto(){
        return PlaceOrderDto.orderLineOf(orderLine.stream().map(OrderLine::toDto).collect(toList()));
    }
}
