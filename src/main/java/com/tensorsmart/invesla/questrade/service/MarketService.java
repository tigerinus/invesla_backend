package com.tensorsmart.invesla.questrade.service;

import java.util.HashMap;
import java.util.Map;

import com.tensorsmart.invesla.questrade.connector.MarketConnector;
import com.tensorsmart.invesla.questrade.connector.response.MarketListResponse;
import com.tensorsmart.invesla.questrade.connector.response.MarketResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

@Component
public class MarketService {

    @Autowired
    private MarketConnector _connector;

    private Map<String, MarketResponse> _marketMap = new HashMap<>();

    public MarketResponse getMarket(String name) {
        Assert.hasText(name, "name should not be empty.");
        return _marketMap.get(name);
    }

    public void updateMarkets() {
        MarketListResponse marketList = _connector.getMarkets();

        if (marketList == null) return;

        for(MarketResponse market : marketList.getMarkets()) {
            _marketMap.put(market.getName(), market);
        }
    }
}
