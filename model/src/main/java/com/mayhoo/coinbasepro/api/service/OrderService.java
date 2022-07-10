package com.mayhoo.coinbasepro.api.service;

import com.mayhoo.coinbasepro.api.model.Fill;
import com.mayhoo.coinbasepro.api.model.Hold;
import com.mayhoo.coinbasepro.api.model.NewOrderSingle;
import com.mayhoo.coinbasepro.api.model.Order;

public interface OrderService {
    Hold[] getAccountHolds(String accountId);

    Hold[] getPagedAccountHolds(String accountId,
                                String beforeOrAfter,
                                Integer pageNumber,
                                Integer limit);

    Order[] getAccountOpenOrders(String accountId);

    Order[] getPagedAccountOpenOrders(String accountId,
                                      String beforeOrAfter,
                                      Integer pageNumber,
                                      Integer limit);

    Order getOrder(String orderId);

    Order createOrder(NewOrderSingle order);

    String cancelOrder(String orderId);

    Order[] getOpenOrders();

    Order[] getPagedOpenOrders(String beforeOrAfter,
                               Integer pageNumber,
                               Integer limit);

    Order[] cancelAllOpenOrders();

    Fill[] getAllFills();

    Fill[] getPagedFills(String beforeOrAfter,
                         Integer pageNumber,
                         Integer limit);
}