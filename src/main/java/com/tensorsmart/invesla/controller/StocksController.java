package com.tensorsmart.invesla.controller;

import java.util.ArrayList;
import java.util.List;

import org.openapitools.api.StocksApi;
import org.openapitools.model.Stock;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/stocks")
public class StocksController implements StocksApi {

    @Override
    @GetMapping
    public ResponseEntity<List<Stock>> getStocks() {
        List<Stock> result = new ArrayList<Stock>();
        return ResponseEntity.ok(result);
    }

    @PostMapping
    public ResponseEntity<Void> addStocks() {
        return ResponseEntity.ok(null);
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteStock() {
        return ResponseEntity.ok(null);
    }
}
