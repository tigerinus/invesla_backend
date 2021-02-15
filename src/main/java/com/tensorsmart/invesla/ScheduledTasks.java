package com.tensorsmart.invesla;

import java.util.ArrayList;
import java.util.List;

import com.tensorsmart.invesla.service.QuoteUpdateService;
import com.tensorsmart.invesla.service.StockManagementService;
import com.tensorsmart.invesla.service.StockDetailUpdateService;

import org.openapitools.model.Stock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduledTasks {

    private static boolean DEBUG = false;

    @Autowired
    private StockManagementService _stockService;

    @Autowired
    private QuoteUpdateService _quoteUpdateService;

    @Autowired
    private StockDetailUpdateService _stockUpdateService;

    @Scheduled(fixedDelay = 5000, initialDelay = 5000)
    public void refreshQuotes() {
        if (ScheduledTasks.DEBUG) {
            return;
        }

        List<String> symbolIdList = new ArrayList<String>();
        for (Stock s : _stockService.getStocks()) {
            symbolIdList.add(s.getSymbolId());
        }

        _quoteUpdateService.updateQuotes(symbolIdList);
    }

    @EventListener(ApplicationReadyEvent.class)
    @Scheduled(zone = "EST", cron = "0 0 6 * * MON-FRI")
    public void refreshSymbols() {
        if (ScheduledTasks.DEBUG) {
            return;
        }

        List<String> symbolIdList = new ArrayList<String>();
        for (Stock s : _stockService.getStocks()) {
            symbolIdList.add(s.getSymbolId());
        }

        _stockUpdateService.updateStocks(symbolIdList);
    }
}
