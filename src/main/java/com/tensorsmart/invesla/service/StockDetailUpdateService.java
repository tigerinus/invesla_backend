package com.tensorsmart.invesla.service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

import com.tensorsmart.invesla.questrade.connector.response.SymbolDetailResponse;
import com.tensorsmart.invesla.questrade.service.SymbolService;
import com.tensorsmart.invesla.repository.StockDetailRepository;
import com.tensorsmart.invesla.repository.entity.StockDetailEntity;
import com.tensorsmart.invesla.service.factory.StockDetailEntityFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class StockDetailUpdateService {
    private static final String ZONE = "America/New_York";

    @Autowired
    private SymbolService _symbolService;

    @Autowired
    private StockDetailRepository _repository;

    public void updateStocks(List<String> symbolIdList) {
        if (symbolIdList.isEmpty())
            return;

        List<SymbolDetailResponse> symbolDetailResponses = _symbolService.getSymbolDetailByIds(symbolIdList);

        if (symbolDetailResponses == null || symbolDetailResponses.isEmpty())
            return;

        List<StockDetailEntity> stockDetailEntityList = symbolDetailResponses.stream()
                .map(StockDetailEntityFactory::get).map(StockDetailUpdateService::stamp).collect(Collectors.toList());

        _repository.saveAll(stockDetailEntityList);
    }

    private static StockDetailEntity stamp(StockDetailEntity stockDetail) {
        String dateStamp = getLastWorkDateTime().atZone(ZoneId.of(ZONE)).format(DateTimeFormatter.BASIC_ISO_DATE);

        stockDetail.setDateStamp(dateStamp);

        return stockDetail;
    }

    private static LocalDateTime getLastWorkDateTime() {
        LocalDateTime now = LocalDateTime.now();

        LocalDateTime lastWorkDateTime;

        switch (now.getDayOfWeek()) {
        case MONDAY:
            lastWorkDateTime = now.minusDays(3);
            break;
        case SUNDAY:
            lastWorkDateTime = now.minusDays(2);
            break;
        default:
            lastWorkDateTime = now.minusDays(1);
        }

        return lastWorkDateTime;
    }
}
