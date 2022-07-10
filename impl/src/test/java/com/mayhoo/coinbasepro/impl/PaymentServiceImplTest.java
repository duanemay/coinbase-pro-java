package com.mayhoo.coinbasepro.impl;

import com.mayhoo.coinbasepro.api.exchange.CoinbaseProExchange;
import com.mayhoo.coinbasepro.api.model.CoinbaseAccounts;
import com.mayhoo.coinbasepro.api.model.PaymentTypes;
import com.mayhoo.coinbasepro.api.service.PaymentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class PaymentServiceImplTest {
    @Mock
    CoinbaseProExchange mockExchange;

    PaymentService service;

    @BeforeEach
    public void setup() {
        service = new PaymentServiceImpl(mockExchange);
    }

    @Test
    void getters() {
        assertThat(service).hasFieldOrPropertyWithValue("exchange", mockExchange);
    }

    @Test
    void getPaymentTypes() {
        PaymentTypes[] mockResponse = new PaymentTypes[0];
        when(mockExchange.get("/payment-methods", PaymentTypes[].class)).thenReturn(mockResponse);
        PaymentTypes[] actualResponse = service.getPaymentTypes();
        assertThat(actualResponse).isSameAs(mockResponse);
    }

    @Test
    void getCoinbaseAccounts() {
        CoinbaseAccounts[] mockResponse = new CoinbaseAccounts[0];
        when(mockExchange.get("/coinbase-accounts", CoinbaseAccounts[].class)).thenReturn(mockResponse);
        CoinbaseAccounts[] actualResponse = service.getCoinbaseAccounts();
        assertThat(actualResponse).isSameAs(mockResponse);
    }

}