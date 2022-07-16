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
public class AccountHistory {
    Integer id;
    @JsonProperty("created_at")
    String createdAt;
    BigDecimal amount;
    BigDecimal balance;
    String type;
    Detail detail;
}