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
public class Limit {
    String type; // bank, fiat_account
    String name; // Bank Account, Coinbase Account
    AccountLimit[] buy;
    @JsonProperty("instant_buy")
    AccountLimit[] instantBuy;
    AccountLimit[] sell;
    AccountLimit[] deposit;
}