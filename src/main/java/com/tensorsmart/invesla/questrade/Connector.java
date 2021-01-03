package com.tensorsmart.invesla.questrade;

import com.tensorsmart.invesla.questrade.model.Token;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Component
public class Connector {
    
    final Logger LOG = LoggerFactory.getLogger(this.getClass());

    String _url;
    String _key;

    @Autowired
    RestTemplate restTemplate;

    public Connector(@Value("${qt.login.url}") String url, @Value("${qt.token.key}") String key) {
        _url = url;
        _key = key;
    }

    public Token GetToken() {
        ResponseEntity<Token> response = null;
        try {
            LOG.info("Calling {}...", _url);
            //String fullUrl = "https://5ff12a5fdb1158001748ae03.mockapi.io/oauth2/token";
            String fullUrl = _url + _key;
            response = restTemplate.getForEntity(fullUrl, Token.class);
        } catch (HttpClientErrorException e) {
            LOG.error(e.getMessage(), e);
            return null;
        }

        if (!response.getStatusCode().is2xxSuccessful()) {
            LOG.error("Refresh token failed. Status code: {}", response.getStatusCode());
            return null;
        }

        return response.getBody();
    }

}
