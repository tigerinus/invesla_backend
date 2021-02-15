package com.tensorsmart.invesla.questrade.connector;

import com.tensorsmart.invesla.questrade.connector.response.TokenResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class TokenConnector {
    
    private String _url;

    @Autowired
    @Qualifier("restTemplateWithoutHeader")
    private RestTemplate _restTemplate;

    public TokenConnector(@Value("${qt.login.url}") String url) {
        _url = url;
    }

    public TokenResponse getToken(String refreshToken) {
        if (refreshToken == null || refreshToken.isEmpty()) return null;

        String fullUrl = _url + refreshToken;

        ResponseEntity<TokenResponse> response;
        try {
            response = _restTemplate.getForEntity(fullUrl, TokenResponse.class);
        } catch (RestClientException e) {
            log.error(e.getMessage(), e);
            return null;
        }

        if (!response.getStatusCode().is2xxSuccessful()) {
            log.error("Refresh token failed. Status code: {}", response.getStatusCode());
            return null;
        }

        return response.getBody();
    }

}
