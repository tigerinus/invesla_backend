package com.tensorsmart.invesla.questrade.connector.response;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;

@Getter
public class QuoteResponse {

    private String symbol;
    private String symbolId;
    private String tier;
    private float bidPrice;
    private long bidSize;
    private float askPrice;
    private long askSize;
    private float lastTradePriceTrHrs;
    private float lastTradePrice;
    private long lastTradeSize;
    private String lastTradeTick;
    private Date lastTradeTime;
    private long volume;
    private float openPrice;
    private float highPrice;
    private float lowPrice;
    private long delay;
    private boolean isHalted;
    private float high52w;
    private float low52w;

    @JsonProperty("VWAP")
    private float volumeWeightedAveragePrice;
}
