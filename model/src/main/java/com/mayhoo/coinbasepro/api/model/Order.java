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
public class Order {
    String id;
    String price;
    String size;
    @JsonProperty("product_id")
    String productId;
    String side;
    String stp;
    String type;
    @JsonProperty("time_in_force")
    String timeInForce;
    @JsonProperty("post_only")
    String postOnly;
    @JsonProperty("created_at")
    String createdAt;
    @JsonProperty("fill_fees")
    String fillFees;
    @JsonProperty("filled_size")
    String filledSize;
    @JsonProperty("executed_value")
    String executedValue;
    String status;
    Boolean settled;
}