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
public class Account {
    String id;
    String currency;
    BigDecimal balance;
    BigDecimal available;
    BigDecimal hold;
    @JsonProperty("profile_id")
    String profileId;
}