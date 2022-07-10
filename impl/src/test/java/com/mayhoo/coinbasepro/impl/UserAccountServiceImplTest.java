package com.mayhoo.coinbasepro.impl;

import com.mayhoo.coinbasepro.api.exchange.CoinbaseProExchange;
import com.mayhoo.coinbasepro.api.model.UserAccountData;
import com.mayhoo.coinbasepro.api.service.UserAccountService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class UserAccountServiceImplTest {
    @Mock
    CoinbaseProExchange mockExchange;

    UserAccountService service;

    @BeforeEach
    public void setup() {
        service = new UserAccountServiceImpl(mockExchange);
    }

    @Test
    void getters() {
        assertThat(service).hasFieldOrPropertyWithValue("exchange", mockExchange);
    }

    @Test
    void getUserAccounts() {
        UserAccountData mockResponse = UserAccountData.builder().build();
        when(mockExchange.get("/users/self/trailing-volume", UserAccountData.class)).thenReturn(mockResponse);
        UserAccountData actualResponse = service.getUserAccounts();
        assertThat(actualResponse).isSameAs(mockResponse);
    }
}