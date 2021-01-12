package com.tensorsmart.invesla.questrade.repository;

import com.tensorsmart.invesla.questrade.repository.entity.TokenEntity;

import org.springframework.data.repository.CrudRepository;

public interface TokenRepository extends CrudRepository<TokenEntity, String> {
    
}
