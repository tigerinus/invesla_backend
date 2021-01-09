package com.tensorsmart.invesla.questrade.connector.response;

import lombok.Getter;

@Getter
public class PositionResponse {
    String symbol;
    int symbolId;
    double openQuantity;
    double closedQuantity;
    double currentMarketValue;
    double currentPrice;
    double averageEntryPrice;
    double closedPnL;
    double openPnL;
    double totalCost;
    boolean isRealTime;
    boolean isUnderReorg;
}
