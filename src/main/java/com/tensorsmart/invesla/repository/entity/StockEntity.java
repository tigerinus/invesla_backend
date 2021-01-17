package com.tensorsmart.invesla.repository.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "invesla_stocks")
@Getter
@Setter
public class StockEntity {
    @Id
    @Column(name = "symbol_id")
    private String symbolId;

    @Column(name = "symbol")
    private String symbol;

    @Column(name = "description")
    private String description;

    @Column(name = "listing_exchange")
    private String listingExchange;

    @Column(name = "currency")
    private String currency;
}
