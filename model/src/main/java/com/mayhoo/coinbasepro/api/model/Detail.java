package com.mayhoo.coinbasepro.api.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

@Value
@Builder
@Jacksonized
@JsonIgnoreProperties(ignoreUnknown = true)
public class Detail {
    @JsonProperty("order_id")
    String orderId;
    @JsonProperty("trade_id")
    Integer tradeId;
    @JsonProperty("product_id")
    String productId;
}