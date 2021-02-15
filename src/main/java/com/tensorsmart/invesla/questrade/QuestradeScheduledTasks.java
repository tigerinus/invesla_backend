package com.tensorsmart.invesla.questrade;

import com.tensorsmart.invesla.questrade.service.MarketService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class QuestradeScheduledTasks {
    
    @Autowired
    private MarketService _marketService;

    @EventListener(ApplicationReadyEvent.class)
    @Scheduled(zone = "EST", cron = "0 0 6 * * MON-FRI")
    public void refreshMarkets() {
        _marketService.updateMarkets();
    }
}
