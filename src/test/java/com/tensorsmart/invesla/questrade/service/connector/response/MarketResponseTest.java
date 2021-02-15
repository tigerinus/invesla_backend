package com.tensorsmart.invesla.questrade.service.connector.response;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tensorsmart.invesla.questrade.connector.response.MarketResponse;

import org.junit.jupiter.api.Test;

public class MarketResponseTest {
    @Test
    void deserializationTest() throws JsonMappingException, JsonProcessingException {
        String json = "{\"name\":\"TSX\",\"tradingVenues\":[\"TSX\",\"ALPH\",\"CXC\",\"OMGA\",\"PURE\",\"CNSX\",\"ATS\",\"ICX\",\"LIQ\",\"LYX\",\"CXD\",\"NEOL\",\"NEO\",\"MATCH\",\"CX2\"],\"defaultTradingVenue\":\"AUTO\",\"primaryOrderRoutes\":[\"AUTO\"],\"secondaryOrderRoutes\":[\"TSX\",\"AUTO\",\"CX2\",\"AQN\",\"AQL\",\"CXC\",\"OME\",\"OM2\",\"CXD\"],\"level1Feeds\":[\"TSX\"],\"level2Feeds\":[\"ALPH\",\"CXC\",\"NEO\",\"NEOL\",\"OMGA\",\"PURE\",\"TSX\"],\"extendedStartTime\":\"2021-02-14T07:00:00.000000-05:00\",\"startTime\":\"2021-02-14T09:30:00.000000-05:00\",\"endTime\":\"2021-02-14T16:00:00.000000-05:00\",\"extendedEndTime\":\"2021-02-14T20:00:00.000000-05:00\",\"snapQuotesLimit\":2147483647}";
        /*
            {
                "name": "TSX",
                "tradingVenues": [
                    "TSX",
                    "ALPH",
                    "CXC",
                    "OMGA",
                    "PURE",
                    "CNSX",
                    "ATS",
                    "ICX",
                    "LIQ",
                    "LYX",
                    "CXD",
                    "NEOL",
                    "NEO",
                    "MATCH",
                    "CX2"
                ],
                "defaultTradingVenue": "AUTO",
                "primaryOrderRoutes": [
                    "AUTO"
                ],
                "secondaryOrderRoutes": [
                    "TSX",
                    "AUTO",
                    "CX2",
                    "AQN",
                    "AQL",
                    "CXC",
                    "OME",
                    "OM2",
                    "CXD"
                ],
                "level1Feeds": [
                    "TSX"
                ],
                "level2Feeds": [
                    "ALPH",
                    "CXC",
                    "NEO",
                    "NEOL",
                    "OMGA",
                    "PURE",
                    "TSX"
                ],
                "extendedStartTime": "2021-02-14T07:00:00.000000-05:00",
                "startTime": "2021-02-14T09:30:00.000000-05:00",
                "endTime": "2021-02-14T16:00:00.000000-05:00",
                "extendedEndTime": "2021-02-14T20:00:00.000000-05:00",
                "snapQuotesLimit": 2147483647
            }
        */

        MarketResponse response = new ObjectMapper().readerFor(MarketResponse.class).readValue(json);

        assertNotNull(response);
        assertEquals("TSX", response.getName());
    }
}
