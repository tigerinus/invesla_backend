package com.tensorsmart.invesla.repository.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "invesla_quotes")
@Getter
@Setter
public class QuoteEntity {
    @Id
    @Column(name = "symbol_id")
    private String symbolId;

    @Column(name = "bid_price")
    private float bidPrice;

    @Column(name = "bid_size")
    private long bidSize;

    @Column(name = "ask_price")
    private float askPrice;

    @Column(name = "ask_size")
    private long askSize;

    @Column(name = "last_trade_price_tr_hrs")
    private float lastTradePriceTrHrs;

    @Column(name = "last_trade_price")
    private float lastTradePrice;

    @Column(name = "last_trade_size")
    private long lastTradeSize;

    @Column(name = "last_trade_tick")
    private String lastTradeTick;

    @Column(name = "last_trade_time")
    private Date lastTradeTime;

    @Column(name = "volume")
    private long volume;

    @Column(name = "open_price")
    private float openPrice;

    @Column(name = "high_price")
    private float highPrice;

    @Column(name = "low_price")
    private float lowPrice;

    @Column(name = "delay")
    private long delay;

    @Column(name = "is_halted")
    private boolean isHalted;

    @Column(name = "high52w")
    private float high52w;

    @Column(name = "low52w")
    private float low52w;

    @Column(name = "volume_weighted_average_price")
    private float volumeWeightedAveragePrice;
}
