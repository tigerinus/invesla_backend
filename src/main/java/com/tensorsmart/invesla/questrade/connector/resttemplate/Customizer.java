package com.tensorsmart.invesla.questrade.connector.resttemplate;

import com.tensorsmart.invesla.questrade.connector.resttemplate.interceptor.LoggingInterceptor;

import org.springframework.boot.web.client.RestTemplateCustomizer;
import org.springframework.web.client.RestTemplate;

public class Customizer implements RestTemplateCustomizer {

    @Override
    public void customize(RestTemplate restTemplate) {
        restTemplate.getInterceptors().add(new LoggingInterceptor());
    }
    
}
