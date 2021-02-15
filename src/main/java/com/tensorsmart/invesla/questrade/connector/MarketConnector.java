package com.tensorsmart.invesla.questrade.connector;

import com.tensorsmart.invesla.questrade.connector.response.MarketListResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class MarketConnector {

    @Autowired
    @Qualifier("restTemplateWithHeader")
    private RestTemplate _restTemplate;

    public MarketListResponse getMarkets() {
        ResponseEntity<MarketListResponse> response;

        try {
            response = _restTemplate.getForEntity("v1/markets", MarketListResponse.class);
        } catch (RestClientException e) {
            log.error(e.getMessage(), e);
            return null;
        }
        return response.getBody();
    }
}
