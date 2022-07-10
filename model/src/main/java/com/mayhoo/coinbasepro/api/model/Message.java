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
public class Message {
    String type;
    Long sequence;
    @JsonProperty("order_id")
    String orderId;
    BigDecimal size;
    BigDecimal price;
    String side;
    @JsonProperty("remaining_size")
    BigDecimal remainingSize;
    String reason;
    @JsonProperty("maker_order_id")
    String makerOrderId;
    @JsonProperty("taker_order_id")
    String takerOrderId;
    String time;
}