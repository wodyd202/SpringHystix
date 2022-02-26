package com.orderservice.service.application.external;

import com.orderservice.service.application.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    public List<Product> getProductsByIds(List<Long> productIds) {
        return productRepository.findByIds();
    }
}
