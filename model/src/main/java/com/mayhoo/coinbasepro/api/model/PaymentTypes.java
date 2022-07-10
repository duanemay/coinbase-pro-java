package com.mayhoo.coinbasepro.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

@Value
@Builder
@Jacksonized
public class PaymentTypes {
    String id; // UUID
    String type; // ach_bank_account
    String name; // Bank of America - eBan... ********7134
    String currency; // USD
    @JsonProperty("primary_buy")
    Boolean primaryBuy;
    @JsonProperty("primary_sell")
    Boolean primarySell;
    @JsonProperty("allow_buy")
    Boolean allowBuy;
    @JsonProperty("allow_sell")
    Boolean allowSell;
    @JsonProperty("allow_deposit")
    Boolean allowDeposit;
    @JsonProperty("allow_withdraw")
    Boolean allowWithdraw;
    Limit limits;
}