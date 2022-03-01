package com.orderservice.config;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandKey;
import com.netflix.hystrix.HystrixCommandProperties;
import org.springframework.cache.Cache;

public class HystrixPutCommand extends HystrixCommand<Object> {
    private final Cache delegate;
    private final Object key;
    private final Object value;

    public HystrixPutCommand(Cache delegate, Object key, Object value) {
        super(Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("testGroupKey"))
                .andCommandKey(HystrixCommandKey.Factory.asKey("cache-put"))
                .andCommandPropertiesDefaults(
                        HystrixCommandProperties.defaultSetter()
                                .withExecutionTimeoutInMilliseconds(500)
                                .withCircuitBreakerErrorThresholdPercentage(50)
                                .withCircuitBreakerRequestVolumeThreshold(5)
                                .withMetricsRollingStatisticalWindowInMilliseconds(20000)));
        this.delegate = delegate;
        this.key = key;
        this.value = value;
    }

    @Override
    protected Object run() throws Exception {
        delegate.put(key, value);
        return null;
    }

    @Override
    protected Object getFallback() {
        System.out.println("put 에러");
        return null;
    }
}
