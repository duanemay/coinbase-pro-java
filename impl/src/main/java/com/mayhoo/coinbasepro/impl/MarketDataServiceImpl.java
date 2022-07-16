package com.mayhoo.coinbasepro.impl;

import com.mayhoo.coinbasepro.api.exchange.CoinbaseProExchange;
import com.mayhoo.coinbasepro.api.model.MarketData;
import com.mayhoo.coinbasepro.api.model.Trade;
import com.mayhoo.coinbasepro.api.service.MarketDataService;
import lombok.RequiredArgsConstructor;
import lombok.Value;

@Value
@RequiredArgsConstructor
public class MarketDataServiceImpl implements MarketDataService {
    private static final String PRODUCT_ENDPOINT = "/products";
    private static final String BOOK = "/book";
    private static final String TRADES = "/trades";

    CoinbaseProExchange exchange;

    @Override
    public MarketData getMarketDataOrderBook(String productId, String level) {
        String marketDataEndpoint = PRODUCT_ENDPOINT + "/" + productId + BOOK;
        if (level != null && !level.equals("")) {
            marketDataEndpoint += "?level=" + level;
        }
        return exchange.get(marketDataEndpoint, MarketData.class);
    }

    @Override
    public Trade[] getTrades(String productId) {
        String tradesEndpoint = PRODUCT_ENDPOINT + "/" + productId + TRADES;
        return exchange.get(tradesEndpoint, Trade[].class);

    }
}