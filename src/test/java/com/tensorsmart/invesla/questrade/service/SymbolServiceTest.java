package com.tensorsmart.invesla.questrade.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import com.tensorsmart.invesla.ScheduledTasks;
import com.tensorsmart.invesla.questrade.connector.response.SymbolDetailResponse;
import com.tensorsmart.invesla.questrade.connector.response.SymbolResponse;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
public class SymbolServiceTest {

    @Autowired
    private SymbolService _service;

    @MockBean
    private ScheduledTasks _dummyScheduledTasks;

    @Test
    public void getSymbolByNameTest() {
        SymbolResponse response = _service.getSymbolByName("AI");

        assertNotNull(response);
        assertEquals(response.getSymbol(), "AI");
    }

    @Test
    public void getSymbolByIdTest() {
        List<String> ids = new ArrayList<String>();
        ids.add("8049");

        List<SymbolDetailResponse> symbolList = _service.getSymbolDetailByIds(ids);
        
        assertNotNull(symbolList);
        assertEquals(ids.size(), symbolList.size());

        SymbolDetailResponse symbolDetail = symbolList.get(0);

        assertEquals("AAPL", symbolDetail.getSymbol());
    }
}
