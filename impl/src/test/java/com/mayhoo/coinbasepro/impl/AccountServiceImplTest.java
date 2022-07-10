package com.mayhoo.coinbasepro.impl;

import com.mayhoo.coinbasepro.api.exchange.CoinbaseProExchange;
import com.mayhoo.coinbasepro.api.model.Account;
import com.mayhoo.coinbasepro.api.model.AccountHistory;
import com.mayhoo.coinbasepro.api.model.Hold;
import com.mayhoo.coinbasepro.api.service.AccountService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AccountServiceImplTest {
    @Mock
    CoinbaseProExchange mockExchange;

    AccountService service;

    @BeforeEach
    public void setup() {
        service = new AccountServiceImpl(mockExchange);
    }

    @Test
    void getters() {
        assertThat(service).hasFieldOrPropertyWithValue("exchange", mockExchange);
    }

    @Test
    void getAccounts() {
        Account[] mockResponse = new Account[0];
        when(mockExchange.get("/accounts", Account[].class)).thenReturn(mockResponse);
        Account[] actualResponse = service.getAccounts();
        assertThat(actualResponse).isSameAs(mockResponse);
    }

    @Test
    void getPagedAccounts() {
        Account[] mockResponse = new Account[0];
        when(mockExchange.pagedGet("/accounts", Account[].class, "before", 1, 2)).thenReturn(mockResponse);
        Account[] actualResponse = service.getPagedAccounts("before", 1, 2);
        assertThat(actualResponse).isSameAs(mockResponse);
    }

    @Test
    void getAccount() {
        Account mockResponse = Account.builder().build();
        when(mockExchange.get("/accounts/123", Account.class)).thenReturn(mockResponse);
        Account actualResponse = service.getAccount("123");
        assertThat(actualResponse).isSameAs(mockResponse);
    }

    @Test
    void getAccountHistory() {
        AccountHistory[] mockResponse = new AccountHistory[0];
        when(mockExchange.get("/accounts/123/ledger", AccountHistory[].class)).thenReturn(mockResponse);
        AccountHistory[] actualResponse = service.getAccountHistory("123");
        assertThat(actualResponse).isSameAs(mockResponse);
    }

    @Test
    void getPagedAccountHistory() {
        AccountHistory[] mockResponse = new AccountHistory[0];
        when(mockExchange.pagedGet("/accounts/123/ledger", AccountHistory[].class, "before", 1, 2)).thenReturn(mockResponse);
        AccountHistory[] actualResponse = service.getPagedAccountHistory("123", "before", 1, 2);
        assertThat(actualResponse).isSameAs(mockResponse);
    }

    @Test
    void getHolds() {
        Hold[] mockResponse = new Hold[0];
        when(mockExchange.get("/accounts/123/holds", Hold[].class)).thenReturn(mockResponse);
        Hold[] actualResponse = service.getHolds("123");
        assertThat(actualResponse).isSameAs(mockResponse);
    }

    @Test
    void getPagedHolds() {
        Hold[] mockResponse = new Hold[0];
        when(mockExchange.pagedGet("/accounts/123/holds", Hold[].class, "before", 1, 2)).thenReturn(mockResponse);
        Hold[] actualResponse = service.getPagedHolds("123", "before", 1, 2);
        assertThat(actualResponse).isSameAs(mockResponse);
    }
}