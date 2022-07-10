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
public class Hold {
    String id;
    @JsonProperty("account_id")
    String accountId;
    @JsonProperty("created_at")
    String createdAt;
    @JsonProperty("update_at")
    String updateAt;
    BigDecimal amount;
    String type;
    String ref;
}