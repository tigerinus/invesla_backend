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
    String accessToken;

    String tokenType;

    long expiresBy = 0;

    String refreshToken;

    String apiServer;
}
