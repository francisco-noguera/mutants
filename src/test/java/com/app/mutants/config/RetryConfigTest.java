package com.app.mutants.config;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.retry.support.RetryTemplate;

@RunWith(MockitoJUnitRunner.class)
public class RetryConfigTest {

	@InjectMocks
	private RetryConfig retryConfig;
	
	@Test
	public void retryTemplate() {
		RetryTemplate template = retryConfig.retryTemplate();
		assertNotNull(template);
	}
	
}
