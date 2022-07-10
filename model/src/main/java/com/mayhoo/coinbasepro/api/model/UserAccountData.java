package com.mayhoo.coinbasepro.api.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

import java.math.BigDecimal;

@Value
@Builder
@Jacksonized
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserAccountData {
    @JsonProperty("product_id")
    String productId;
    @JsonProperty("exchange_volume")
    BigDecimal exchangeVolume;
    BigDecimal volume;
    @JsonProperty("recorded_at")
    String recordedAt;
}