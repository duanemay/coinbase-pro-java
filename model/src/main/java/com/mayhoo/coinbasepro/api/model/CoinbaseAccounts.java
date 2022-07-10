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
public class CoinbaseAccounts {
    String id; //UUID
    String name;
    BigDecimal balance;
    String currency;
    String type;
    Boolean primary;
    Boolean active;
    @JsonProperty("wire_deposit_enabled")
    DepositInformation wireDepositInformation;
    @JsonProperty("sepa_deposit_information")
    SepaDepositInformation sepaDepositInformation;
}