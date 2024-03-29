package com.tensorsmart.invesla.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import com.tensorsmart.invesla.questrade.service.SymbolService;
import com.tensorsmart.invesla.repository.StockRepository;
import com.tensorsmart.invesla.repository.entity.StockEntity;
import com.tensorsmart.invesla.service.factory.StockEntityFactory;
import com.tensorsmart.invesla.service.wrapper.StockWrapper;

import org.openapitools.model.Stock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class StockManagementService {

    @Autowired
    private StockRepository _repository;

    @Autowired
    private SymbolService _symbolService;

    public List<Stock> getStocks() {

        List<Stock> result = new ArrayList<>();
        for (StockEntity stockEntity : _repository.findAll()) {
            result.add(new StockWrapper(stockEntity));
        }

        return result;
    }

    public Stock getStockById(String id) {
        Optional<StockEntity> stockEntity = _repository.findById(id);

        if (stockEntity.isPresent())
            return new StockWrapper(stockEntity.get());

        return null;
    }

    public void addStocks(List<String> symbols) {

        if (symbols.isEmpty()) {
            return;
        }

        List<StockEntity> existingStocks = _repository.findAllBySymbolIn(symbols);

        List<String> existingSymbols = existingStocks.stream().map(StockEntity::getSymbol).collect(Collectors.toList());

        symbols.removeAll(existingSymbols);

        if (symbols.isEmpty()) {
            return;
        }

        List<StockEntity> newStocks = symbols.stream().map(symbol -> _symbolService.getSymbolByName(symbol))
                .filter(Objects::nonNull).map(StockEntityFactory::get).collect(Collectors.toList());

        _repository.saveAll(newStocks);
    }

    public void deleteStock(String symbol) {
        _repository.deleteBySymbol(symbol);
    }
}
