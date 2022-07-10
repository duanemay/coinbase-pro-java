package com.mayhoo.coinbasepro.api.service;

import com.mayhoo.coinbasepro.api.model.PaymentResponse;

import java.math.BigDecimal;

public interface WithdrawalsService {
    PaymentResponse makeWithdrawalToPaymentMethod(BigDecimal amount, String currency, String paymentMethodId);

    PaymentResponse makeWithdrawalToCoinbase(BigDecimal amount, String currency, String coinbaseAccountId);

    PaymentResponse makeWithdrawalToCryptoAccount(BigDecimal amount, String currency, String cryptoAddress);
}