package com.mayhoo.coinbasepro.api.service;

import java.math.BigDecimal;

public interface TransferService {
    String transfer(String type, BigDecimal amount, String coinbaseAccountId);
}