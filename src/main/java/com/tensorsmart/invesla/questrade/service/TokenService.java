package com.tensorsmart.invesla.questrade.service;

import java.util.Date;
import java.util.Iterator;

import com.tensorsmart.invesla.questrade.connector.TokenConnector;
import com.tensorsmart.invesla.questrade.connector.response.TokenResponse;
import com.tensorsmart.invesla.questrade.repository.TokenRepository;
import com.tensorsmart.invesla.questrade.repository.entity.TokenEntity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class TokenService {

    volatile TokenEntity _token;
    
    @Autowired
    private TokenConnector _connector;

    @Autowired
    private TokenRepository _tokenRepository;

    public TokenEntity getToken() {
        refreshTokenFromRepositoryIfNeccessary();
        Assert.notNull(_token, "token should not be null at this point.");
        return _token;
    }

    private void refreshTokenFromRepositoryIfNeccessary() {
        if (null != _token && new Date().getTime() < _token.getExpiresBy()) {
            return;
        }

        refreshTokenRepositoryFromAPIIfNeccessary();
        Assert.state(_tokenRepository.count() == 1, "There should always be only 1 token at this point.");
        Iterator<TokenEntity> i = _tokenRepository.findAll().iterator();
        if (i.hasNext()) {
            _token = i.next();    
        }
    }

    private void refreshTokenRepositoryFromAPIIfNeccessary() {
        Assert.state(_tokenRepository.count() <= 1, "There should always be at most 1 token in the database.");
        Iterator<TokenEntity> i = _tokenRepository.findAll().iterator();

        String refreshToken = null;

        if (i.hasNext()) {
            TokenEntity token = i.next();
            if (new Date().getTime() < token.getExpiresBy()) {
                return;    
            }

            refreshToken = token.getRefreshToken();
        }

        // obtain from API
        TokenResponse response = _connector.getToken(refreshToken);

        if (null == response) {
            log.error("unable to obtain new token from API");
            return;
        }

        _token = new TokenEntity();
        _token.setAccessToken(response.getAccessToken());
        _token.setTokenType(response.getTokenType());
        _token.setExpiresBy(new Date().getTime() + response.getExpiresIn() * 1000);
        _token.setRefreshToken(response.getRefreshToken());
        _token.setApiServer(response.getApiServer());

        _tokenRepository.deleteAll();
        _tokenRepository.save(_token);
    }

}
