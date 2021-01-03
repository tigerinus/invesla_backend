package com.tensorsmart.invesla.questrade.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;

@Getter
public class Token {
    @JsonProperty("access_token")
    String AccessToken;

    @JsonProperty("token_type")
    String TokenType;

    @JsonProperty("expires_in")
    int ExpiresIn;

    @JsonProperty("refresh_token")
    String RefreshToken;

    @JsonProperty("api_server")
    String ApiServer;
}
