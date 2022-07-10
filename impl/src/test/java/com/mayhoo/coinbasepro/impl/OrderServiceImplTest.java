package com.mayhoo.coinbasepro.impl;

import com.mayhoo.coinbasepro.api.exchange.CoinbaseProExchange;
import com.mayhoo.coinbasepro.api.model.*;
import com.mayhoo.coinbasepro.api.service.OrderService;
import com.mayhoo.coinbasepro.assertj.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.mayhoo.coinbasepro.assertj.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class OrderServiceImplTest {
    @Mock
    CoinbaseProExchange mockExchange;

    OrderService service;

    @BeforeEach
    public void setup() {
        service = new OrderServiceImpl(mockExchange);
    }

    @Test
    void getters() {
        Assertions.assertThat(service).hasFieldOrPropertyWithValue("exchange", mockExchange);
    }

    @Test
    void getAccountHolds() {
        Hold[] mockResponse = new Hold[0];
        when(mockExchange.get("/orders/123/holds", Hold[].class)).thenReturn(mockResponse);
        Hold[] response = service.getAccountHolds("123");
        assertThat(response).isSameAs(mockResponse);
    }

    @Test
    void getPagedAccountHolds() {
        Hold[] mockResponse = new Hold[0];
        when(mockExchange.pagedGet("/orders/123/holds", Hold[].class, "before", 2, 12)).thenReturn(mockResponse);
        Hold[] response = service.getPagedAccountHolds("123", "before", 2, 12);
        assertThat(response).isSameAs(mockResponse);
    }

    @Test
    void getAccountOpenOrders() {
        Order[] mockResponse = new Order[0];
        when(mockExchange.get("/orders/123/orders", Order[].class)).thenReturn(mockResponse);
        Order[] response = service.getAccountOpenOrders("123");
        assertThat(response).isSameAs(mockResponse);
    }

    @Test
    void getPagedAccountOpenOrders() {
        Order[] mockResponse = new Order[0];
        when(mockExchange.pagedGet("/orders/123/orders", Order[].class, "before", 2, 12)).thenReturn(mockResponse);
        Order[] response = service.getPagedAccountOpenOrders("123", "before", 2, 12);
        assertThat(response).isSameAs(mockResponse);
    }

    @Test
    void getOrder() {
        Order mockResponse = Order.builder().build();
        when(mockExchange.get("/orders/123", Order.class)).thenReturn(mockResponse);
        Order response = service.getOrder("123");
        assertThat(response).isSameAs(mockResponse);
    }

    @Test
    void createOrder() {
        Order mockResponse = Order.builder().build();
        NewOrderSingle request = NewLimitOrderSingle.builder().build();
        when(mockExchange.post("/orders", Order.class, request)).thenReturn(mockResponse);
        Order response = service.createOrder(request);
        assertThat(response).isSameAs(mockResponse);
    }

    @Test
    void cancelOrder() {
        String mockResponse = "response";
        when(mockExchange.delete("/orders/123", String.class)).thenReturn(mockResponse);
        String response = service.cancelOrder("123");
        assertThat(response).isSameAs(mockResponse);
    }

    @Test
    void getOpenOrders() {
        Order[] mockResponse = new Order[0];
        when(mockExchange.get("/orders", Order[].class)).thenReturn(mockResponse);
        Order[] response = service.getOpenOrders();
        assertThat(response).isSameAs(mockResponse);
    }

    @Test
    void getPagedOpenOrders() {
        Order[] mockResponse = new Order[0];
        when(mockExchange.pagedGet("/orders", Order[].class, "before", 2, 12)).thenReturn(mockResponse);
        Order[] response = service.getPagedOpenOrders("before", 2, 12);
        assertThat(response).isSameAs(mockResponse);
    }

    @Test
    void cancelAllOpenOrders() {
        Order[] mockResponse = new Order[0];
        when(mockExchange.delete("/orders", Order[].class)).thenReturn(mockResponse);
        Order[] response = service.cancelAllOpenOrders();
        assertThat(response).isSameAs(mockResponse);
    }

    @Test
    void getAllFills() {
        Fill[] mockResponse = new Fill[0];
        when(mockExchange.get("/fills", Fill[].class)).thenReturn(mockResponse);
        Fill[] response = service.getAllFills();
        assertThat(response).isSameAs(mockResponse);
    }

    @Test
    void getPagedFills() {
        Fill[] mockResponse = new Fill[0];
        when(mockExchange.pagedGet("/fills", Fill[].class, "before", 2, 12)).thenReturn(mockResponse);
        Fill[] response = service.getPagedFills("before", 2, 12);
        assertThat(response).isSameAs(mockResponse);
    }

}