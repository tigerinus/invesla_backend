package com.tensorsmart.invesla.service.wrapper;

import com.tensorsmart.invesla.repository.entity.StockEntity;

import org.openapitools.model.Stock;

public class StockWrapper extends Stock {
    public StockWrapper(StockEntity stockEntity) {
        setSymbol(stockEntity.getSymbol());
        setSymbolId(stockEntity.getSymbolId());
        setMarket(stockEntity.getListingExchange());
    }
}
