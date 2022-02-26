package com.productservice;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static java.util.Arrays.asList;

@RestController
@RequestMapping("/api/v1/product")
public class ProductController {

    @GetMapping
    public List<Product> getProducts(){
        throw new IllegalArgumentException();
//        return asList(
//                Product.of("상품명1", 10000, 100),
//                Product.of("상품명2", 20000, 200)
//            );
    }
}
