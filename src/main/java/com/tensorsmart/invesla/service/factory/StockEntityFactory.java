package com.tensorsmart.invesla.service.factory;

import com.tensorsmart.invesla.questrade.connector.response.SymbolResponse;
import com.tensorsmart.invesla.repository.entity.StockEntity;

public class StockEntityFactory {
    private StockEntityFactory() {
    }

    public static StockEntity get(SymbolResponse symbolResponse) {
        StockEntity stockEntity = new StockEntity();
        stockEntity.setSymbol(symbolResponse.getSymbol());
        stockEntity.setSymbolId(symbolResponse.getSymbolId());
        stockEntity.setDescription(symbolResponse.getDescription());
        stockEntity.setListingExchange(symbolResponse.getListingExchange());
        stockEntity.setCurrency(symbolResponse.getCurrency());
        return stockEntity;
    }
}
