package com.orderservice.config;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandKey;
import com.netflix.hystrix.HystrixCommandProperties;
import feign.Feign;
import feign.Logger;
import feign.hystrix.HystrixFeign;
import feign.hystrix.SetterFactory;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static com.netflix.hystrix.HystrixCommand.Setter.withGroupKey;
import static com.netflix.hystrix.HystrixCommandKey.Factory.asKey;
import static com.netflix.hystrix.HystrixCommandProperties.defaultSetter;

@EnableFeignClients(basePackages = "com.orderservice")
@Configuration
public class FeignClientConfig {
    @Bean
    Feign.Builder feignBuilder() {
        SetterFactory setterFactory = (target, method) ->
                withGroupKey(HystrixCommandGroupKey.Factory.asKey(target.name()))
                .andCommandKey(asKey(Feign.configKey(target.type(), method)))
                .andCommandPropertiesDefaults(defaultSetter()
                        .withExecutionTimeoutInMilliseconds(3000)
                        .withExecutionIsolationStrategy(HystrixCommandProperties.ExecutionIsolationStrategy.THREAD)
                        .withMetricsRollingStatisticalWindowInMilliseconds(10000) // 기준시간
                        .withCircuitBreakerSleepWindowInMilliseconds(100000) // 서킷 열려있는 시간
                        .withCircuitBreakerErrorThresholdPercentage(50)
                .withCircuitBreakerRequestVolumeThreshold(5)); // 최소 호출 횟수
        return HystrixFeign.builder()
                .logLevel(Logger.Level.BASIC)
                .setterFactory(setterFactory);
    }
}
