package com.tensorsmart.invesla.repository.entity;

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
    String symbolId;

    String symbol;

    String description;

    String listingExchange;

    String currency;
}
