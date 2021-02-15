package com.tensorsmart.invesla.questrade.service;

import java.util.List;

import com.tensorsmart.invesla.questrade.connector.QuotesConnector;
import com.tensorsmart.invesla.questrade.connector.response.QuoteListResponse;
import com.tensorsmart.invesla.questrade.connector.response.QuoteResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class QuoteService {
    
    @Autowired
    private QuotesConnector _connector;

    public List<QuoteResponse> getQuotes(List<String> symbolIdList) {
        if (symbolIdList == null || symbolIdList.isEmpty()) return null;

        QuoteListResponse response = _connector.getQuotes(symbolIdList);

        if (response == null) return null;

        return response.getQuotes();
    }

}
