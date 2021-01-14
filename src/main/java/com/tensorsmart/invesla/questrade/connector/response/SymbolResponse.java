package com.tensorsmart.invesla.questrade.connector.response;

import lombok.Getter;

@Getter
public class SymbolResponse {

    private String symbol;
    private String symbolId;
    private String description;
    private String securityType;
    private String listingExchange;
    private boolean isTradable;
    private boolean isQuotable;
    private String currency;

}
