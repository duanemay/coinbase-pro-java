package com.mayhoo.coinbasepro.api.service;

import com.mayhoo.coinbasepro.api.model.Account;
import com.mayhoo.coinbasepro.api.model.AccountHistory;
import com.mayhoo.coinbasepro.api.model.Hold;

public interface AccountService {
    Account[] getAccounts();

    Account[] getPagedAccounts(String beforeOrAfter, Integer pageNumber, Integer limit);

    Account getAccount(String id);

    AccountHistory[] getAccountHistory(String accountId);

    AccountHistory[] getPagedAccountHistory(String accountId,
                                            String beforeOrAfter,
                                            Integer pageNumber,
                                            Integer limit);

    Hold[] getHolds(String accountId);

    Hold[] getPagedHolds(String accountId,
                         String beforeOrAfter,
                         Integer pageNumber,
                         Integer limit);
}