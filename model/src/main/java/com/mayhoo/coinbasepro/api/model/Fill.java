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
public class Fill {
    @JsonProperty("trade_id")
    Integer tradeId;
    @JsonProperty("product_id")
    String productId;
    BigDecimal size;
    @JsonProperty("order_id")
    String orderId;
    @JsonProperty("created_at")
    String createdAt;
    String liquidity;
    BigDecimal fee;
    Boolean settled;
    String side;
}