package com.tensorsmart.invesla.questrade;

import static org.junit.jupiter.api.Assertions.assertTrue;

import com.tensorsmart.invesla.questrade.model.Token;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ConnectorTest {
    
    @Autowired
    Connector _connector;

    @Test
    public void GetTokenTest() {
        Token token = _connector.GetToken();

        assertTrue(token.getExpiresIn() > 0);
    }
}
