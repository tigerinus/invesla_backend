package com.tensorsmart.invesla.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.tensorsmart.invesla.ScheduledTasks;
import org.junit.jupiter.api.Test;
import org.openapitools.model.Stock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
public class StockServiceTest {

    @Autowired
    private StockManagementService _service;

    @MockBean
    private ScheduledTasks _scheduledTasks; // disable scheduledTasks

    private final String symbol1 = "AI";
    private final String symbolId1 = "33418188";
    private final String symbol2 = "QS";
    private final String symbolId2 = "33317976";
    
    @Test
    public void test1() {
        List<String> symbols = Arrays.asList(symbol1, symbol2);

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
        List<Stock> stockList = _service.getStocks();
        
        assertEquals(2, stockList.size());

        Map<String, Stock> stockMap = stockList.stream().collect(Collectors.toMap(Stock::getSymbol, stock -> stock));
        assertEquals(stockMap.get(symbol1).getSymbolId(), symbolId1);
        assertEquals(stockMap.get(symbol2).getSymbolId(), symbolId2);

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
