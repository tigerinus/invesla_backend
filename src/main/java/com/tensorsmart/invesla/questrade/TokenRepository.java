package com.tensorsmart.invesla.questrade;

import com.tensorsmart.invesla.questrade.entity.Token;

import org.springframework.data.repository.CrudRepository;

public interface TokenRepository extends CrudRepository<Token, String> {
    
}
