package com.orderservice.config;

import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.context.annotation.Configuration;

@EnableHystrix
@EnableCircuitBreaker
@Configuration
public class HystrixConfig {
}
