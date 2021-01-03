package com.tensorsmart.invesla.questrade.connector.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;

@Getter
public class TokenResponse {
    @JsonProperty("access_token")
    String accessToken;

    @JsonProperty("token_type")
    String tokenType;

    @JsonProperty("expires_in")
    int expiresIn;

    @JsonProperty("refresh_token")
    String refreshToken;

    @JsonProperty("api_server")
    String apiServer;
}
