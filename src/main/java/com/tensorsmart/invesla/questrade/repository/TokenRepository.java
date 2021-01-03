package com.tensorsmart.invesla.questrade.repository;

import com.tensorsmart.invesla.questrade.repository.entity.Token;

import org.springframework.data.repository.CrudRepository;

public interface TokenRepository extends CrudRepository<Token, String> {
    
}
