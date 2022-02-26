package com.orderservice.service.application.external;

import com.orderservice.config.FeignClientConfig;
import com.orderservice.service.application.Product;
import feign.hystrix.FallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Collections;
import java.util.List;

@FeignClient(name = "product", url = "http://localhost:8081",
             configuration = FeignClientConfig.class,
             fallbackFactory = ProductRepository.ProductRepositoryFallbackFactory.class)
public interface ProductRepository {

    @GetMapping("/api/v1/product")
    List<Product> findByIds();

    @Component
    class ProductRepositoryFallbackFactory implements FallbackFactory<ProductRepository> {

        @Override
        public ProductRepository create(Throwable cause) {
            return () -> {
                System.out.println("에러라고 ???");
                return Collections.emptyList();
            };
        }
    }
}

