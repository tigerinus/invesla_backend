package com.tensorsmart.invesla.questrade.connector.response;

import lombok.Getter;

@Getter
public class SymbolResponse {

    String symbol;
    String symbolId;
    String description;
    String securityType;
    String listingExchange;
    boolean isTradable;
    boolean isQuotable;
    String currency;
}
