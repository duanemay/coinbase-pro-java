package com.mayhoo.coinbasepro.api.exchange;

import org.springframework.http.HttpEntity;

public interface CoinbaseProExchange {
    String getBaseUrl();

    HttpEntity<String> securityHeaders(String endpoint, String method, String body);

    <T> T get(String endpoint, Class<T> type);

    <T> T pagedGet(String endpoint, Class<T> responseClass, String beforeOrAfter, Integer pageNumber, Integer limit);

    <T, R> T post(String endpoint, Class<T> responseClass, R jsonObject);

    <T> T delete(String endpoint, Class<T> responseClass);
}