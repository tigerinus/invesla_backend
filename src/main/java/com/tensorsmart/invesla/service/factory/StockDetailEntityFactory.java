package com.tensorsmart.invesla.service.factory;

import com.tensorsmart.invesla.questrade.connector.response.SymbolDetailResponse;
import com.tensorsmart.invesla.repository.entity.StockDetailEntity;

public class StockDetailEntityFactory {

    private StockDetailEntityFactory() {
    }

    public static StockDetailEntity get(SymbolDetailResponse symbolDetailReponse) {
        StockDetailEntity stockDetailEntity = new StockDetailEntity();
        stockDetailEntity.setSymbolId(symbolDetailReponse.getSymbolId());
        stockDetailEntity.setPrevDayClosePrice(symbolDetailReponse.getPrevDayClosePrice());
        stockDetailEntity.setHighPrice52(symbolDetailReponse.getHighPrice52());
        stockDetailEntity.setLowPrice52(symbolDetailReponse.getLowPrice52());
        stockDetailEntity.setAverageVol3Months(symbolDetailReponse.getAverageVol3Months());
        stockDetailEntity.setAverageVol20Days(symbolDetailReponse.getAverageVol20Days());
        stockDetailEntity.setOutstandingShares(symbolDetailReponse.getOutstandingShares());
        stockDetailEntity.setEps(symbolDetailReponse.getEps());
        stockDetailEntity.setPe(symbolDetailReponse.getPe());
        stockDetailEntity.setDividend(symbolDetailReponse.getDividend());
        stockDetailEntity.setYield(symbolDetailReponse.getYield());
        stockDetailEntity.setMarketCap(symbolDetailReponse.getMarketCap());
        stockDetailEntity.setTradeUnit(symbolDetailReponse.getTradeUnit());
        stockDetailEntity.setDividendDate(symbolDetailReponse.getDividendDate());
        return stockDetailEntity;
    }
}
