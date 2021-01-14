package com.tensorsmart.invesla.service;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.openapitools.model.Stock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class StockServiceTest {
    
    @Autowired
    private StockService _service;

    @Test
    public void test1() {
        List<String> symbols = Arrays.asList("AI", "QS");

        // delete
        symbols.forEach(_service::deleteStock);

        // verify
        for (Stock stock : _service.getStocks()) {
            if (symbols.contains(stock.getSymbol())) {
                fail("Symbols should not exist.");
            }
        }
        
        // add
        _service.addStocks(symbols);

        // verify
        List<String> actualSymbols = new ArrayList<String>();
        for (Stock stock : _service.getStocks()) {
            actualSymbols.add(stock.getSymbol());
        }

        assertTrue(actualSymbols.containsAll(symbols));
        assertTrue(symbols.containsAll(actualSymbols));

        // delete
        symbols.forEach(_service::deleteStock);

        // verify
        for (Stock stock : _service.getStocks()) {
            if (symbols.contains(stock.getSymbol())) {
                fail("Symbols should not exist.");
            }
        }
    }
}
