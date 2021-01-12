package com.tensorsmart.invesla.repository.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "invesla_stocks")
public class StockEntity {
    @Id
    String symbolId;

    String symbol;

    String description;

    String listingExchange;

    String currency;
}
