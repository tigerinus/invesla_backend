package com.tensorsmart.invesla.questrade.connector.resttemplate;

import com.tensorsmart.invesla.questrade.connector.resttemplate.interceptor.LoggingInterceptor;
import com.tensorsmart.invesla.questrade.connector.resttemplate.interceptor.TokenInterceptor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class Config {

    @Bean
    public RestTemplate restTemplateWithoutHeader(LoggingInterceptor loggingInterceptor) {
        RestTemplate restTemplate = new RestTemplate();

        restTemplate.getInterceptors().add(loggingInterceptor);

        return restTemplate;
    }

    @Bean
    public RestTemplate restTemplateWithHeader( //
            TokenInterceptor tokenInterceptor, LoggingInterceptor loggingInterceptor) {

        RestTemplate restTemplate = new RestTemplate();

        restTemplate.getInterceptors().add(tokenInterceptor);
        restTemplate.getInterceptors().add(loggingInterceptor);

        return restTemplate;
    }
}
