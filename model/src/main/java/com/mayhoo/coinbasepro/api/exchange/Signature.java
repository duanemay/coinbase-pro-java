package com.mayhoo.coinbasepro.api.exchange;

public interface Signature {
    String generate(String requestPath, String method, String body, String timestamp);
}