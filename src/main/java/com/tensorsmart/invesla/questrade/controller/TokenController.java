package com.tensorsmart.invesla.questrade.controller;

import java.text.DateFormat;
import java.util.Date;
import java.util.TimeZone;

import javax.validation.Valid;

import com.tensorsmart.invesla.questrade.repository.entity.TokenEntity;
import com.tensorsmart.invesla.questrade.service.TokenService;

import org.openapitools.api.TokenApi;
import org.openapitools.model.TokenRequest;
import org.openapitools.model.TokenResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TokenController implements TokenApi {

    @Autowired
    private TokenService _service;

    @Override
    public ResponseEntity<TokenResponse> getToken() {
        TokenEntity token = _service.getToken();

        Date expiresBy = new Date(token.getExpiresBy());
        DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.FULL, DateFormat.FULL);
        dateFormat.setTimeZone(TimeZone.getTimeZone("EST"));

        TokenResponse response = new TokenResponse();
        response.setExpiresBy(dateFormat.format(expiresBy));

        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<Void> refreshToken(@Valid TokenRequest tokenRequest) {
        if (tokenRequest == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        String refreshToken = tokenRequest.getRefreshToken();

        if (refreshToken == null || refreshToken.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        try {
            _service.refreshTokenRepositoryFromAPI(refreshToken);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_GATEWAY).build();
        }

        return ResponseEntity.status(HttpStatus.OK).build();
    }
    
}
