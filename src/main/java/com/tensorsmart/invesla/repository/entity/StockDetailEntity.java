package com.tensorsmart.invesla.repository.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import com.tensorsmart.invesla.repository.entity.StockDetailEntity.StockDetailId;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "invesla_stock_detail")
@IdClass(StockDetailId.class)
@Getter
@Setter
public class StockDetailEntity {
    @Id
    @Column(name = "date_stamp")
    private String dateStamp;

    @Id    
    @Column(name = "symbol_id")
    private String symbolId;

    @Column(name = "prev_day_close_price")
    private double prevDayClosePrice;

    @Column(name = "high_price_52")
    private double highPrice52;

    @Column(name = "low_price_52")
    private double lowPrice52;

    @Column(name = "average_vol3_months")
    private long averageVol3Months;

    @Column(name = "average_vol20_days")
    private long averageVol20Days;

    @Column(name = "outstanding_shares")
    private long outstandingShares;

    @Column(name = "eps")
    private double eps;

    @Column(name = "pe")
    private double pe;

    @Column(name = "dividend")
    private double dividend;

    @Column(name = "yield")
    private double yield;

    @Column(name = "market_cap")
    private double marketCap;

    @Column(name = "trade_unit")
    private long tradeUnit;

    @Column(name = "dividend_date")
    private Date dividendDate;

    public class StockDetailId {
        private String _dateStamp;
        private String _symbolId;

        public StockDetailId(String dateStamp, String symbolId) {
            _dateStamp = dateStamp;
            _symbolId = symbolId;
        }

        @Override
        public int hashCode() {
            return (_dateStamp + _symbolId).hashCode();
        }
    }
}
