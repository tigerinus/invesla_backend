package com.tensorsmart.invesla.repository;

import com.tensorsmart.invesla.repository.entity.StockEntity;

import org.springframework.data.repository.CrudRepository;

public interface StockRepository extends CrudRepository<StockEntity, String> {
    
}
