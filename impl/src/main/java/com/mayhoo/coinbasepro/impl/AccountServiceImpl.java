package com.mayhoo.coinbasepro.impl;

import com.mayhoo.coinbasepro.api.exchange.CoinbaseProExchange;
import com.mayhoo.coinbasepro.api.model.Account;
import com.mayhoo.coinbasepro.api.model.AccountHistory;
import com.mayhoo.coinbasepro.api.model.Hold;
import com.mayhoo.coinbasepro.api.service.AccountService;
import lombok.RequiredArgsConstructor;
import lombok.Value;

@Value
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private static final String ACCOUNTS_ENDPOINT = "/accounts";
    private static final String LEDGER = "/ledger";
    private static final String HOLDS = "/holds";

    CoinbaseProExchange exchange;

    @Override
    public Account[] getAccounts() {
        return exchange.get(ACCOUNTS_ENDPOINT, Account[].class);
    }

    @Override
    public Account[] getPagedAccounts(String beforeOrAfter, Integer pageNumber, Integer limit) {
        return exchange.pagedGet(ACCOUNTS_ENDPOINT,
                Account[].class,
                beforeOrAfter,
                pageNumber,
                limit);
    }

    @Override
    public Account getAccount(String id) {
        return exchange.get(ACCOUNTS_ENDPOINT + "/" + id, Account.class);
    }

    @Override
    public AccountHistory[] getAccountHistory(String accountId) {
        String accountHistoryEndpoint = ACCOUNTS_ENDPOINT + "/" + accountId + LEDGER;
        return exchange.get(accountHistoryEndpoint, AccountHistory[].class);
    }

    @Override
    public AccountHistory[] getPagedAccountHistory(String accountId,
                                                   String beforeOrAfter,
                                                   Integer pageNumber,
                                                   Integer limit) {

        String accountHistoryEndpoint = ACCOUNTS_ENDPOINT + "/" + accountId + LEDGER;
        return exchange.pagedGet(accountHistoryEndpoint,
                AccountHistory[].class,
                beforeOrAfter,
                pageNumber,
                limit);
    }

    @Override
    public Hold[] getHolds(String accountId) {
        String holdsEndpoint = ACCOUNTS_ENDPOINT + "/" + accountId + HOLDS;
        return exchange.get(holdsEndpoint, Hold[].class);
    }

    @Override
    public Hold[] getPagedHolds(String accountId,
                                String beforeOrAfter,
                                Integer pageNumber,
                                Integer limit) {
        String holdsEndpoint = ACCOUNTS_ENDPOINT + "/" + accountId + HOLDS;
        return exchange.pagedGet(holdsEndpoint,
                Hold[].class,
                beforeOrAfter,
                pageNumber,
                limit);
    }
}