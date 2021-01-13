package com.tensorsmart.invesla.questrade;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.tensorsmart.invesla.questrade.repository.entity.TokenEntity;
import com.tensorsmart.invesla.questrade.service.TokenService;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TokenServiceTest {
    
    @Autowired
    TokenService _service;

    @Test
    public void getTokenTest() {
        TokenEntity token = _service.getToken();

        assertNotNull(token);
        assertNotNull(token.getAccessToken());
        assertNotNull(token.getRefreshToken());
        assertTrue(token.getExpiresBy() > 0);
    }
}
