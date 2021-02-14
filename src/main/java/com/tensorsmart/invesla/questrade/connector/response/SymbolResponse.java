package com.tensorsmart.invesla.questrade.connector.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;

@Getter
public class SymbolResponse {

    private String symbol;
    private String symbolId;
    private String description;
    private SecurityType securityType;

    
    private String listingExchange;
    private Boolean isTradable;
    private Boolean isQuotable;
    private String currency;

    public enum SecurityType {
        @JsonProperty("Stock")
        STOCK,

        @JsonProperty("Option")
        OPTION,

        @JsonProperty("Bond")
        BOND,

        @JsonProperty("Right")
        RIGHT,

        @JsonProperty("Gold")
        GOLD,

        @JsonProperty("MutualFund")
        MUTUAL_FUND,

        @JsonProperty("Index")
        INDEX
    }
}
