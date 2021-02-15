package com.tensorsmart.invesla.controller;

import java.util.List;

import com.tensorsmart.invesla.service.StockManagementService;

import org.openapitools.api.StocksApi;
import org.openapitools.model.Stock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StockController implements StocksApi {

    @Autowired
    private StockManagementService _service;

    @Override
    public ResponseEntity<List<Stock>> getStocks() {
        List<Stock> result = _service.getStocks();
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
