package com.mayhoo.coinbasepro.api.service;

import com.mayhoo.coinbasepro.api.model.MarketData;
import com.mayhoo.coinbasepro.api.model.Trade;

public interface MarketDataService {
    MarketData getMarketDataOrderBook(String productId, String level);

    Trade[] getTrades(String productId);
}