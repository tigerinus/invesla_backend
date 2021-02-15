package com.tensorsmart.invesla.questrade.connector.resttemplate.interceptor;

import java.io.IOException;

import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class LoggingInterceptor implements ClientHttpRequestInterceptor {

    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution)
            throws IOException {

        ClientHttpResponse response = execution.execute(request, body);
        if (response.getStatusCode().is2xxSuccessful()) {
            log.info("{} {} [{}]", request.getMethod(), request.getURI(), response.getStatusCode());
        } else {
            log.error("{} {} [{}]", request.getMethod(), request.getURI(), response.getStatusCode());
        }
        return response;
    }

}
