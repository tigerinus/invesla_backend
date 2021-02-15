package com.tensorsmart.invesla.questrade.connector;

import com.tensorsmart.invesla.questrade.connector.response.MarketListResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class MarketConnector {
    
    @Autowired
    @Qualifier("restTemplateWithHeader")
    private RestTemplate _restTemplate;

    public MarketListResponse getMarkets() {
        ResponseEntity<MarketListResponse> response = _restTemplate.getForEntity("v1/markets", MarketListResponse.class);

        return response.getBody();
    }
}
