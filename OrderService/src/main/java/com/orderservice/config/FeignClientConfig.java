package com.orderservice.config;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandKey;
import com.netflix.hystrix.HystrixCommandProperties;
import feign.Feign;
import feign.hystrix.HystrixFeign;
import feign.hystrix.SetterFactory;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@EnableFeignClients(basePackages = "com.orderservice")
@Configuration
public class FeignClientConfig {
    @Bean
    Feign.Builder feignBuilder() {
        SetterFactory setterFactory = (target, method) -> HystrixCommand.Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey(target.name()))
                .andCommandKey(HystrixCommandKey.Factory.asKey(Feign.configKey(target.type(), method)))
                // 위는 groupKey와 commandKey 설정
                // 아래는 properties 설정
                .andCommandPropertiesDefaults(HystrixCommandProperties.defaultSetter()
                        .withExecutionIsolationStrategy(HystrixCommandProperties.ExecutionIsolationStrategy.SEMAPHORE)
                        .withMetricsRollingStatisticalWindowInMilliseconds(10000) // 기준시간
                        .withCircuitBreakerSleepWindowInMilliseconds(3000) // 서킷 열려있는 시간
                        .withCircuitBreakerErrorThresholdPercentage(50)
                .withCircuitBreakerRequestVolumeThreshold(5)); // 최소 호출 횟수
        return HystrixFeign.builder().setterFactory(setterFactory);
    }
}
