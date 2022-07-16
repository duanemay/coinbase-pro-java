package com.mayhoo.coinbasepro.impl;

import com.mayhoo.coinbasepro.api.exchange.CoinbaseProExchange;
import com.mayhoo.coinbasepro.api.model.Product;
import com.mayhoo.coinbasepro.api.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.Value;

@Value
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private static final String PRODUCTS_ENDPOINT = "/products";

    CoinbaseProExchange exchange;

    @Override
    public Product[] getProducts() {
        return exchange.get(PRODUCTS_ENDPOINT, Product[].class);
    }
}