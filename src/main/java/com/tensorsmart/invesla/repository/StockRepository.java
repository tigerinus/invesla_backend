package com.tensorsmart.invesla.repository;

import java.util.List;

import com.tensorsmart.invesla.repository.entity.StockEntity;

import org.springframework.data.repository.CrudRepository;

public interface StockRepository extends CrudRepository<StockEntity, String> {
    List<StockEntity> findAllBySymbolIn(List<String> symbols);

    void deleteBySymbol(String symbol);
}
