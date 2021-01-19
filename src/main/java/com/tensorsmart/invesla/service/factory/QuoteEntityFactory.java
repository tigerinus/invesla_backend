package com.tensorsmart.invesla.service.factory;

import com.tensorsmart.invesla.questrade.connector.response.QuoteResponse;
import com.tensorsmart.invesla.repository.entity.QuoteEntity;

public class QuoteEntityFactory {
    private QuoteEntityFactory() {
    }

    public static QuoteEntity get(QuoteResponse quoteResponse) {
        QuoteEntity quoteEntity = new QuoteEntity();

        quoteEntity.setSymbolId(quoteResponse.getSymbolId());
        quoteEntity.setBidPrice(quoteResponse.getBidPrice());
        quoteEntity.setBidSize(quoteResponse.getBidSize());
        quoteEntity.setAskPrice(quoteResponse.getAskPrice());
        quoteEntity.setAskSize(quoteResponse.getAskSize());
        quoteEntity.setLastTradePriceTrHrs(quoteResponse.getLastTradePriceTrHrs());
        quoteEntity.setLastTradePrice(quoteResponse.getLastTradePrice());
        quoteEntity.setLastTradeSize(quoteResponse.getLastTradeSize());
        quoteEntity.setLastTradeTick(quoteResponse.getLastTradeTick());
        quoteEntity.setLastTradeTime(quoteResponse.getLastTradeTime());
        quoteEntity.setVolume(quoteResponse.getVolume());
        quoteEntity.setOpenPrice(quoteResponse.getOpenPrice());
        quoteEntity.setHighPrice(quoteResponse.getHighPrice());
        quoteEntity.setLowPrice(quoteResponse.getLowPrice());
        quoteEntity.setDelay(quoteResponse.getDelay());
        quoteEntity.setHalted(quoteResponse.isHalted());
        quoteEntity.setHigh52w(quoteResponse.getHigh52w());
        quoteEntity.setLow52w(quoteResponse.getLow52w());
        
        return quoteEntity;
    }
}
