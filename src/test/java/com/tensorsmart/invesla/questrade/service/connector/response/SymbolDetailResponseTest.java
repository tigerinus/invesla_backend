package com.tensorsmart.invesla.questrade.service.connector.response;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tensorsmart.invesla.questrade.connector.response.SymbolDetailListResponse;
import com.tensorsmart.invesla.questrade.connector.response.SymbolDetailResponse;

import org.junit.jupiter.api.Test;

class SymbolDetailResponseTest {

    @Test
    void deserializationTest() throws JsonMappingException, JsonProcessingException {
        String json = "{\"symbols\":[{\"symbol\":\"AAPL\",\"symbolId\":8049,\"prevDayClosePrice\":135.39,\"highPrice52\":145.09,\"lowPrice52\":53.1525,\"averageVol3Months\":103518380,\"averageVol20Days\":106044826,\"outstandingShares\":16788100000,\"eps\":3.71,\"pe\":36.66038,\"dividend\":0.205,\"yield\":0.6029,\"exDate\":\"2021-02-05T00:00:00.000000-05:00\",\"marketCap\":2283348937000,\"tradeUnit\":1,\"optionType\":null,\"optionDurationType\":null,\"optionRoot\":\"\",\"optionContractDeliverables\":{\"underlyings\":[],\"cashInLieu\":0},\"optionExerciseType\":null,\"listingExchange\":\"NASDAQ\",\"description\":\"APPLE INC\",\"securityType\":\"Stock\",\"optionExpiryDate\":null,\"dividendDate\":\"2021-02-11T00:00:00.000000-05:00\",\"optionStrikePrice\":null,\"isTradable\":true,\"isQuotable\":true,\"hasOptions\":true,\"currency\":\"USD\",\"minTicks\":[{\"pivot\":0,\"minTick\":0.0001},{\"pivot\":1,\"minTick\":0.01}],\"industrySector\":\"Technology\",\"industryGroup\":\"Undefined\",\"industrySubgroup\":\"Undefined\"}]}";
        /*
            {
                "symbols": [
                    {
                        "symbol": "AAPL",
                        "symbolId": 8049,
                        "prevDayClosePrice": 135.39,
                        "highPrice52": 145.09,
                        "lowPrice52": 53.1525,
                        "averageVol3Months": 103518380,
                        "averageVol20Days": 106044826,
                        "outstandingShares": 16788100000,
                        "eps": 3.71,
                        "pe": 36.66038,
                        "dividend": 0.205,
                        "yield": 0.6029,
                        "exDate": "2021-02-05T00:00:00.000000-05:00",
                        "marketCap": 2283348937000,
                        "tradeUnit": 1,
                        "optionType": null,
                        "optionDurationType": null,
                        "optionRoot": "",
                        "optionContractDeliverables": {
                            "underlyings": [],
                            "cashInLieu": 0
                        },
                        "optionExerciseType": null,
                        "listingExchange": "NASDAQ",
                        "description": "APPLE INC",
                        "securityType": "Stock",
                        "optionExpiryDate": null,
                        "dividendDate": "2021-02-11T00:00:00.000000-05:00",
                        "optionStrikePrice": null,
                        "isTradable": true,
                        "isQuotable": true,
                        "hasOptions": true,
                        "currency": "USD",
                        "minTicks": [
                            {
                                "pivot": 0,
                                "minTick": 0.0001
                            },
                            {
                                "pivot": 1,
                                "minTick": 0.01
                            }
                        ],
                        "industrySector": "Technology",
                        "industryGroup": "Undefined",
                        "industrySubgroup": "Undefined"
                    }
                ]
            }
        */

        SymbolDetailListResponse response = new ObjectMapper().readerFor(SymbolDetailListResponse.class).readValue(json);

        assertNotNull(response);
        assertNotNull(response.getSymbols());
        assertEquals(1, response.getSymbols().size());

        SymbolDetailResponse symbolDetail = response.getSymbols().get(0);
        assertEquals(135.39, symbolDetail.getPrevDayClosePrice());
    }
}
