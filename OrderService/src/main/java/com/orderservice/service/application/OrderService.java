package com.orderservice.service.application;

import com.orderservice.service.application.external.ProductService;
import com.orderservice.service.domain.Order;
import com.orderservice.service.domain.OrderLine;
import com.orderservice.service.domain.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
@Transactional
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;

    // external
    private final ProductService productService;

    public Long placeOrder(String orderer, PlaceOrderDto placeOrderDto) {
        List<Long> productIds = placeOrderDto.getProductIds();
        List<Product> products = productService.getProductsByIds(productIds);
        Order order = Order.of(
                orderer,
                products.stream().map(product -> OrderLine.of(product.getName(), product.getQuantity(), product.getPrice()))
                        .collect(toList())
        );
        orderRepository.save(order);
        return order.getId();
    }
}
