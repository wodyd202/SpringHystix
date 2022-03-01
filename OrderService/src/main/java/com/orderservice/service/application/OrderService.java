package com.orderservice.service.application;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.orderservice.service.application.external.ProductService;
import com.orderservice.service.domain.Order;
import com.orderservice.service.domain.OrderLine;
import com.orderservice.service.domain.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
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

    @Cacheable(value = "order", key = "#id")
    public Order getOrder(Long id){
        return orderRepository.findById(id).get();
    }

    @HystrixCommand(fallbackMethod = "placeOrderFallback",
    commandProperties = {
            @HystrixProperty(name = "execution.isolation.semaphore.maxConcurrentRequests", value = "3"),
            @HystrixProperty(name = "execution.isolation.strategy", value = "SEMAPHORE")
    })
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

    Long placeOrderFallback(String orderer, PlaceOrderDto placeOrderDto){
        System.out.println("????");
        return 123L;
    }
}
