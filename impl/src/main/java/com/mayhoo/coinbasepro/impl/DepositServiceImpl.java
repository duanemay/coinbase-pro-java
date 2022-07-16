package com.mayhoo.coinbasepro.impl;

import com.mayhoo.coinbasepro.api.exchange.CoinbaseProExchange;
import com.mayhoo.coinbasepro.api.model.CoinbasePaymentRequest;
import com.mayhoo.coinbasepro.api.model.PaymentRequest;
import com.mayhoo.coinbasepro.api.model.PaymentResponse;
import com.mayhoo.coinbasepro.api.service.DepositService;
import lombok.RequiredArgsConstructor;
import lombok.Value;

import java.math.BigDecimal;

@Value
@RequiredArgsConstructor
public class DepositServiceImpl implements DepositService {
    private static final String DEPOSIT_ENDPOINT = "/deposits";
    private static final String PAYMENTS = "/payment-method";
    private static final String COINBASE_PAYMENT = "/coinbase-account";

    CoinbaseProExchange exchange;

    @Override
    public PaymentResponse depositViaPaymentMethod(BigDecimal amount, String currency, String paymentMethodId) {
        PaymentRequest paymentRequest = PaymentRequest.builder()
                .amount(amount)
                .currency(currency)
                .paymentMethodId(paymentMethodId)
                .build();

        return exchange.post(DEPOSIT_ENDPOINT + PAYMENTS,
                PaymentResponse.class,
                paymentRequest);
    }

    @Override
    public PaymentResponse coinbaseDeposit(BigDecimal amount, String currency, String coinbaseAccountId) {
        CoinbasePaymentRequest coinbasePaymentRequest = CoinbasePaymentRequest.builder()
                .amount(amount)
                .currency(currency)
                .coinbaseAccountId(coinbaseAccountId)
                .build();

        return exchange.post(DEPOSIT_ENDPOINT + COINBASE_PAYMENT,
                PaymentResponse.class,
                coinbasePaymentRequest);
    }
}