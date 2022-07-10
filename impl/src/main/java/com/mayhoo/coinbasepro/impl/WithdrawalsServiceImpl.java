package com.mayhoo.coinbasepro.impl;

import com.mayhoo.coinbasepro.api.exchange.CoinbaseProExchange;
import com.mayhoo.coinbasepro.api.model.CoinbasePaymentRequest;
import com.mayhoo.coinbasepro.api.model.CryptoPaymentRequest;
import com.mayhoo.coinbasepro.api.model.PaymentRequest;
import com.mayhoo.coinbasepro.api.model.PaymentResponse;
import com.mayhoo.coinbasepro.api.service.WithdrawalsService;
import lombok.RequiredArgsConstructor;
import lombok.Value;

import java.math.BigDecimal;

@Value
@RequiredArgsConstructor
public class WithdrawalsServiceImpl implements WithdrawalsService {
    private static final String WITHDRAWALS_ENDPOINT = "/withdrawals";
    private static final String PAYMENT_METHOD = "/payment-method";
    private static final String COINBASE = "/coinbase-account";
    private static final String CRYPTO = "/crypto";

    CoinbaseProExchange exchange;

    @Override
    public PaymentResponse makeWithdrawalToPaymentMethod(BigDecimal amount, String currency, String paymentMethodId) {
        PaymentRequest withdrawalRequest = PaymentRequest.builder()
                .amount(amount)
                .currency(currency)
                .paymentMethodId(paymentMethodId)
                .build();

        return exchange.post(WITHDRAWALS_ENDPOINT + PAYMENT_METHOD,
                PaymentResponse.class,
                withdrawalRequest);
    }

    @Override
    public PaymentResponse makeWithdrawalToCoinbase(BigDecimal amount, String currency, String coinbaseAccountId) {
        CoinbasePaymentRequest withdrawalRequest = new CoinbasePaymentRequest(amount, currency, coinbaseAccountId);
        return exchange.post(WITHDRAWALS_ENDPOINT + COINBASE,
                PaymentResponse.class,
                withdrawalRequest);
    }

    @Override
    public PaymentResponse makeWithdrawalToCryptoAccount(BigDecimal amount, String currency, String cryptoAddress) {
        CryptoPaymentRequest withdrawalRequest = new CryptoPaymentRequest(amount, currency, cryptoAddress);
        return exchange.post(WITHDRAWALS_ENDPOINT + CRYPTO,
                PaymentResponse.class,
                withdrawalRequest);
    }
}