package com.tensorsmart.invesla.questrade.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Token {
    @Id
    String AccessToken;

    long ExpiresBy = 0;

    String RefreshToken;
}
