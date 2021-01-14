package com.tensorsmart.invesla.questrade.connector;

import java.util.List;

import com.tensorsmart.invesla.questrade.connector.response.QuoteListResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.springframework.web.client.RestTemplate;

@Component
public class QuotesConnector {

    @Autowired
    @Qualifier("restTemplateWithHeader")
    private RestTemplate _restTemplate;

    public QuoteListResponse getQuotes(List<String> symbolIdList) {
        Assert.notNull(symbolIdList, "symbolIdList should not be null");
        Assert.notEmpty(symbolIdList, "symbolIdList should not be empty");

        String ids = String.join(",", symbolIdList);

        ResponseEntity<QuoteListResponse> response = _restTemplate.getForEntity("v1/markets/quotes?ids=" + ids,
                QuoteListResponse.class);

        return response.getBody();
    }

}
