package com.tensorsmart.invesla.questrade.connector.response;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class QuoteListResponse {

    private List<QuoteResponse> quotes;

}
