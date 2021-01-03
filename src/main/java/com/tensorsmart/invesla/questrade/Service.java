package com.tensorsmart.invesla.questrade;

import java.util.Date;
import java.util.Iterator;

import com.tensorsmart.invesla.questrade.dto.TokenDTO;
import com.tensorsmart.invesla.questrade.entity.Token;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

@Component
public class Service {
    final Logger LOG = LoggerFactory.getLogger(this.getClass());

    Token _token;
    
    @Autowired
    Connector _connector;

    @Autowired
    TokenRepository _tokenRepository;

    public Token getToken() {
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
        Iterator<Token> i = _tokenRepository.findAll().iterator();
        if (i.hasNext()) {
            _token = i.next();    
        }
    }

    private void refreshTokenRepositoryFromAPIIfNeccessary() {
        Assert.state(_tokenRepository.count() <= 1, "There should always be at most 1 token in the database.");
        Iterator<Token> i = _tokenRepository.findAll().iterator();

        String refreshToken = null;

        if (i.hasNext()) {
            Token token = i.next();
            if (new Date().getTime() < token.getExpiresBy()) {
                return;    
            }

            refreshToken = token.getRefreshToken();
        }

        // obtain from API
        TokenDTO dto = _connector.getToken(refreshToken);

        if (null == dto) {
            LOG.error("unable to obtain new token from API");
            return;
        }

        _token = new Token();
        _token.setAccessToken(dto.getAccessToken());
        _token.setRefreshToken(dto.getRefreshToken());
        _token.setExpiresBy(new Date().getTime() + dto.getExpiresIn() * 1000);

        _tokenRepository.deleteAll();
        _tokenRepository.save(_token);
    }

}
