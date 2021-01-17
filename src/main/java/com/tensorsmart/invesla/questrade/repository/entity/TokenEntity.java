package com.tensorsmart.invesla.questrade.repository.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "questrade_token")
@Getter
@Setter
public class TokenEntity {
    @Id
    @Column(name = "access_token")
    private String accessToken;

    @Column(name = "token_type")
    private String tokenType;

    @Column(name = "expires_by")
    private long expiresBy = 0;

    @Column(name = "refresh_token")
    private String refreshToken;

    @Column(name = "api_server")
    private String apiServer;
}
