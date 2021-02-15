package com.tensorsmart.invesla.questrade.connector.response;

import java.util.List;

import lombok.Getter;

@Getter
public class MarketListResponse {
    private List<MarketResponse> markets;
}
