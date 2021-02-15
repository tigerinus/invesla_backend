package com.tensorsmart.invesla.questrade.connector;

import java.util.List;

import com.tensorsmart.invesla.questrade.connector.response.SymbolDetailListResponse;
import com.tensorsmart.invesla.questrade.connector.response.SymbolListResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class SymbolsConnector {

    @Autowired
    @Qualifier("restTemplateWithHeader")
    private RestTemplate _restTemplate;

    public SymbolListResponse searchSymbols(String prefix) {
        Assert.hasText(prefix, "prefix should not be empty");

        ResponseEntity<SymbolListResponse> response;

        try {
            response = _restTemplate.getForEntity("v1/symbols/search?prefix=" + prefix, SymbolListResponse.class);
        } catch (RestClientException e) {
            log.error(e.getMessage(), e);
            return null;
        }

        return response.getBody();
    }

    public SymbolDetailListResponse getSymbols(List<String> ids) {
        Assert.notNull(ids, "ids should not be null");
        Assert.notEmpty(ids, "ids should not be empty");

        ResponseEntity<SymbolDetailListResponse> response;
        String joinedIds = String.join(",", ids);

        try {
            response = _restTemplate.getForEntity("v1/symbols?ids=" + joinedIds, SymbolDetailListResponse.class);
        } catch (RestClientException e) {
            log.error(e.getMessage(), e);
            return null;
        }

        return response.getBody();
    }
}
