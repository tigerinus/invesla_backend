package com.tensorsmart.invesla.questrade;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.tensorsmart.invesla.questrade.repository.entity.Token;
import com.tensorsmart.invesla.questrade.service.TokenService;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ServiceTest {
    
    @Autowired
    TokenService _service;

    @Test
    public void GetTokenTest() {
        Token token = _service.getToken();

        assertNotNull(token);
        assertNotNull(token.getAccessToken());
        assertNotNull(token.getRefreshToken());
        assertTrue(token.getExpiresBy() > 0);
    }
}
