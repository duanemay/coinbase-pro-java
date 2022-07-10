package com.mayhoo.coinbasepro.impl;

import com.mayhoo.coinbasepro.api.exchange.CoinbaseProExchange;
import com.mayhoo.coinbasepro.api.model.Transfer;
import com.mayhoo.coinbasepro.api.service.TransferService;
import lombok.RequiredArgsConstructor;
import lombok.Value;

import java.math.BigDecimal;

/**
 * This class is best used in conjunction with the coinbase library
 * to get the coinbase account Id's. see: https://github.com/coinbase/coinbase-java
 */
@Value
@RequiredArgsConstructor
public class TransferServiceImpl implements TransferService {
    private static final String TRANSFER_ENDPOINT = "/transfers";

    CoinbaseProExchange exchange;

    @Override
    public String transfer(String type, BigDecimal amount, String coinbaseAccountId) {
        return exchange.post(TRANSFER_ENDPOINT,
                String.class,
                new Transfer(type, amount, coinbaseAccountId));
    }
}