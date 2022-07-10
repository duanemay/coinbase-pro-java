package com.mayhoo.coinbasepro.impl;

import com.mayhoo.coinbasepro.api.exchange.CoinbaseProExchange;
import com.mayhoo.coinbasepro.api.model.MarketData;
import com.mayhoo.coinbasepro.api.model.Trade;
import com.mayhoo.coinbasepro.api.service.MarketDataService;
import com.mayhoo.coinbasepro.assertj.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MarketDataServiceImplTest {
    @Mock
    CoinbaseProExchange mockExchange;

    MarketDataService service;

    @BeforeEach
    public void setup() {
        service = new MarketDataServiceImpl(mockExchange);
    }

    @Test
    void getters() {
        Assertions.assertThat(service).hasFieldOrPropertyWithValue("exchange", mockExchange);
    }

    @Test
    void getMarketDataOrderBook_withLevel() {
        MarketData mockResponse = MarketData.builder().build();
        when(mockExchange.get("/products/123/book?level=level1", MarketData.class)).thenReturn(mockResponse);
        MarketData response = service.getMarketDataOrderBook("123", "level1");
        Assertions.assertThat(response).isSameAs(mockResponse);
    }

    @Test
    void getMarketDataOrderBook_withNullLevel() {
        MarketData mockResponse = MarketData.builder().build();
        when(mockExchange.get("/products/123/book", MarketData.class)).thenReturn(mockResponse);
        MarketData response = service.getMarketDataOrderBook("123", null);
        Assertions.assertThat(response).isSameAs(mockResponse);
    }

    @Test
    void getMarketDataOrderBook_withEmptyLevel() {
        MarketData mockResponse = MarketData.builder().build();
        when(mockExchange.get("/products/123/book", MarketData.class)).thenReturn(mockResponse);
        MarketData response = service.getMarketDataOrderBook("123", "");
        Assertions.assertThat(response).isSameAs(mockResponse);
    }

    @Test
    void getTrades() {
        Trade[] mockResponse = new Trade[0];
        when(mockExchange.get("/products/123/trades", Trade[].class)).thenReturn(mockResponse);
        Trade[] response = service.getTrades("123");
        assertThat(response).isSameAs(mockResponse);
    }
}