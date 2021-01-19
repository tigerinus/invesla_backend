package com.tensorsmart.invesla.repository;

import com.tensorsmart.invesla.repository.entity.QuoteEntity;

import org.springframework.data.repository.CrudRepository;

public interface QuoteRepository extends CrudRepository<QuoteEntity, String> {
    
}
