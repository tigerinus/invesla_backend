package com.tensorsmart.invesla.questrade.connector;

import com.tensorsmart.invesla.questrade.connector.response.TokenResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Component
public class TokenConnector {
    
    final static Logger LOG = LoggerFactory.getLogger(TokenConnector.class);

    String _url;
    String _refreshToken;

    @Autowired
    @Qualifier("restTemplateWithoutHeader")
    RestTemplate restTemplate;

    public TokenConnector(@Value("${qt.login.url}") String url, @Value("${qt.token.key}") String manualRefreshToken) {
        _url = url;
        _refreshToken = manualRefreshToken;
    }

    public TokenResponse getToken(String refreshToken) {
        if (null != refreshToken && !refreshToken.trim().isEmpty()) {
            _refreshToken = refreshToken;
        }

        ResponseEntity<TokenResponse> response = null;
        try {
            LOG.info("Calling {}...", _url);
            String fullUrl = _url + _refreshToken;
            response = restTemplate.getForEntity(fullUrl, TokenResponse.class);
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
