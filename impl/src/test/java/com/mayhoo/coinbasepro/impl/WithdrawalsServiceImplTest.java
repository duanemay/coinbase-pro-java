package com.mayhoo.coinbasepro.impl;

import com.mayhoo.coinbasepro.api.exchange.CoinbaseProExchange;
import com.mayhoo.coinbasepro.api.model.CoinbasePaymentRequest;
import com.mayhoo.coinbasepro.api.model.CryptoPaymentRequest;
import com.mayhoo.coinbasepro.api.model.PaymentRequest;
import com.mayhoo.coinbasepro.api.model.PaymentResponse;
import com.mayhoo.coinbasepro.api.service.WithdrawalsService;
import com.mayhoo.coinbasepro.assertj.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class WithdrawalsServiceImplTest {
    @Mock
    CoinbaseProExchange mockExchange;

    WithdrawalsService service;

    @BeforeEach
    public void setup() {
        service = new WithdrawalsServiceImpl(mockExchange);
    }

    @Test
    void getters() {
        Assertions.assertThat(service).hasFieldOrPropertyWithValue("exchange", mockExchange);
    }

    @Test
    void depositViaPaymentMethod() {
        ArgumentCaptor<PaymentRequest> captor = ArgumentCaptor.forClass(PaymentRequest.class);
        PaymentResponse mockResponse = PaymentResponse.builder().build();
        when(mockExchange.post(eq("/withdrawals/payment-method"), eq(PaymentResponse.class), captor.capture())).thenReturn(mockResponse);
        PaymentResponse response = service.makeWithdrawalToPaymentMethod(BigDecimal.TEN, "USD", "payment-method-id");
        Assertions.assertThat(captor.getValue())
                .hasAmount(BigDecimal.TEN)
                .hasCurrency("USD")
                .hasPaymentMethodId("payment-method-id");
        Assertions.assertThat(response).isSameAs(mockResponse);
    }

    @Test
    void makeWithdrawalToCoinbase() {
        ArgumentCaptor<CoinbasePaymentRequest> captor = ArgumentCaptor.forClass(CoinbasePaymentRequest.class);
        PaymentResponse mockResponse = PaymentResponse.builder().build();
        when(mockExchange.post(eq("/withdrawals/coinbase-account"), eq(PaymentResponse.class), captor.capture())).thenReturn(mockResponse);
        PaymentResponse response = service.makeWithdrawalToCoinbase(BigDecimal.TEN, "USD", "coinbaseAccountId");
        Assertions.assertThat(captor.getValue())
                .hasAmount(BigDecimal.TEN)
                .hasCurrency("USD")
                .hasCoinbaseAccountId("coinbaseAccountId");
        Assertions.assertThat(response).isSameAs(mockResponse);
    }


    @Test
    void makeWithdrawalToCryptoAccount() {
        ArgumentCaptor<CryptoPaymentRequest> captor = ArgumentCaptor.forClass(CryptoPaymentRequest.class);
        PaymentResponse mockResponse = PaymentResponse.builder().build();
        when(mockExchange.post(eq("/withdrawals/crypto"), eq(PaymentResponse.class), captor.capture())).thenReturn(mockResponse);
        PaymentResponse response = service.makeWithdrawalToCryptoAccount(BigDecimal.TEN, "USD", "cryptoAddress");
        Assertions.assertThat(captor.getValue())
                .hasAmount(BigDecimal.TEN)
                .hasCurrency("USD")
                .hasCryptoAddress("cryptoAddress");
        Assertions.assertThat(response).isSameAs(mockResponse);
    }

}