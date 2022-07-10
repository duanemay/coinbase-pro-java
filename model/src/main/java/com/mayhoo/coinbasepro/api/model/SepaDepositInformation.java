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
public class SepaDepositInformation {
    String iban;
    String swift;
    @JsonProperty("bank_name")
    String bankName;
    @JsonProperty("bank_address")
    String bankAddress;
    @JsonProperty("bank_country_name")
    String bankCountryName;
    @JsonProperty("account_name")
    String accountName;
    @JsonProperty("account_address")
    String accountAddress;
    String reference;
}