package com.mayhoo.coinbasepro.api.service;

import com.mayhoo.coinbasepro.api.model.PaymentResponse;

import java.math.BigDecimal;

public interface DepositService {
    PaymentResponse depositViaPaymentMethod(BigDecimal amount, String currency, String paymentMethodId);

    PaymentResponse coinbaseDeposit(BigDecimal amount, String currency, String coinbaseAccountId);
}