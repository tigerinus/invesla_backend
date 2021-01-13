package com.tensorsmart.invesla.controller;

import java.util.ArrayList;
import java.util.List;

import com.tensorsmart.invesla.repository.entity.StockEntity;
import com.tensorsmart.invesla.service.StockService;

import org.openapitools.api.StocksApi;
import org.openapitools.model.Stock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/stocks")
public class StockController implements StocksApi {

    @Autowired
    private StockService _service;

    @Override
    public ResponseEntity<List<Stock>> getStocks() {
        List<Stock> result = new ArrayList<Stock>();
        Iterable<StockEntity> stocks = _service.getStocks();

        for (StockEntity stockEntity : stocks) {
            Stock stock = new Stock();
            stock.setSymbol(stockEntity.getSymbol());
        }

        return ResponseEntity.ok(result);
    }

    @Override
    public ResponseEntity<Void> addStocks(List<String> symbols) {

        _service.addStocks(symbols);

        return ResponseEntity.ok(null);
    }

    @Override
    public ResponseEntity<Void> deleteStock(String symbol) {
    
        _service.deleteStock(symbol);

        return ResponseEntity.ok(null);
    }
}
