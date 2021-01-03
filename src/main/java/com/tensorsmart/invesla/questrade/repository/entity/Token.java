package com.tensorsmart.invesla.questrade.repository.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Token {
    @Id
    String accessToken;

    String tokenType;

    long expiresBy = 0;

    String refreshToken;

    String apiServer;
}
