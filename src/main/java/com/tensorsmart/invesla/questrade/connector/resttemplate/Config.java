package com.tensorsmart.invesla.questrade.connector.resttemplate;

import com.tensorsmart.invesla.questrade.connector.resttemplate.interceptor.TokenInterceptor;
import com.tensorsmart.invesla.questrade.connector.resttemplate.interceptor.LoggingInterceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class Config {

    @Autowired
    private LoggingInterceptor _loggingInterceptor;

    @Autowired
    private TokenInterceptor _tokenInterceptor;

    @Bean
	public RestTemplate restTemplateWithoutHeader() {
        RestTemplate restTemplate = new RestTemplate();

        restTemplate.getInterceptors().add(_loggingInterceptor);

        return restTemplate;
    }

    @Bean
    public RestTemplate restTemplateWithHeader() {

        RestTemplate restTemplate = new RestTemplate();

        restTemplate.getInterceptors().add(_tokenInterceptor);
        restTemplate.getInterceptors().add(_loggingInterceptor);

        return restTemplate;
    }
}
