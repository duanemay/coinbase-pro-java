package com.mayhoo.coinbasepro.impl;

import com.mayhoo.coinbasepro.api.exchange.CoinbaseProExchange;
import com.mayhoo.coinbasepro.api.model.Product;
import com.mayhoo.coinbasepro.api.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class ProductServiceImplTest {
    @Mock
    CoinbaseProExchange mockExchange;

    ProductService service;

    @BeforeEach
    public void setup() {
        service = new ProductServiceImpl(mockExchange);
    }

    @Test
    void getters() {
        assertThat(service).hasFieldOrPropertyWithValue("exchange", mockExchange);
    }

    @Test
    void getProducts() {
        Product[] mockResponse = new Product[0];
        when(mockExchange.get("/products", Product[].class)).thenReturn(mockResponse);
        Product[] actualResponse = service.getProducts();
        assertThat(actualResponse).isSameAs(mockResponse);
    }
}