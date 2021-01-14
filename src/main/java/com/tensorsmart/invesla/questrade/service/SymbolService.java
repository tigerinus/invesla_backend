package com.tensorsmart.invesla.questrade.service;

import java.util.List;

import com.tensorsmart.invesla.questrade.connector.SymbolsConnector;
import com.tensorsmart.invesla.questrade.connector.response.SymbolResponse;
import com.tensorsmart.invesla.questrade.connector.response.SymbolListResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SymbolService {

    @Autowired
    private SymbolsConnector _connector;

    public SymbolResponse getSymbol(String symbol) {
        SymbolListResponse response = _connector.searchSymbols(symbol);

        List<SymbolResponse> list = response.getSymbols();

        for (SymbolResponse s : list) {
            if (s.getSymbol().equals(symbol)) {
                return s;
            }
        }

        return null;
    }
}
