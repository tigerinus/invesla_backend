package com.tensorsmart.invesla.questrade.repository.entity;

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
    private String accessToken;

    private String tokenType;

    private long expiresBy = 0;

    private String refreshToken;

    private String apiServer;
}
