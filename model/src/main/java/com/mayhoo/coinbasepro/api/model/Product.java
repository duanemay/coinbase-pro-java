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
public class Product {
    String id;
    @JsonProperty("base_currency")
    String baseCurrency;
    @JsonProperty("quote_currency")
    String quoteCurrency;
    @JsonProperty("base_min_size")
    Double baseMinSize;
    @JsonProperty("base_max_size")
    Double baseMaxSize;
    @JsonProperty("quote_increment")
    Double quoteIncrement;
}