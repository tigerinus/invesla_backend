package com.tensorsmart.invesla.questrade.connector.response;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;

@Getter
public class SymbolDetailResponse extends SymbolResponse {
    private double prevDayClosePrice;
    private double highPrice52;
    private double lowPrice52;
    private long averageVol3Months;
    private long averageVol20Days;
    private long outstandingShares;
    private double eps;
    private double pe;
    private double dividend;
    private double yield;
    private Date exDate;
    private double marketCap;
    private long tradeUnit;
    private OptionType optionType;
    private OptionDurationType optionDurationType;
    private String optionRoot;
    private OptionContractDeliverables optionContractDeliverables;
    private OptionExerciseType optionExerciseType;
    private Date optionExpiryDate;
    private Date dividendDate;
    private double optionStrikePrice;
    private Boolean hasOptions;
    private List<MinTick> minTicks;
    private String industrySector;
    private String industryGroup;
    private String industrySubgroup;

    public enum OptionType {
        @JsonProperty("Call")
        CALL,

        @JsonProperty("Put")
        PUT
    }

    public enum OptionDurationType {
        LEAP,

        @JsonProperty("Weekly")
        WEEKLY,

        @JsonProperty("Monthly")
        MONTHLY,

        @JsonProperty("Quarterly")
        QUARTERLY
    }

    public enum OptionExerciseType {
        @JsonProperty("American")
        AMERICAN,

        @JsonProperty("European")
        EUROPEAN
    }

    @Getter
    public static class OptionContractDeliverables {
        List<UnderlyingMultiplierPair> underlyings;
        double cashInLieu;

        public class UnderlyingMultiplierPair {
            long multiplier;
            String underlyingSymbol;
            String underlyingSymbolId;
        }
    }

    @Getter
    public static class MinTick {
        double pivot;
        double minTick;
    }
}
