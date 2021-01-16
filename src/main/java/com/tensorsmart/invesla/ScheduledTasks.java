package com.tensorsmart.invesla;

import java.util.ArrayList;
import java.util.List;

import com.tensorsmart.invesla.questrade.service.QuoteService;
import com.tensorsmart.invesla.service.StockService;

import org.openapitools.model.Stock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class ScheduledTasks {

    @Autowired
    StockService _stockService;

    @Autowired
    QuoteService _quoteService;

    @Scheduled(fixedDelay = 1000, initialDelay = 5000)
    public void refreshQuotes() {
        log.info("refreshing quotes...");

        List<String> symbolIdList = new ArrayList<String>();
        for (Stock s : _stockService.getStocks()) {
            symbolIdList.add(s.getSymbolId());
        }

        _quoteService.getQuotes(symbolIdList);


    }
}
