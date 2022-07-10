package com.mayhoo.coinbasepro.impl;

import com.mayhoo.coinbasepro.api.exchange.CoinbaseProExchange;
import com.mayhoo.coinbasepro.api.model.Fill;
import com.mayhoo.coinbasepro.api.model.Hold;
import com.mayhoo.coinbasepro.api.model.NewOrderSingle;
import com.mayhoo.coinbasepro.api.model.Order;
import com.mayhoo.coinbasepro.api.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.Value;

@Value
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private static final String ORDERS_ENDPOINT = "/orders";
    private static final String FILLS_ENDPOINT = "/fills";
    private static final String OPEN_ORDERS_METHOD = "/orders";

    CoinbaseProExchange exchange;

    @Override
    public Hold[] getAccountHolds(String accountId) {
        return exchange.get(ORDERS_ENDPOINT + "/" + accountId + "/holds",
                Hold[].class);
    }

    @Override
    public Hold[] getPagedAccountHolds(String accountId,
                                       String beforeOrAfter,
                                       Integer pageNumber,
                                       Integer limit) {
        return exchange.pagedGet(ORDERS_ENDPOINT + "/" + accountId + "/holds",
                Hold[].class,
                beforeOrAfter,
                pageNumber,
                limit);
    }

    @Override
    public Order[] getAccountOpenOrders(String accountId) {
        return exchange.get(ORDERS_ENDPOINT + "/" + accountId + OPEN_ORDERS_METHOD,
                Order[].class);
    }

    @Override
    public Order[] getPagedAccountOpenOrders(String accountId,
                                             String beforeOrAfter,
                                             Integer pageNumber,
                                             Integer limit) {
        return exchange.pagedGet(ORDERS_ENDPOINT + "/" + accountId + OPEN_ORDERS_METHOD,
                Order[].class,
                beforeOrAfter,
                pageNumber,
                limit);
    }

    @Override
    public Order getOrder(String orderId) {
        return exchange.get(ORDERS_ENDPOINT + "/" + orderId, Order.class);
    }

    @Override
    public Order createOrder(NewOrderSingle order) {
        return exchange.post(ORDERS_ENDPOINT, Order.class, order);
    }

    @Override
    public String cancelOrder(String orderId) {
        String deleteEndpoint = ORDERS_ENDPOINT + "/" + orderId;
        return exchange.delete(deleteEndpoint, String.class);
    }

    @Override
    public Order[] getOpenOrders() {
        return exchange.get(ORDERS_ENDPOINT, Order[].class);
    }

    @Override
    public Order[] getPagedOpenOrders(String beforeOrAfter,
                                      Integer pageNumber,
                                      Integer limit) {
        return exchange.pagedGet(ORDERS_ENDPOINT, Order[].class,
                beforeOrAfter,
                pageNumber,
                limit);
    }

    @Override
    public Order[] cancelAllOpenOrders() {
        return exchange.delete(ORDERS_ENDPOINT, Order[].class);
    }

    @Override
    public Fill[] getAllFills() {
        return exchange.get(FILLS_ENDPOINT, Fill[].class);
    }

    @Override
    public Fill[] getPagedFills(String beforeOrAfter,
                                Integer pageNumber,
                                Integer limit) {
        return exchange.pagedGet(FILLS_ENDPOINT, Fill[].class,
                beforeOrAfter,
                pageNumber,
                limit);
    }
}