package com.tensorsmart.invesla.service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import com.tensorsmart.invesla.questrade.connector.response.MarketResponse;
import com.tensorsmart.invesla.questrade.connector.response.QuoteResponse;
import com.tensorsmart.invesla.questrade.service.MarketService;
import com.tensorsmart.invesla.questrade.service.QuoteService;
import com.tensorsmart.invesla.repository.QuoteRepository;
import com.tensorsmart.invesla.repository.entity.QuoteEntity;
import com.tensorsmart.invesla.service.factory.QuoteEntityFactory;

import org.openapitools.model.Stock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class QuoteUpdateService {

    @Autowired
    private StockManagementService _stockManagementService;

    @Autowired
    private QuoteService _quoteService;

    @Autowired
    private MarketService _marketService;

    @Autowired
    private QuoteRepository _repository;

    public void updateQuotes(List<String> symbolIdList) {
        if (symbolIdList == null || symbolIdList.isEmpty()) return;

        List<String> symbolIdStillTradingList = symbolIdList.stream().filter(s -> isSymbolStillTrading(s)).collect(Collectors.toList());

        if (symbolIdStillTradingList.isEmpty()) {
            log.debug("no stock is in trading hours right now.");
            return;
        }

        List<QuoteResponse> quoteResponseList = _quoteService.getQuotes(symbolIdStillTradingList);

        if (quoteResponseList == null || quoteResponseList.isEmpty()) return;

        List<QuoteEntity> quoteEntityList = quoteResponseList.stream().map(response -> QuoteEntityFactory.get(response))
                .collect(Collectors.toList());

        _repository.saveAll(quoteEntityList);
    }

    private boolean isSymbolStillTrading(String id) {
        Stock stock = _stockManagementService.getStockById(id);

        if (stock == null) {
            log.error("unable to get stock info for id '{}', assuming the stock is still trading.", id);
            return true;
        }

        String marketName = stock.getMarket();

        MarketResponse market = _marketService.getMarket(marketName);

        if (market == null) {
            log.warn("unable to get market information for '{}', assuming the stock is still trading.", marketName);
            return true;
        }

        Date now = new Date();

        if (now.before(market.getExtendedStartTime())) return false;
        if (now.after(market.getExtendedEndTime())) return false;

        return true;
    }
}
