package com.tensorsmart.invesla.controller;

import java.util.ArrayList;
import java.util.List;

import org.openapitools.api.StocksApi;
import org.openapitools.model.Stock;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StocksController implements StocksApi {

    @Override
    public ResponseEntity<List<Stock>> getStocks() {
        List<Stock> result = new ArrayList<Stock>();
        return ResponseEntity.ok(result);
    }
}
