package com.mayhoo.coinbasepro.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
public abstract class NewOrderSingle {
    @JsonProperty("product_id")
    String clientOid; //optional
    String type; //default is limit, other types are market and stop
    String side;
    @JsonProperty("product_id")
    String productId;
    String stp; //optional: values are dc, co , cn , cb
    String funds;
}