package com.tensorsmart.invesla.questrade.connector.response;

import java.util.Date;
import java.util.List;

import lombok.Getter;

@Getter
public class MarketResponse {
    private String name;
    private List<String> tradingVenues;
    private String defaultTradingVenue;
    private List<String> primaryOrderRoutes;
    private List<String> secondaryOrderRoutes;
    private List<String> level1Feeds;
    private List<String> level2Feeds;
    private Date extendedStartTime;
    private Date startTime;
    private Date endTime;
    private Date extendedEndTime;
    private long snapQuotesLimit;
}
