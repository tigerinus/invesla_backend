package com.tensorsmart.invesla.questrade.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.tensorsmart.invesla.ScheduledTasks;
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
    public void getSymbolTest() {
        SymbolResponse response = _service.getSymbol("AI");

        assertNotNull(response);
        assertEquals(response.getSymbol(), "AI");
    }
}
