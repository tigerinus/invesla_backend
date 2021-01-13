package com.tensorsmart.invesla.questrade;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.tensorsmart.invesla.questrade.connector.response.SymbolResponse;
import com.tensorsmart.invesla.questrade.service.SymbolService;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class SymbolServiceTest {
    @Autowired
    private SymbolService _service;

    @Test
    public void getSymbolTest() {
        SymbolResponse response = _service.getSymbol("AI");

        assertNotNull(response);
        assertEquals(response.getSymbol(), "AI");
    }
}
