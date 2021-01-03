package com.tensorsmart.invesla.questrade;

import com.tensorsmart.invesla.questrade.dto.TokenDTO;

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
    String _refreshToken;

    @Autowired
    RestTemplate restTemplate;

    public Connector(@Value("${qt.login.url}") String url, @Value("${qt.token.key}") String manualRefreshToken) {
        _url = url;
        _refreshToken = manualRefreshToken;
    }

    public TokenDTO getToken(String refreshToken) {
        if (null != refreshToken && !refreshToken.trim().isEmpty()) {
            _refreshToken = refreshToken;
        }

        ResponseEntity<TokenDTO> response = null;
        try {
            LOG.info("Calling {}...", _url);
            //String fullUrl = "https://5ff12a5fdb1158001748ae03.mockapi.io/oauth2/token";
            String fullUrl = _url + _refreshToken;
            response = restTemplate.getForEntity(fullUrl, TokenDTO.class);
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
