package com.mayhoo.coinbasepro.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import lombok.Value;

import java.math.BigDecimal;

@Value
@Builder
@RequiredArgsConstructor
public class PaymentRequest {
    BigDecimal amount;
    String currency;
    @JsonProperty("payment_method_id")
    String paymentMethodId;
}