package com.tensorsmart.invesla.questrade;

import java.util.Date;

import com.tensorsmart.invesla.questrade.model.Token;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Service {
    final Logger LOG = LoggerFactory.getLogger(this.getClass());

    Token _token;
    long _expires_by;

    Connector _connector;

    public Service(Connector connector) {
        _connector = connector;
    }

    public void RefreshTokenIfExpired() {
        if (new Date().getTime() < _expires_by) {
            LOG.info("token has expired.");
            _token = _connector.GetToken();

            if (null != _token) {
                _expires_by = new Date().getTime() + _token.getExpiresIn() * 1000;
            }
        }
    }
}
