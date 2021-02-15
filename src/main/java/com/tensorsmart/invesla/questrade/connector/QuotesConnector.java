package com.tensorsmart.invesla.questrade.connector;

import java.util.List;

import com.tensorsmart.invesla.questrade.connector.response.QuoteListResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class QuotesConnector {

    @Autowired
    @Qualifier("restTemplateWithHeader")
    private RestTemplate _restTemplate;

    public QuoteListResponse getQuotes(List<String> symbolIdList) {
        if (symbolIdList == null || symbolIdList.isEmpty())
            return null;

        String ids = String.join(",", symbolIdList);

        ResponseEntity<QuoteListResponse> response;

        try {
            response = _restTemplate.getForEntity("v1/markets/quotes?ids=" + ids, QuoteListResponse.class);
        } catch (RestClientException e) {
            log.error(e.getMessage(), e);
            return null;
        }

        return response.getBody();
    }

}
