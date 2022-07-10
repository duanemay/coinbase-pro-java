package com.mayhoo.coinbasepro.api.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.Value;
import lombok.experimental.SuperBuilder;
import lombok.extern.jackson.Jacksonized;

import java.math.BigDecimal;

@Value
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Jacksonized
@JsonIgnoreProperties(ignoreUnknown = true)
public class NewMarketOrderSingle extends NewOrderSingle {
    BigDecimal size; //optional: Desired amount in BTC

    @SuppressWarnings("java:S1144") // Used by Builder
    private NewMarketOrderSingle(final NewMarketOrderSingleBuilder<?, ?> b) {
        // need to set type to market
        super(b.type("market"));
        this.size = b.size;
    }
}