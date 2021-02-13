package com.tensorsmart.invesla.service;

import java.util.List;
import java.util.stream.Collectors;

import com.tensorsmart.invesla.questrade.connector.response.QuoteResponse;
import com.tensorsmart.invesla.questrade.service.QuoteService;
import com.tensorsmart.invesla.repository.QuoteRepository;
import com.tensorsmart.invesla.repository.entity.QuoteEntity;
import com.tensorsmart.invesla.service.factory.QuoteEntityFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class QuoteUpdateService {

    @Autowired
    QuoteService _quoteService;

    @Autowired
    QuoteRepository _repository;

    public void updateQuotes(List<String> symbolIdList) {
        if (symbolIdList.isEmpty()) return;

        List<QuoteResponse> quoteResponseList = _quoteService.getQuotes(symbolIdList);

        if (quoteResponseList.size() == 0) return;

        List<QuoteEntity> quoteEntityList = quoteResponseList.stream().map(response -> QuoteEntityFactory.get(response))
                .collect(Collectors.toList());

        _repository.saveAll(quoteEntityList);
    }
}
