package com.mayhoo.coinbasepro.impl;

import com.mayhoo.coinbasepro.api.exchange.CoinbaseProExchange;
import com.mayhoo.coinbasepro.api.model.CoinbasePaymentRequest;
import com.mayhoo.coinbasepro.api.model.PaymentRequest;
import com.mayhoo.coinbasepro.api.model.PaymentResponse;
import com.mayhoo.coinbasepro.api.service.DepositService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

import static com.mayhoo.coinbasepro.assertj.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DepositServiceImplTest {
    @Mock
    CoinbaseProExchange mockExchange;

    DepositService service;

    @BeforeEach
    public void setup() {
        service = new DepositServiceImpl(mockExchange);
    }

    @Test
    void getters() {
        assertThat(service).hasFieldOrPropertyWithValue("exchange", mockExchange);
    }

    @Test
    void depositViaPaymentMethod() {
        ArgumentCaptor<PaymentRequest> captor = ArgumentCaptor.forClass(PaymentRequest.class);
        PaymentResponse mockResponse = PaymentResponse.builder().build();
        when(mockExchange.post(eq("/deposits/payment-method"), eq(PaymentResponse.class), captor.capture())).thenReturn(mockResponse);
        PaymentResponse response = service.depositViaPaymentMethod(BigDecimal.TEN, "USD", "payment-method-id");
        assertThat(captor.getValue())
                .hasAmount(BigDecimal.TEN)
                .hasCurrency("USD")
                .hasPaymentMethodId("payment-method-id");
        assertThat(response).isSameAs(mockResponse);
    }

    @Test
    void coinbaseDeposit() {
        ArgumentCaptor<CoinbasePaymentRequest> captor = ArgumentCaptor.forClass(CoinbasePaymentRequest.class);
        PaymentResponse mockResponse = PaymentResponse.builder().build();
        when(mockExchange.post(eq("/deposits/coinbase-account"), eq(PaymentResponse.class), captor.capture())).thenReturn(mockResponse);
        PaymentResponse response = service.coinbaseDeposit(BigDecimal.TEN, "USD", "coinbaseAccountId");
        assertThat(captor.getValue())
                .hasAmount(BigDecimal.TEN)
                .hasCurrency("USD")
                .hasCoinbaseAccountId("coinbaseAccountId");
        assertThat(response).isSameAs(mockResponse);
    }
}