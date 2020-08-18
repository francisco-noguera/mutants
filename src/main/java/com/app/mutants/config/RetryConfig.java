package com.app.mutants.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.retry.backoff.UniformRandomBackOffPolicy;
import org.springframework.retry.policy.SimpleRetryPolicy;
import org.springframework.retry.support.RetryTemplate;

@Configuration
@EnableRetry
public class RetryConfig {
	
    @Bean
    public RetryTemplate retryTemplate() {
    SimpleRetryPolicy simpleRetryPolicy = new SimpleRetryPolicy();
    simpleRetryPolicy.setMaxAttempts(2);
   
    UniformRandomBackOffPolicy uniformRandomBackOffPolicy = new UniformRandomBackOffPolicy();
    uniformRandomBackOffPolicy.setMinBackOffPeriod(500);
    uniformRandomBackOffPolicy.setMaxBackOffPeriod(1000);
   
        RetryTemplate retryTemplate = new RetryTemplate();
        retryTemplate.setBackOffPolicy(uniformRandomBackOffPolicy);
        retryTemplate.setRetryPolicy(simpleRetryPolicy);

        return retryTemplate;
    }
}
