package com.tensorsmart.invesla.questrade.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.List;

import com.tensorsmart.invesla.ScheduledTasks;
import com.tensorsmart.invesla.questrade.connector.response.QuoteResponse;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
class QuoteServiceTest {
    
    @Autowired
    private QuoteService _service;

    @MockBean
    private ScheduledTasks _dummyScheduledTasks;

    @Test
    void getQuoteListTest() {
        List<String> symbolIdList = Arrays.asList("7956432");

        List<QuoteResponse> responseList = _service.getQuotes(symbolIdList);

        assertNotNull(responseList);
        assertTrue(responseList.size() > 0);
        
        QuoteResponse quote = responseList.get(0);

        assertEquals("ARKK", quote.getSymbol());
    }
}
