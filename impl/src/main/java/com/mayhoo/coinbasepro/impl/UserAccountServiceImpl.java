package com.mayhoo.coinbasepro.impl;

import com.mayhoo.coinbasepro.api.exchange.CoinbaseProExchange;
import com.mayhoo.coinbasepro.api.model.UserAccountData;
import com.mayhoo.coinbasepro.api.service.UserAccountService;
import lombok.RequiredArgsConstructor;
import lombok.Value;

@Value
@RequiredArgsConstructor
public class UserAccountServiceImpl implements UserAccountService {
    private static final String USER_ACCOUNT_ENDPOINT = "/users/self/trailing-volume";

    CoinbaseProExchange exchange;

    @Override
    public UserAccountData getUserAccounts() {
        return exchange.get(USER_ACCOUNT_ENDPOINT, UserAccountData.class);
    }
}