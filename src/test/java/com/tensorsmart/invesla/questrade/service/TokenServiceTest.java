package com.tensorsmart.invesla.questrade.service;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.tensorsmart.invesla.ScheduledTasks;
import com.tensorsmart.invesla.questrade.repository.entity.TokenEntity;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
class TokenServiceTest {
    
    @Autowired
    private TokenService _service;

    @MockBean
    private ScheduledTasks _dummyScheduledTasks;

    @Test
    void getTokenTest() {
        TokenEntity token = _service.getToken();

        assertNotNull(token);
        assertNotNull(token.getAccessToken());
        assertNotNull(token.getRefreshToken());
        assertTrue(token.getExpiresBy() > 0);
    }
}
