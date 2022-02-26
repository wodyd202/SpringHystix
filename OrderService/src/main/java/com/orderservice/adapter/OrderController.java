package com.orderservice.adapter;

import com.orderservice.service.application.OrderService;
import com.orderservice.service.application.PlaceOrderDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/api/v1/order")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @PostMapping
    public ResponseEntity<Long> placeOrder(@RequestHeader("user") String userId,
                                           @RequestBody PlaceOrderRequest request) {
        PlaceOrderDto placeOrderDto = request.toDto();
        Long orderId = orderService.placeOrder(userId, placeOrderDto);
        return ResponseEntity.created(URI.create(String.valueOf(orderId))).build();
    }
}
