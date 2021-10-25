package com.tensorsmart.invesla.questrade.service;

import java.util.ArrayList;
import java.util.List;

import com.tensorsmart.invesla.questrade.connector.SymbolsConnector;
import com.tensorsmart.invesla.questrade.connector.response.SymbolResponse;
import com.tensorsmart.invesla.questrade.connector.response.SymbolDetailListResponse;
import com.tensorsmart.invesla.questrade.connector.response.SymbolDetailResponse;
import com.tensorsmart.invesla.questrade.connector.response.SymbolListResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SymbolService {

    @Autowired
    private SymbolsConnector _connector;

    public SymbolResponse getSymbolByName(String symbol) {
        SymbolListResponse response = _connector.searchSymbols(symbol);

        if (response == null) return null;

        List<SymbolResponse> list = response.getSymbols();

        for (SymbolResponse s : list) {
            if (s.getSymbol().equals(symbol)) {
                return s;
            }
        }

        return null;
    }

    public List<SymbolDetailResponse> getSymbolDetailByIds(List<String> ids) {
        for (String id : ids) {
            if (id.isEmpty()) {
                throw new IllegalArgumentException("ids");
            }
        }

        SymbolDetailListResponse response = _connector.getSymbols(ids);

        if (response == null) {
            return new ArrayList<>(0);
        }

        return response.getSymbols();
    }
}
