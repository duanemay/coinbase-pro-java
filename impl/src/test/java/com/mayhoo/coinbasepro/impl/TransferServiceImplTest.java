package com.mayhoo.coinbasepro.impl;

import com.mayhoo.coinbasepro.api.exchange.CoinbaseProExchange;
import com.mayhoo.coinbasepro.api.model.Transfer;
import com.mayhoo.coinbasepro.api.service.TransferService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

import static com.mayhoo.coinbasepro.assertj.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class TransferServiceImplTest {
    @Mock
    CoinbaseProExchange mockExchange;

    TransferService service;

    @BeforeEach
    public void setup() {
        service = new TransferServiceImpl(mockExchange);
    }

    @Test
    void getters() {
        assertThat(service).hasFieldOrPropertyWithValue("exchange", mockExchange);
    }

    @Test
    void transfer() {
        ArgumentCaptor<Transfer> captor = ArgumentCaptor.forClass(Transfer.class);
        String mockResponse = "response";
        when(mockExchange.post(eq("/transfers"), eq(String.class), captor.capture())).thenReturn(mockResponse);
        String response = service.transfer("type", BigDecimal.TEN, "coinbaseAccountId");
        assertThat(captor.getValue())
                .hasType("type")
                .hasAmount(BigDecimal.TEN)
                .hasCoinbaseAccountId("coinbaseAccountId");
        assertThat(response).isSameAs(mockResponse);
    }
}