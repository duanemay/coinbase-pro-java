package com.mayhoo.coinbasepro.impl;

import com.mayhoo.coinbasepro.api.exchange.CoinbaseProExchange;
import com.mayhoo.coinbasepro.api.model.CoinbaseAccounts;
import com.mayhoo.coinbasepro.api.model.PaymentTypes;
import com.mayhoo.coinbasepro.api.service.PaymentService;
import lombok.RequiredArgsConstructor;
import lombok.Value;

@Value
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {
    private static final String PAYMENT_METHODS_ENDPOINT = "/payment-methods";
    private static final String COINBASE_ACCOUNTS_ENDPOINT = "/coinbase-accounts";

    CoinbaseProExchange exchange;

    @Override
    public PaymentTypes[] getPaymentTypes() {
        return exchange.get(PAYMENT_METHODS_ENDPOINT, PaymentTypes[].class);
    }

    @Override
    public CoinbaseAccounts[] getCoinbaseAccounts() {
        return exchange.get(COINBASE_ACCOUNTS_ENDPOINT, CoinbaseAccounts[].class);
    }
}